package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import me.gurasic.incremental.GUIs.ApothGUI.Farming.Upgrades.Wheat.Wheat;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.window.Window;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ListenePlant implements Listener {
    private Incremental plugin;
    Random random = new Random();
    private List<Plant> plants;
    public static Window window;
    ItemProvider FarmingItem = new ItemBuilder(Material.GOLDEN_HOE).setDisplayName("§l§6Farming")
            .addLoreLines("PlaceHolder");
    ItemProvider GlassDark = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ");

    public ListenePlant(Incremental plugin) {
        this.plugin = plugin;
        this.plants = new ArrayList<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # F # # # #",
                        "# W . . . . . . #",
                        "# . . . . . . . #",
                        "# . . . . . . . #")
                .addIngredient('#', GlassDark)
                .addIngredient('W', new Wheat())
                .addIngredient('F', FarmingItem).build();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
             if (item != null && item.getType() == Material.WHEAT_SEEDS) {
                if (block.getType() == Material.FARMLAND) {
                    plants.add(new Plant("Wheat", "#ffaa00",7200, Arrays.asList(
                            new Stage("Mature", 0, 10, Material.GREEN_CONCRETE),
                            new Stage("Sprout", 11, 20, Material.GREEN_CONCRETE_POWDER),
                            new Stage("Seed", 21, 30, Material.GREEN_WOOL)
                    ), (int) accessPlayerDataF(player.getUniqueId(),"WheatSpeed")));
                    plugin.plantSeed(block.getLocation().add(0.5,1.5,0.5), plants.get(0), 30, player, (int) accessPlayerDataF(player.getUniqueId(),  plants.get(0).getName()+"Speed"));
                }
            }
             if (block.getType() == Material.FARMLAND) {
                 window = Window.single()
                         .setViewer(player)
                         .setTitle("Plant Upgrades")
                         .setGui(gui)
                         .build(player);
                 window.open();
            }
            for (Plant plant : plants) {
                if (block.getType() == plant.getStages().get(0).getMaterial() && plant.getFinished()) {
                    Location buffStandLocation = plant.GetLocation().clone().add(0, 1.1, 0);
                    Location nameStandLocation = plant.GetLocation().clone().add(0, 1.5, 0);
                    plant.getArmorStands().clear();
                    ArmorStand buffStand = spawnText(buffStandLocation, Component.text("Buff Active!", NamedTextColor.GOLD));
                    ArmorStand nameStand = spawnText(nameStandLocation, Component.text(plant.getName(), NamedTextColor.GREEN));
                    plant.addArmorStand("buffStand", buffStand);
                    plant.addArmorStand("nameStand", nameStand);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 0f);
                    new BukkitRunnable() {
                        int buffTimeLeft = plant.GetBuffDuration();
                        @Override
                        public void run() {
                            if (buffTimeLeft <= 0) {
                                storePlayerDataF(player.getUniqueId(), plant.getName()+"Buff", false);
                                plant.getArmorStand("buffStand").customName(Component.text("Done, Click to Collect!!", NamedTextColor.GOLD));
                                plant.setFinishedBuff(true);
                                cancel();
                            } else {
                                storePlayerDataF(player.getUniqueId(), plant.getName()+"Buff", true);
                                plant.getArmorStand("buffStand").customName(Component.text("Buff Time left: " + formatTime(buffTimeLeft), NamedTextColor.GOLD));
                                buffTimeLeft--;
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 20L);  // 20 ticks = 1 second
                    if (plant.getFinishedBuff()) {
                    int AwardedPoints = random.nextInt(5,(int) accessPlayerDataF(player.getUniqueId(), plant.getName()+"Value") + 6);
                    int PointCount = (int) accessPlayerDataF(player.getUniqueId(), plant.getName());
                    plant.GetLocation().getBlock().setType(Material.AIR);
                    plant.GetLocation().add(0,-1,0).getBlock().setType(Material.FARMLAND);
                    plant.GetLocation().add(0,1,0);
                    plant.getArmorStand("nameStand").remove();
                    plant.getArmorStand("timeStand").remove();
                    plant.getArmorStands().clear();
                    plant.setFinishedBuff(false);
                    storePlayerDataF(player.getUniqueId(), plant.getName(),PointCount + AwardedPoints);
                    player.sendMessage(Component.text("- - - - - - - Plant Harvested!! - - - - - - - -", TextColor.fromHexString("#38ff42")));
                    player.sendMessage(Component.text("Plant Type: ", TextColor.fromHexString("#787878"))
                            .append(Component.text(plant.getName(), TextColor.fromHexString(plant.getHexColor()))));
                    player.sendMessage(Component.text("Obtained: ", TextColor.fromHexString("#787878"))
                            .append(Component.text(AwardedPoints + "◎", TextColor.fromHexString(plant.getHexColor()))));
                    player.sendMessage(Component.text("- - - - - - - - - - - - - - - - - - - - - - - -", TextColor.fromHexString("#38ff42")));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 0f);
                    }
                }
            }
        }
    }
    private ArmorStand spawnText(Location location, Component text) {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCanPickupItems(false);
        armorStand.customName(text);
        armorStand.setMarker(true);
        armorStand.setCustomNameVisible(true);
        return armorStand;
    }
    private String formatTime(int timeInSeconds) {
        int days = timeInSeconds / 86400;
        int hours = (timeInSeconds % 86400) / 3600;
        int minutes = ((timeInSeconds % 86400) % 3600) / 60;
        int seconds = ((timeInSeconds % 86400) % 3600) % 60;
        return days + "d" + hours + "h" + minutes + "m" + seconds + "s";
    }

    public void storePlayerDataF(UUID uuid, String key, Object value) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        try {
            // Store data
            playerData.set(key, value);
            playerData.save(playerFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("ERROR storing Farming data! See stacktrace for more details:");
            e.printStackTrace();
        }
    }
    private Object accessPlayerDataF(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
}

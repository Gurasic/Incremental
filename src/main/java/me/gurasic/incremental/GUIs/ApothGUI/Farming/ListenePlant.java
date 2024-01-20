package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import me.gurasic.incremental.GUIs.ApothGUI.Farming.Upgrades.Wheat.Wheat;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
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
        plants.add(new Plant("Wheat", "#ffaa00", Arrays.asList(
                new Stage("Mature", 0, 10, Material.GREEN_CONCRETE),
                new Stage("Sprout", 11, 20, Material.GREEN_CONCRETE_POWDER),
                new Stage("Seed", 21, 30, Material.GREEN_WOOL)
        )));
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
                    plugin.plantSeed(block.getLocation().add(0.5,1.5,0.5), plants.get(0), 30, player);
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
                    int AwardedPoints = random.nextInt(5,(int) accessPlayerDataF(player.getUniqueId(), plant.getName()+"Value") + 6);
                    int PointCount = (int) accessPlayerDataF(player.getUniqueId(), plant.getName());
                    plant.GetLocation().getBlock().setType(Material.AIR);
                    plant.GetLocation().add(0,-1,0).getBlock().setType(Material.FARMLAND);
                    plant.GetLocation().add(0,1,0);
                    plant.getArmorStand(0).remove();
                    plant.getArmorStand(1).remove();
                    plant.getArmorStands().clear();
                    storePlayerDataF(player.getUniqueId(), plant.getName(),PointCount + AwardedPoints);
                    player.sendMessage(Component.text("- - - - - - - Plant Harvested!! - - - - - - - -", TextColor.fromHexString("#38ff42")));
                    player.sendMessage(Component.text("Plant Type: ", TextColor.fromHexString("#787878"))
                            .append(Component.text(plant.getName(),TextColor.fromHexString(plant.getHexColor()))));
                    player.sendMessage(Component.text("Obtained: ", TextColor.fromHexString("#787878"))
                            .append(Component.text(AwardedPoints+"◎",TextColor.fromHexString(plant.getHexColor()))));
                    player.sendMessage(Component.text("- - - - - - - - - - - - - - - - - - - - - - - -", TextColor.fromHexString("#38ff42")));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 0f);
                }
            }
        }
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

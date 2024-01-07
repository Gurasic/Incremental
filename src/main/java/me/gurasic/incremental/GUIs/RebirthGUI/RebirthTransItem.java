package me.gurasic.incremental.GUIs.RebirthGUI;

import me.gurasic.incremental.GUIs.ChallengeMenu.ChallengeItem;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class RebirthTransItem extends AbstractItem {
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.TOTEM_OF_UNDYING).setDisplayName("§dRebirth")
                .addLoreLines("§7- Varpurge yourself entirely")
                .addLoreLines("§7- Gain §c+1§7 and earn §c♦§7")
                .addLoreLines("§7- Rebirth Upgrades are saved")
                .addLoreLines("§7- Challenge Progress is Saved")
                .addLoreLines("")
                .addLoreLines("§cRequires §5+30");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int CurrentRebirthPoints = (int) accessPlayerData(player.getUniqueId(), "Rebirth_Points");
        int CurrentRebirthCount = (int) accessPlayerData(player.getUniqueId(), "Rebirth_Level");
        Random random = new Random();
        if (clickType.isLeftClick()) {
            if ((int) accessPlayerData(player.getUniqueId(), "SuperPrestigeLevel") >= 30) {
                if ((boolean) accessPlayerData(player.getUniqueId(), "The_Vault")) {
                    storePlayerData(player.getUniqueId(), "playerPrestige", 1);
                    storePlayerData(player.getUniqueId(), "prestigePoints", 40);
                    storePlayerData(player.getUniqueId(), "SuperPrestigeLevel",  1);
                    storePlayerData(player.getUniqueId(), "SuperPrestigePoints", 4);
                }
                else {
                    storePlayerData(player.getUniqueId(), "playerPrestige", 0);
                    storePlayerData(player.getUniqueId(), "prestigePoints", 0);
                    storePlayerData(player.getUniqueId(), "SuperPrestigeLevel",  0);
                    storePlayerData(player.getUniqueId(), "SuperPrestigePoints", 0);
                }
                storePlayerData(player.getUniqueId(), "pointCount", 0);
                storePlayerData(player.getUniqueId(), "multiCount", 1);
                storePlayerData(player.getUniqueId(), "playerLevel", 1);
                storePlayerData(player.getUniqueId(), "beforeCost", 1);
                storePlayerData(player.getUniqueId(), "LuckPrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "GenerosityPrestigeCost", 3);
                storePlayerData(player.getUniqueId(), "DefensePrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "DefensePrestigeCost", 100);
                storePlayerData(player.getUniqueId(), "GiveDefenseCharm", false);
                storePlayerData(player.getUniqueId(), "PricesPrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "PricesPrestigeCost", 10);
                storePlayerData(player.getUniqueId(), "WishingPrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "ClickerPrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "ClickerPrestigeCost", 2);
                storePlayerData(player.getUniqueId(), "ArmorPrestigeLevel", 0);
                storePlayerData(player.getUniqueId(), "ArmorPrestigeCost", 3);
                storePlayerData(player.getUniqueId(), "RangerSP", false);
                storePlayerData(player.getUniqueId(), "Cleaning_Service", false);
                storePlayerData(player.getUniqueId(), "Booster", false);
                storePlayerData(player.getUniqueId(), "Good_Will", false);
                storePlayerData(player.getUniqueId(), "Out_For_Blood", false);
                storePlayerData(player.getUniqueId(), "Lucky_day", false);
                storePlayerData(player.getUniqueId(), "Well_Feed", false);
                storePlayerData(player.getUniqueId(), "Refreshing_Sip", false);
                storePlayerData(player.getUniqueId(), "The_Perfect_Wish", false);
                storePlayerData(player.getUniqueId(), "Crushing_Blows", false);
                storePlayerData(player.getUniqueId(), "Fist_Mastery", false);
                storePlayerData(player.getUniqueId(), "Clicking_Expert", false);
                storePlayerData(player.getUniqueId(), "Fast_Pass", false);
                storePlayerData(player.getUniqueId(), "Blind_Rage", false);
                storePlayerData(player.getUniqueId(), "Clover_Blades", false);
                storePlayerData(player.getUniqueId(), "Better_Blocks", false);
                storePlayerData(player.getUniqueId(), "Tank", false);
                storePlayerData(player.getUniqueId(), "FleetFoot", false);
                storePlayerData(player.getUniqueId(), "PlayerSpeedCapBool", true);
                storePlayerData(player.getUniqueId(), "Healthy", false);
                storePlayerData(player.getUniqueId(), "HealthyBool", true);
                storePlayerData(player.getUniqueId(), "QuickBuy", false);
                storePlayerData(player.getUniqueId(), "Hasty_Hands", false);
                storePlayerData(player.getUniqueId(), "Serial_Killer", false);
                storePlayerData(player.getUniqueId(), "Clicking_Legend", false);
                storePlayerData(player.getUniqueId(), "Master_Ranger", false);
                storePlayerData(player.getUniqueId(), "Blood_Is_Fuel", false);
                storePlayerData(player.getUniqueId(), "Incredible_Findings", false);
                player.setWalkSpeed(0.2f);
                player.setHealth(20);
                player.playSound(player, Sound.ENTITY_ENDER_DRAGON_GROWL, 15f, 0f);
                player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
                player.playSound(player, Sound.ENTITY_WITHER_HURT, 15f,0f);
                player.sendMessage(Component.text(player.getName()+" got a +1 +1 +1 +1 +1!", TextColor.fromHexString("#ff2e2e"), TextDecoration.BOLD));
                storePlayerData(player.getUniqueId(), "Rebirth_Points", CurrentRebirthPoints + 5);
                storePlayerData(player.getUniqueId(), "Rebirth_Level", CurrentRebirthCount + 1);
                ChallengeItem.HasRebirth = true;
                Incremental plugin = Incremental.getInstance();
                World world = player.getWorld();
                Vector playerPos = player.getLocation().toVector();
                if ((boolean) accessPlayerData(player.getUniqueId(), "RebirthAnimation")) {
                new BukkitRunnable() {
                    double t = 0;
                    public void run() {
                        t += Math.PI/16;
                        for (double theta = 0; theta <= 2*Math.PI; theta += Math.PI/32) {
                            double x = t * Math.cos(theta);
                            double y = 0.1;
                            double z = t * Math.sin(theta);

                            Vector position = playerPos.clone().add(new Vector(x, y, z));

                            float ratio = (float) (theta / (2 * Math.PI));
                            int red, green;
                            if (ratio <= 0.5) {
                                red = 255;
                                green = (int) (165 * (2 * ratio));
                            } else {
                                red = 255;
                                green = 165 - (int) (165 * (2 * (ratio - 0.5)));
                            }

                            int blue = 0;
                            Color color = Color.fromRGB(red, green, blue);
                            Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);
                            player.spawnParticle(Particle.REDSTONE, position.toLocation(world), 0, dustOptions);


                            if (t > 2) {
                                for(int i = 0; i < 5; i++) {
                                double randomX = random.nextDouble() * 3 - 1;
                                double randomY = random.nextDouble() * 4 - 1;
                                double randomZ = random.nextDouble() * 3 - 1;

                                Vector randomPosition = playerPos.clone().add(new Vector(randomX, randomY, randomZ));
                                player.spawnParticle(Particle.REDSTONE, randomPosition.toLocation(world), 0, dustOptions);
                                }
                                this.cancel();

                            }
                        }
                    }
                }.runTaskTimer(plugin, 0, 1);
            }
        }
    }
}
    private Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
    public void storePlayerData(UUID uuid, String key, Object value) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        try {
            // Store data
            playerData.set(key, value);
            playerData.save(playerFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("ERROR storing player data! See stacktrace for more details:");
            e.printStackTrace();
        }
    }
}

package me.gurasic.incremental.GUIs.ApothGUI;

import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ApothTransListener implements Listener {
    private Incremental plugin;

    public ApothTransListener(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        int CurrentSuns = (int) plugin.accessPlayerData(player.getUniqueId(), "ApothPoints");
        int CurrentApothLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "ApothLevel");
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {
                // The AIMATION (NEEDS REWORK)
//                new BukkitRunnable() {
//                    private double centerX = player.getLocation().getX();
//                    private double centerY = player.getLocation().getY();
//                    private double centerZ = player.getLocation().getZ();
//                    private double height = 8;
//                    private double radius = 2; // Reduced radius to bring the ring closer to the player
//                    private long startTime = System.currentTimeMillis();
//                    private int ringCount = 0;
//                    public void run() {
//                        if (System.currentTimeMillis() - startTime > 5000) {
//                            this.cancel();
//                            // Spawn a big particle explosion
//                            player.playSound(player, Sound.ENTITY_ENDER_DRAGON_GROWL, 15f, 0f);
//                            player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
//                            player.playSound(player, Sound.ENTITY_WITHER_HURT, 15f,0f);
//                            player.playSound(player, Sound.ENTITY_WITHER_DEATH, 15f,0f);
//                            player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 15f,0f);
//                            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, new Location(player.getWorld(), centerX, centerY, centerZ), 1);
//                            player.sendMessage(Component.text(player.getName()+" got a +1 +1 +1 +1 +1 +1!", TextColor.fromHexString("#00ffea"), TextDecoration.BOLD));
//                            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
//                            return;
//                        }
//                        for (int i = 0; i < 50; i++) {
//                            double theta = 2 * Math.PI * Math.random();
//                            double phi = Math.acos(2 * Math.random() - 1);
//                            double r = 12 * Math.cbrt(Math.random()); // Cube root ensures uniform distribution
//                            double x = centerX + r * Math.sin(phi) * Math.cos(theta);
//                            double y = centerY + (Math.random() * height); // Random height within 10 blocks
//                            double z = centerZ + r * Math.sin(phi) * Math.sin(theta);
//                            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
//                            player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, y, z), 20, dustOptions); // Increased particle count to 20
//                        }
//                        if (System.currentTimeMillis() - startTime > ringCount * 500) { // Spawn a new ring every 0.5 seconds
//                            new BukkitRunnable() {
//                                private double ringCenterY = player.getLocation().getY() + height; // Start the ring at the top
//                                public void run() {
//                                    if (ringCenterY < centerY) {
//                                        this.cancel();
//                                        return;
//                                    }
//                                    for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 64) {
//                                        double x = player.getLocation().getX() + radius * Math.cos(theta);
//                                        double y = ringCenterY;
//                                        double z = player.getLocation().getZ() + radius * Math.sin(theta);
//                                        Particle.DustOptions dustOptions2 = new Particle.DustOptions(Color.fromRGB(173, 12, 0), 1);
//                                        player.getWorld().spawnParticle(Particle.REDSTONE, new Location(player.getWorld(), x, y, z), 10, dustOptions2); // Reduced particle count to 10
//                                    }
//                                    ringCenterY--; // Move the ring down one block
//                                }
//                            }.runTaskTimer(plugin, 0, 3);
//                            ringCount++;
//                        }
//                    }
//                }.runTaskTimer(plugin, 0, 1);




                if ((int) plugin.accessPlayerData(player.getUniqueId(), "Rebirth_Level") >= 30) {
                    plugin.storePlayerData(player.getUniqueId(), "playerPrestige", 0);
                    plugin.storePlayerData(player.getUniqueId(), "prestigePoints", 0);
                    plugin.storePlayerData(player.getUniqueId(), "SuperPrestigeLevel",  0);
                    plugin.storePlayerData(player.getUniqueId(), "SuperPrestigePoints", 0);
                    plugin.storePlayerData(player.getUniqueId(), "pointCount", 0);
                    plugin.storePlayerData(player.getUniqueId(), "multiCount", 1);
                    plugin.storePlayerData(player.getUniqueId(), "playerLevel", 1);
                    plugin.storePlayerData(player.getUniqueId(), "beforeCost", 1);
                    plugin.storePlayerData(player.getUniqueId(), "LuckPrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "GenerosityPrestigeCost", 3);
                    plugin.storePlayerData(player.getUniqueId(), "DefensePrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "DefensePrestigeCost", 100);
                    plugin.storePlayerData(player.getUniqueId(), "GiveDefenseCharm", false);
                    plugin.storePlayerData(player.getUniqueId(), "PricesPrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "PricesPrestigeCost", 10);
                    plugin.storePlayerData(player.getUniqueId(), "WishingPrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "ClickerPrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "ClickerPrestigeCost", 2);
                    plugin.storePlayerData(player.getUniqueId(), "ArmorPrestigeLevel", 0);
                    plugin.storePlayerData(player.getUniqueId(), "ArmorPrestigeCost", 3);
                    plugin.storePlayerData(player.getUniqueId(), "RangerSP", false);
                    plugin.storePlayerData(player.getUniqueId(), "Cleaning_Service", false);
                    plugin.storePlayerData(player.getUniqueId(), "Booster", false);
                    plugin.storePlayerData(player.getUniqueId(), "Good_Will", false);
                    plugin.storePlayerData(player.getUniqueId(), "Out_For_Blood", false);
                    plugin.storePlayerData(player.getUniqueId(), "Lucky_day", false);
                    plugin.storePlayerData(player.getUniqueId(), "Well_Feed", false);
                    plugin.storePlayerData(player.getUniqueId(), "Refreshing_Sip", false);
                    plugin.storePlayerData(player.getUniqueId(), "The_Perfect_Wish", false);
                    plugin.storePlayerData(player.getUniqueId(), "Crushing_Blows", false);
                    plugin.storePlayerData(player.getUniqueId(), "Fist_Mastery", false);
                    plugin.storePlayerData(player.getUniqueId(), "Clicking_Expert", false);
                    plugin.storePlayerData(player.getUniqueId(), "Fast_Pass", false);
                    plugin.storePlayerData(player.getUniqueId(), "Blind_Rage", false);
                    plugin.storePlayerData(player.getUniqueId(), "Clover_Blades", false);
                    plugin.storePlayerData(player.getUniqueId(), "Better_Blocks", false);
                    plugin.storePlayerData(player.getUniqueId(), "Tank", false);
                    plugin.storePlayerData(player.getUniqueId(), "FleetFoot", false);
                    plugin.storePlayerData(player.getUniqueId(), "PlayerSpeedCapBool", true);
                    plugin.storePlayerData(player.getUniqueId(), "Healthy", false);
                    plugin.storePlayerData(player.getUniqueId(), "HealthyBool", true);
                    plugin.storePlayerData(player.getUniqueId(), "QuickBuy", false);
                    plugin.storePlayerData(player.getUniqueId(), "Hasty_Hands", false);
                    plugin.storePlayerData(player.getUniqueId(), "Serial_Killer", false);
                    plugin.storePlayerData(player.getUniqueId(), "Clicking_Legend", false);
                    plugin.storePlayerData(player.getUniqueId(), "Master_Ranger", false);
                    plugin.storePlayerData(player.getUniqueId(), "Blood_Is_Fuel", false);
                    plugin.storePlayerData(player.getUniqueId(), "Incredible_Findings", false);
                    // -- Rebirth Values --
                    plugin.storePlayerData(player.getUniqueId(), "Expertise", false);
                    plugin.storePlayerData(player.getUniqueId(), "Prestige_Accelerator", false);
                    plugin.storePlayerData(player.getUniqueId(), "Speedrunner", false);
                    plugin.storePlayerData(player.getUniqueId(), "Brightest_Night", false);
                    plugin.storePlayerData(player.getUniqueId(), "The_Vault", false);
                    plugin.storePlayerData(player.getUniqueId(), "MilSword", false);
                    plugin.storePlayerData(player.getUniqueId(), "Block_Fiend", false);
                    plugin.storePlayerData(player.getUniqueId(), "Extreme_Speed", false);
                    plugin.storePlayerData(player.getUniqueId(), "Mastery", false);
                    plugin.storePlayerData(player.getUniqueId(), "Hollow_Heart", false);
                    plugin.storePlayerData(player.getUniqueId(), "HollowBool", false);
                    plugin.storePlayerData(player.getUniqueId(), "Rebirth_Level", 0);
                    plugin.storePlayerData(player.getUniqueId(), "Rebirth_Points", 0);
                    player.setWalkSpeed(0.2f);
                    AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    if (maxHealth != null) {
                        maxHealth.setBaseValue(20);
                    }
                    player.playSound(player, Sound.ENTITY_ENDER_DRAGON_GROWL, 15f, 0f);
                    player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
                    player.playSound(player, Sound.ENTITY_WITHER_HURT, 15f,0f);
                    player.playSound(player, Sound.ENTITY_WITHER_DEATH, 15f,0f);
                    player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 15f,0f);
                    plugin.storePlayerData(player.getUniqueId(), "PlayerHasApoth", true);
                    player.sendMessage(Component.text(player.getName()+" got a +1 +1 +1 +1 +1 +1!", TextColor.fromHexString("#00ffea"), TextDecoration.BOLD));
                    plugin.storePlayerData(player.getUniqueId(), "ApothPoints", (CurrentSuns + 5) + CurrentApothLevel);
                    plugin.storePlayerData(player.getUniqueId(), "ApothLevel", CurrentApothLevel + 1);
                }
            }
        }
    }
}


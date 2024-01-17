package me.gurasic.incremental;

import me.gurasic.incremental.GUIs.ApothGUI.ApothTransListener;
import me.gurasic.incremental.GUIs.ApothGUI.Farming.FarmingEntry;
import me.gurasic.incremental.GUIs.ApothGUI.Farming.ListenePlant;
import me.gurasic.incremental.GUIs.ApothGUI.Farming.Plant;
import me.gurasic.incremental.GUIs.ApothGUI.Farming.Stage;
import me.gurasic.incremental.GUIs.PrestigeGUI.PrestigeGUI;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic.Hand_Damage;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic.Steak_Listener;
import me.gurasic.incremental.Listener.Clicker;
import me.gurasic.incremental.Listener.DeathListener;
import me.gurasic.incremental.Listener.PlayerJoin;
import me.gurasic.incremental.Listener.Upgrader;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public final class Incremental extends JavaPlugin {
    private static Incremental instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getConsoleSender().sendMessage("The Grind Never Ends");
        File folder = new File(getDataFolder(), "FarmingData");
        if (!folder.exists()) {
            folder.mkdir();
        }
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new Clicker(this), this);
        getServer().getPluginManager().registerEvents(new Upgrader(this), this);
        getServer().getPluginManager().registerEvents(new PrestigeGUI(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new Steak_Listener(this), this);
        getServer().getPluginManager().registerEvents(new Hand_Damage(this), this);
        getServer().getPluginManager().registerEvents(new ApothTransListener(this), this);
        getServer().getPluginManager().registerEvents(new FarmingEntry(), this);
        getServer().getPluginManager().registerEvents(new ListenePlant(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Incremental getInstance() {
        return instance;
    }
    public void createPlayerFile(UUID uuid) {
        File playerFile = new File(getDataFolder(), uuid.toString() + ".yml");
        if (!playerFile.exists()) {
            try {
                // Ensure the directory exists
                playerFile.getParentFile().mkdirs();
                // Then create the file
                playerFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("ERROR creating player file! See stacktrace for more details:");
                e.printStackTrace();
            }
        }
    }

    public void storePlayerData(UUID uuid, String key, Object value) {
        File playerFile = new File(getDataFolder(), uuid.toString() + ".yml");
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

    public Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File(getDataFolder(), uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
    public void giveRainbowArmor(Player player) {
        // Create a new ItemStack for each piece of leather armor
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        // Create a BukkitRunnable to change the color of the armor every second
        new BukkitRunnable() {
            double hue = 0;

            @Override
            public void run() {
                // Calculate the RGB values of the color based on the hue
                int red = (int) (Math.sin(hue) * 127 + 128);
                int green = (int) (Math.sin(hue + 2 * Math.PI / 3) * 127 + 128);
                int blue = (int) (Math.sin(hue + 4 * Math.PI / 3) * 127 + 128);

                // Get the ItemMeta for each piece of armor
                LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

                // Set the color of each piece of armor
                helmetMeta.setColor(Color.fromRGB(red, green, blue));
                chestplateMeta.setColor(Color.fromRGB(red, green, blue));
                leggingsMeta.setColor(Color.fromRGB(red, green, blue));
                bootsMeta.setColor(Color.fromRGB(red, green, blue));

                // Set the ItemMeta of each piece of armor
                helmet.setItemMeta(helmetMeta);
                chestplate.setItemMeta(chestplateMeta);
                leggings.setItemMeta(leggingsMeta);
                boots.setItemMeta(bootsMeta);

                // Give the player the armor
                player.getInventory().setHelmet(helmet);
                player.getInventory().setChestplate(chestplate);
                player.getInventory().setLeggings(leggings);
                player.getInventory().setBoots(boots);

                // Increment the hue
                hue += 0.05;
                if (hue > 2 * Math.PI) {
                    hue = 0;
                }
            }
        }.runTaskTimer(this, 0, 2);  // Run every second (20 ticks = 1 second)
    }

    public void plantSeed(Location location, Plant plant, int growTimeInSeconds) {
        if (!this.isEnabled()) {
            return;
        }

        Location nameStandLocation = location.clone().add(0, 1.5, 0);
        Location timeStandLocation = location.clone().add(0, 1.1, 0);
        Location blockLocation = location.clone();  // No need to add 2 to the y-coordinate

        ArmorStand nameStand = spawnText(nameStandLocation, Component.text(plant.getName(), NamedTextColor.GREEN));
        ArmorStand timeStand = spawnText(timeStandLocation, Component.text("Time left: " + formatTime(growTimeInSeconds), NamedTextColor.YELLOW));

        // Start a timer
        new BukkitRunnable() {
            int timeLeft = growTimeInSeconds;  // Time left in seconds;
            @Override
            public void run() {
                if (timeLeft <= 0) {
                    timeStand.customName(Component.text("Fully Grown!!!", NamedTextColor.GOLD));
                    cancel();
                } else {
                    // Update the time left
                    timeStand.customName(Component.text("Time left: " + formatTime(timeLeft), NamedTextColor.YELLOW));
                    Stage currentStage = plant.getStageForTime(timeLeft);
                    if (currentStage != null) {
                        // Update the plant's growth stage
                        nameStand.customName(Component.text(plant.getName() + " (" + currentStage.getName() + ")", NamedTextColor.GREEN));
                        blockLocation.getBlock().setType(currentStage.getMaterial());  // Update the block to the current stage
                    }
                    timeLeft--;
                }
            }
        }.runTaskTimer(this, 0L, 20L);  // 20 ticks = 1 second
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
}

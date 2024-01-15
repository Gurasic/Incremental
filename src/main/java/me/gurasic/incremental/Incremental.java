package me.gurasic.incremental;

import me.gurasic.incremental.GUIs.ApothGUI.ApothTransListener;
import me.gurasic.incremental.GUIs.PrestigeGUI.PrestigeGUI;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic.Hand_Damage;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic.Steak_Listener;
import me.gurasic.incremental.Listener.Clicker;
import me.gurasic.incremental.Listener.DeathListener;
import me.gurasic.incremental.Listener.PlayerJoin;
import me.gurasic.incremental.Listener.Upgrader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class Incremental extends JavaPlugin {
    private static Incremental instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getConsoleSender().sendMessage("The Grind Never Ends");
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new Clicker(this), this);
        getServer().getPluginManager().registerEvents(new Upgrader(this), this);
        getServer().getPluginManager().registerEvents(new PrestigeGUI(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new Steak_Listener(this), this);
        getServer().getPluginManager().registerEvents(new Hand_Damage(this), this);
        getServer().getPluginManager().registerEvents(new ApothTransListener(this), this);
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
}

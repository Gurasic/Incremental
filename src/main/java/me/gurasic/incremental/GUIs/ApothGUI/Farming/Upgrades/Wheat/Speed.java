package me.gurasic.incremental.GUIs.ApothGUI.Farming.Upgrades.Wheat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Speed extends AbstractItem {
    private UUID playerUUID;

    public Speed(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.FEATHER).setDisplayName("§bPlant Speed §8| §c"+accessPlayerDataF(playerUUID, "WheatSpeed")+"/99")
                .addLoreLines("§7Increases The Speed of the plant")
                .addLoreLines("§7By 1% with each level")
                .addLoreLines("§7- - -")
                .addLoreLines("§7Cost: §6"+ accessPlayerDataF(playerUUID, "WheatSpeedCost") +"◎§7");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int Points = (int) accessPlayerDataF(player.getUniqueId(), "Wheat");
        int level = (int) accessPlayerDataF(player.getUniqueId(), "WheatSpeed") + 1;
        int Cost = (int) Math.round(upgradeCost(level, (int) accessPlayerDataF(player.getUniqueId(), "WheatSpeedCost")));
        storePlayerDataF(player.getUniqueId(), "WheatSpeedCost", Cost);
        if (Points >= Cost) {
            storePlayerDataF(player.getUniqueId(), "WheatSpeed", level);
            storePlayerDataF(player.getUniqueId(), "Wheat", Points - Cost );
            level = (int) accessPlayerDataF(player.getUniqueId(), "WheatSpeed") + 1;
            Cost = (int) Math.round(upgradeCost(level, (int) accessPlayerDataF(player.getUniqueId(), "WheatSpeedCost")));
            storePlayerDataF(player.getUniqueId(), "WheatSpeedCost", Cost);
            Wheat.window.changeTitle("Wheat Upgrades | " + accessPlayerDataF(player.getUniqueId(), "Wheat") + "◎");
        }
      notifyWindows();
    }
    public static double upgradeCost(int level, int baseCost) {
        if (level == 1) {
            baseCost = 4;
        }
        double linearFactor = 1.2;
        double exponentialFactor = 1.01;
        return baseCost + linearFactor * level * Math.pow(exponentialFactor, level);
    }
    private Object accessPlayerDataF(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
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
}

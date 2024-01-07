package me.gurasic.incremental.GUIs.PrestigeGUI.LuckUpgrade;

import me.gurasic.incremental.GUIs.PrestigeGUI.ClickerUpgrade.ClickerUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.PrestigeShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

public class LuckUpgradeButton extends AbstractItem {

    private UUID playerUUID;

    public LuckUpgradeButton(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.MAGENTA_GLAZED_TERRACOTTA).setDisplayName("§aCost "+ accessPlayerData(playerUUID, "LuckPrestigeCost") + "★");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        int Level = (int) accessPlayerData(player.getUniqueId(),"LuckPrestigeLevel");
        int PrestigePoints = (int) accessPlayerData(player.getUniqueId(),"prestigePoints");
        int Cost = (Level + 1) * 50;
        storePlayerData(player.getUniqueId(), "LuckPrestigeCost",  Cost);
        if (clickType.isLeftClick()) {
           if (PrestigePoints >= Cost && Level != 40) {
               storePlayerData(player.getUniqueId(), "LuckPrestigeLevel", Level + 1);
               storePlayerData(player.getUniqueId(), "prestigePoints", PrestigePoints - Cost);
               Cost = (Level + 1) * 50;
               storePlayerData(player.getUniqueId(), "LuckPrestigeCost",  Cost);
               LuckUpgradeItem.window.changeTitle("Prestige Shop | " + accessPlayerData(player.getUniqueId(), "prestigePoints") + "★");
               PrestigeShopItem.window.changeTitle("Prestige Shop | " + accessPlayerData(player.getUniqueId(), "prestigePoints") + "★");
               player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f, 0f);
           }
        }
        notifyWindows(); // this will update the ItemStack that is displayed to the player
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

    public Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }

}

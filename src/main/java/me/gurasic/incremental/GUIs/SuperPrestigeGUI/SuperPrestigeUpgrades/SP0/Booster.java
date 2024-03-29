package me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP0;

import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Booster extends AbstractItem {

    private UUID playerUUID;

    public Booster(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "Booster")) {
            return new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                    .setDisplayName("§eBooster §61☽")
                    .addLoreLines("§7Get more levels from §6+1 +1s")
                    .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    .addEnchantment(Enchantment.LURE, 1, true);
        } else {
            return new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                    .setDisplayName("§eBooster §61☽")
                    .addLoreLines("§7Get more levels from §6+1 +1s")
                    .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int SP =  (int) accessPlayerData(playerUUID, "SuperPrestigePoints");
        int PlayerKillBoost =  (int) accessPlayerData(playerUUID, "IncLvs");
        if (clickType.isLeftClick()) {
            if (SP >= 1) {
                storePlayerData(playerUUID, "Booster", true);
                storePlayerData(playerUUID, "SuperPrestigePoints", SP - 1);
                storePlayerData(playerUUID, "IncLvs", PlayerKillBoost + 10);
                SuperPrestigeItem.window.changeTitle("Super Prestige Shop | " + accessPlayerData(player.getUniqueId(), "SuperPrestigePoints") + "☽");
                player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f,0f);
                player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 10f,0f);
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
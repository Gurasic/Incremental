package me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP7;

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
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Clicking_Legend extends AbstractItem {

    private UUID playerUUID;

    public Clicking_Legend(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "Clicking_Legend")) {
            return new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                    .setDisplayName("§eClicking Legend §610☽")
                    .addLoreLines("§7Your Clicker givex §a2x§7 the")
                    .addLoreLines("§a+1§7 making it §a4x§7 ")
                    .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    .setAmount(8)
                    .addEnchantment(Enchantment.LURE, 1, true);
        } else {
            return new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                    .setDisplayName("§eClicking Legend §610☽")
                    .addLoreLines("§7Your Clicker givex §a2x§7 the")
                    .addLoreLines("§a+1§7 making it §a4x§7 ")
                    .setAmount(8)
                    .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int SP =  (int) accessPlayerData(playerUUID, "SuperPrestigePoints");
        if (clickType.isLeftClick()) {
            if (SP >= 10 && (boolean) accessPlayerData(playerUUID, "Adrenaline")) {
                storePlayerData(playerUUID, "Clicking_Legend", true);
                storePlayerData(playerUUID, "SuperPrestigePoints", SP - 10);
                SuperPrestigeItem.window.changeTitle("Super Prestige Shop | " + accessPlayerData(player.getUniqueId(), "SuperPrestigePoints") + "☽");
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
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
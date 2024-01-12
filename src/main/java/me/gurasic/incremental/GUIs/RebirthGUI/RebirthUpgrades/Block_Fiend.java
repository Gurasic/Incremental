package me.gurasic.incremental.GUIs.RebirthGUI.RebirthUpgrades;

import me.gurasic.incremental.GUIs.RebirthGUI.RebirthGUIItem;
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

public class Block_Fiend extends AbstractItem {
    private UUID playerUUID;

    public Block_Fiend(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "Block_Fiend")) {
        return new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§3Block Fiend §8| §c10♦")
                .addLoreLines("§eGold§7 and §bDiamond§7 blocks give")
                .addLoreLines("§a3x§7 the §a+1§7, they also")
                .addLoreLines("§7give up to §a5x§7 incrementally")
                .addLoreLines("§7the longer you use them")
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .addEnchantment(Enchantment.LURE, 1, true);
        }
        else {
            return new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§3Block Fiend §8| §c10♦")
                    .addLoreLines("§eGold§7 and §bDiamond§7 blocks give")
                    .addLoreLines("§a3x§7 the §a+1§7, they also")
                    .addLoreLines("§7give up to §a5x§7 incrementally")
                    .addLoreLines("§7the longer you use them");
        }
    }


    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int RebirthPoints = (int) accessPlayerData(player.getUniqueId(), "Rebirth_Points");
        int Cost = 10;
        if (clickType.isLeftClick()) {
            if (RebirthPoints >= Cost) {
                storePlayerData(player.getUniqueId(), "Rebirth_Points", RebirthPoints - Cost);
                storePlayerData(player.getUniqueId(), "Block_Fiend", true);
                player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f,0f);
                player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 10f,0f);
                RebirthGUIItem.window.changeTitle("Rebirth Shop | " + accessPlayerData(player.getUniqueId(), "Rebirth_Points") + "♦");
            }
        }
        notifyWindows(); // this will update the ItemStack that is displayed to the player
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

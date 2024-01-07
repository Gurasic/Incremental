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

public class Brightest_Night extends AbstractItem {
    private UUID playerUUID;

    public Brightest_Night(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "Brightest_Night")) {
        return new ItemBuilder(Material.SNOWBALL).setDisplayName("Brightest Night §8| §c10♦")
                .addLoreLines("§7Upon Superprestige you get §a2x§7 the §d☽§7 and")
                .addLoreLines("§7upon prestige you get §a+10§7 and 2x the §d★")
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .addEnchantment(Enchantment.LURE, 1, true);
        }
        else {
            return new ItemBuilder(Material.SNOWBALL).setDisplayName("Brightest Night §8| §c10♦")
                    .addLoreLines("§7Upon Superprestige you get §a2x§7 the §d☽§7 and")
                    .addLoreLines("§7upon prestige you get §a+10§7 and 2x the §d★");
        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int RebirthPoints = (int) accessPlayerData(player.getUniqueId(), "Rebirth_Points");
        int Cost = 10;
        if (clickType.isLeftClick()) {
            if (RebirthPoints >= Cost) {
                storePlayerData(player.getUniqueId(), "Rebirth_Points", RebirthPoints - Cost);
                storePlayerData(player.getUniqueId(), "Brightest_Night", true);
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

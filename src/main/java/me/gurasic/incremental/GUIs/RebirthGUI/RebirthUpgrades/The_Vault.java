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

public class The_Vault extends AbstractItem {
    private UUID playerUUID;

    public The_Vault(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "Speedrunner")) {
        return new ItemBuilder(Material.CLOCK).setDisplayName("§eSpeedrunner §8| §c10♦")
                .addLoreLines("§7You get an extra superprestige, per superprestige")
                .addLoreLines("§7But no moons from it")
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .addEnchantment(Enchantment.LURE, 1, true);
        }
        else {
            return new ItemBuilder(Material.CLOCK).setDisplayName("§eSpeedrunner §8| §c10♦")
                    .addLoreLines("§7You get an extra superprestige, per superprestige")
                    .addLoreLines("§7But no moons from it");
        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        int RebirthPoints = (int) accessPlayerData(player.getUniqueId(), "Rebirth_Points");
        int Cost = 10;
        if (clickType.isLeftClick()) {
            if (RebirthPoints >= Cost) {
                storePlayerData(player.getUniqueId(), "Rebirth_Points", RebirthPoints - Cost);
                storePlayerData(player.getUniqueId(), "Speedrunner", true);
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

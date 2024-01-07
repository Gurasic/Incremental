package me.gurasic.incremental.GUIs.RebirthGUI;

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

public class AnimationToggleRB extends AbstractItem {
    private UUID playerUUID;

    public AnimationToggleRB(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        if ((boolean) accessPlayerData(playerUUID, "RebirthAnimation")) {
            return new ItemBuilder(Material.LIME_DYE).setDisplayName("§cRebirth §fAnimation Toggle")
                    .addLoreLines("§7Toggles the animation at rebirth")
                    .addLoreLines("§7Current state = §aON");
        }
        else {
            return new ItemBuilder(Material.RED_DYE).setDisplayName("§cRebirth §fAnimation Toggle")
                    .addLoreLines("§7Toggles the animation at rebirth")
                    .addLoreLines("§7Current state = §cOFF");
        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        if (clickType.isLeftClick()) {
            boolean currentState = (boolean) accessPlayerData(playerUUID, "RebirthAnimation");
            storePlayerData(player.getUniqueId(), "RebirthAnimation", !currentState);
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


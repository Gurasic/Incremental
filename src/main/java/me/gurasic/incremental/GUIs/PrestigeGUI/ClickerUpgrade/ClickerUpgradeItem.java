package me.gurasic.incremental.GUIs.PrestigeGUI;

import me.gurasic.incremental.Incremental;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.window.Window;

import java.io.File;
import java.util.UUID;

public class ClickerUpgradeItem extends AbstractItem {

    public ItemProvider GrayGlass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
            .setDisplayName(" ");
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.LIME_DYE)
                .setDisplayName("§aClicker")
                .addLoreLines("§7Upgrades the output of your clicker")
                .addLoreLines("§8+3 per level");
    }
    Gui ClickerGUI = Gui.normal() // Creates the GuiBuilder for a normal GUI
            .setStructure(
                    "1 1 1 1 1 1 1 1 1",
                    "1 1 1 1 1 1 1 1 1",
                    "1 1 1 1 1 1 1 1 1")
            .addIngredient('1', GrayGlass)
            .build();
    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        Window window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Shop | " + accessPlayerData(player.getUniqueId(), "prestigePoints") + "★")
                .setGui(ClickerGUI)
                .build();
        if (clickType.isLeftClick()) {
            window.open();
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

}


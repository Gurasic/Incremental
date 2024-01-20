package me.gurasic.incremental.GUIs.ApothGUI.Farming.Upgrades.Wheat;

import me.gurasic.incremental.GUIs.ChallengeMenu.ChallengeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
import me.gurasic.incremental.GUIs.RebirthGUI.AnimationToggleRB;
import me.gurasic.incremental.GUIs.RebirthGUI.RebirthUpgrades.*;
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

public class Wheat extends AbstractItem {
    ItemProvider GlassGray = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
    ItemProvider GlassDark = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ");
    public static Window window;
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.WHEAT).setDisplayName("§6Wheat Upgrades")
                .addLoreLines("§7Spend §6◎§7 on Wheat Upgrades");
    }


    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        Gui gui = Gui.normal()
                .setStructure(
                        "D # # # W # # # D",
                        "D D 1 . . . . D D",
                        "D # # # # # # # D")
                .addIngredient('#', GlassGray)
                .addIngredient('D', GlassDark)
                .addIngredient('1', new Value(player.getUniqueId()))
                .addIngredient('W', getItemProvider())
                .build();

        window = Window.single()
                .setViewer(player)
                .setTitle("Wheat Upgrades | " + accessPlayerDataF(player.getUniqueId(), "Wheat") + "◎")
                .setGui(gui)
                .build(player);

        if(clickType.isLeftClick()) {
            window.open();
        }
    }
    private Object accessPlayerDataF(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
}
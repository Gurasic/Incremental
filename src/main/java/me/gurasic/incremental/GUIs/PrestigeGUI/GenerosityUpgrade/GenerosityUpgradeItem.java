package me.gurasic.incremental.GUIs.PrestigeGUI.GenerosityUpgrade;

import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
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
import java.util.function.Supplier;

public class ClickerUpgradeItem extends AbstractItem {

    public ItemProvider GrayGlass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
            .setDisplayName(" ");
    public static Window window;
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.LIME_DYE)
                .setDisplayName("§aClicker")
                .addLoreLines("§7Upgrades the output of your clicker")
                .addLoreLines("§8+3 per level");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        Gui ClickerGUI = Gui.normal() // Creates the GuiBuilder for a normal GUI
                .setStructure(
                        "1 1 1 1 3 1 1 1 1",
                        "1 1 4 1 1 1 5 1 1",
                        "1 1 1 1 2 1 1 1 1")
                .addIngredient('1', GrayGlass)
                .addIngredient('2', new ReturnItem())
                .addIngredient('3', getItemProvider())
                .addIngredient('4', new ClickerUpgradeSign(1, new Supplier<ItemProvider>() {
                    @Override
                    public ItemProvider get() {
                        return new ItemBuilder(Material.OAK_SIGN).setDisplayName("§bLevel " +accessPlayerData(player.getUniqueId(), "ClickerPrestigeLevel")+"/500");
                    }
                }, player.getUniqueId()))
                .addIngredient('5', new ClickerUpgradeButton(player.getUniqueId()))
                .build();

        window = Window.single()
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


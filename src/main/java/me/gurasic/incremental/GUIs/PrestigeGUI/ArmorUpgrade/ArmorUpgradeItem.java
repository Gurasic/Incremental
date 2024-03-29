package me.gurasic.incremental.GUIs.PrestigeGUI.ArmorUpgrade;

import me.gurasic.incremental.GUIs.PrestigeGUI.ClickerUpgrade.ClickerUpgradeButton;
import me.gurasic.incremental.GUIs.PrestigeGUI.ClickerUpgrade.ClickerUpgradeSign;
import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.window.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class ArmorUpgradeItem extends AbstractItem {

    public ItemProvider GrayGlass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
            .setDisplayName(" ");
    public static Window window;
    public List<ItemFlag> hideflags = new ArrayList<>();

    @Override
    public ItemProvider getItemProvider() {
        hideflags.add(ItemFlag.HIDE_ATTRIBUTES);
        return new ItemBuilder(Material.IRON_CHESTPLATE)
                .setDisplayName("§6Items")
                .addLoreLines("§7Lets you get item upgrades earlier.")
                .addLoreLines("§8+1 per level")
                .setItemFlags(hideflags);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        Gui ClickerGUI = Gui.normal() // Creates the GuiBuilder for a normal GUI
                .setStructure(
                        "1 1 1 1 3 1 1 1 1",
                        "1 1 4 1 1 1 5 1 1",
                        "1 1 1 1 2 1 1 1 1")
                .addIngredient('1', GrayGlass)
                .addIngredient('2', new ReturnItem())
                .addIngredient('3', getItemProvider())
                .addIngredient('4', new ArmorUpgradeSign(1, new Supplier<ItemProvider>() {
                    @Override
                    public ItemProvider get() {
                        return new ItemBuilder(Material.OAK_SIGN).setDisplayName("§bLevel " +accessPlayerData(player.getUniqueId(), "ArmorPrestigeLevel")+"/210");
                    }
                }, player.getUniqueId()))
                .addIngredient('5', new ArmorUpgradeButton(player.getUniqueId()))
                .build();

        window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Shop | " + accessPlayerData(player.getUniqueId(), "prestigePoints") + "★")
                .setGui(ClickerGUI)
                .build(player);
        if (clickType.isLeftClick()) {
            window.open();
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

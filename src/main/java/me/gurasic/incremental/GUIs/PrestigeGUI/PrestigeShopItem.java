package me.gurasic.incremental.GUIs.PrestigeGUI;

import me.gurasic.incremental.GUIs.PrestigeGUI.ArmorUpgrade.ArmorUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.ClickerUpgrade.ClickerUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.DefenseUpgrade.DefenseUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.GenerosityUpgrade.GenerosityUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.LuckUpgrade.LuckUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.PricesUpgrade.PricesUpgradeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.WishingUpgrade.WishingUpgradeItem;
import me.gurasic.incremental.GUIs.ChallengeMenu.ChallengeItem;
import me.gurasic.incremental.GUIs.RebirthGUI.RebirthGUIItem;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem;
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

public class PrestigeShopItem extends AbstractItem {

    public ItemProvider BlackGlass = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
            .setDisplayName(" ");
    public ItemProvider GrayGlass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
            .setDisplayName(" ");
    public static Window window;
    ItemProvider sp2;
    Gui ShopGUI = Gui.normal() // Creates the GuiBuilder for a normal GUI
            .setStructure(
                    "1 1 1 2 R 2 1 1 1",
                    "2 3 4 5 6 7 8 9 2",
                    "1 1 1 S 2 C 1 1 1")
            .addIngredient('1', BlackGlass)
            .addIngredient('2', GrayGlass)
            .addIngredient('3', new ClickerUpgradeItem())
            .addIngredient('4', new ArmorUpgradeItem())
            .addIngredient('5', new GenerosityUpgradeItem())
            .addIngredient('6', new LuckUpgradeItem())
            .addIngredient('7', new DefenseUpgradeItem())
            .addIngredient('8', new PricesUpgradeItem())
            .addIngredient('9', new WishingUpgradeItem())
            .addIngredient('S', new SuperPrestigeItem())
            .addIngredient('C', new ChallengeItem())
            .addIngredient('R', new RebirthGUIItem())
            .build();
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.NETHER_STAR)
                .setDisplayName("§cPrestige Shop")
                .addLoreLines("§7Buy perks with Prestige Points");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        int sp = (int) accessPlayerData(player.getUniqueId(), "SuperPrestigeLevel");
         window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Shop | " + accessPlayerData(player.getUniqueId(), "prestigePoints") + "★")
                .setGui(ShopGUI)
                .build();
        if (clickType.isLeftClick()) {
            window.open();
        }

        notifyWindows();
    }

    private Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }

}


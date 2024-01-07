package me.gurasic.incremental.GUIs;

import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.xenondevs.inventoryaccess.component.ComponentWrapper;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Click;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PrestigeGUI extends BaseItem implements Listener {
    private Incremental plugin;

    public PrestigeGUI(Incremental plugin) {
        this.plugin = plugin;
    }
    public List<ItemFlag> hideflags = new ArrayList<>();
    public ItemProvider ClickerBonus() {
        return new ItemBuilder(Material.GOLDEN_APPLE)
                .setDisplayName("§dClicker Bonus")
                .addLoreLines("§7Each Prestige gives +1 to")
                .addLoreLines("§7Your Clicker");
    }
    public ItemProvider ArmorBonus() {
        hideflags.add(ItemFlag.HIDE_ATTRIBUTES);
        return new ItemBuilder(Material.IRON_CHESTPLATE)
                .setDisplayName("§6Armor Bonus")
                .addLoreLines("§7If you have Prestiged, you start")
                .addLoreLines("§7out with very basic armor")
                .setItemFlags(hideflags);

    }
    public ItemProvider SwordBonus() {
        hideflags.add(ItemFlag.HIDE_ATTRIBUTES);
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName("§cKill Bonus")
                .addLoreLines("§7For each Prestige, +1 +1s are more")
                .addLoreLines("§7common and give more clicker levels")
                .setItemFlags(hideflags);
    }

    public ItemProvider PrestigeShop() {
        return new ItemBuilder(Material.NETHER_STAR)
                .setDisplayName("§cPrestige Shop")
                .addLoreLines("§7Buy perks with Prestige Points");

    }
    @Override
    public ItemProvider getItemProvider() {
        return PrestigeShop();
    }
    public Gui gui = Gui.normal() // Creates the GuiBuilder for a normal GUI
            .setStructure(
                    ". . . . . . . . .",
                    ". 1 . 2 . 3 . 4 .",
                    ". . . . . . . . .")
            .addIngredient('1', ClickerBonus())
            .addIngredient('2', ArmorBonus())
            .addIngredient('3', SwordBonus())
            .addIngredient('4', PrestigeShop())
            .build();

    public Gui gui2 = Gui.normal() // Creates the GuiBuilder for a normal GUI
            .setStructure(
                    ". . . . . . . . .",
                    ". . . . . . . . .",
                    ". . . . . . . . .").build();

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        Window window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Shop")
                .setGui(gui2)
                .build();
        if (clickType.isLeftClick()) {
            window.open();
        }

        notifyWindows(); // this will update the ItemStack that is displayed to the player
    }
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Material blockMaterial = player.getLocation().getBlock().getRelative(0, -1, 0).getType();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Perks")
                .setGui(gui)
                .build();
        if (blockMaterial == Material.REDSTONE_BLOCK) {
            window.open();
        }
    }
}


























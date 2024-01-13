package me.gurasic.incremental.GUIs.PrestigeGUI;

import me.gurasic.incremental.Incremental;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.List;

public class PrestigeGUI implements Listener {
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
    public ItemProvider item = new ItemBuilder(Material.GOLDEN_APPLE)
            .setDisplayName("§dClicker Bonus")
            .addLoreLines("§7Each Prestige gives +1 to")
            .addLoreLines("§7Your Clicker");


    public Gui gui = Gui.normal() // Creates the GuiBuilder for a normal GUI
            .setStructure(
                    ". . . . . . . . .",
                    ". 1 . 2 . 3 . 4 .",
                    ". . . . . . . . .")
            .addIngredient('1', ClickerBonus())
            .addIngredient('2', ArmorBonus())
            .addIngredient('3', item)
            .addIngredient('4', new PrestigeShopItem())
            .build();

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Material blockMaterial = player.getLocation().getBlock().getRelative(0, -1, 0).getType();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("Prestige Perks")
                .setGui(gui)
                .build(player);

        if (blockMaterial == Material.REDSTONE_BLOCK) {
            window.open();
        }
    }
}


























package me.gurasic.incremental.GUIs.SuperPrestigeGUI;

import me.gurasic.incremental.Listener.HeadCache;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import xyz.xenondevs.invui.gui.ScrollGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.SkullBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.ScrollItem;

import java.util.UUID;

public class ScrollDownItem extends ScrollItem {

    public ScrollDownItem() {
        super(3);
    }
    HeadCache cache = new HeadCache();
    @Override
    public ItemProvider getItemProvider(ScrollGui<?> gui) {
        ItemBuilder builder = new ItemBuilder(cache.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzkxMmQ0NWIxYzc4Y2MyMjQ1MjcyM2VlNjZiYTJkMTU3NzdjYzI4ODU2OGQ2YzFiNjJhNTQ1YjI5YzcxODcifX19"));
        builder.setDisplayName("§7Scroll down");
        if (!gui.canScroll(3))
            builder.addLoreLines("§cYou can't scroll further down");

        return builder;
    }
    public static ItemStack createHead(String playerName) {
        // Get the player from the server
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);

        // Create a new ItemStack of type PLAYER_HEAD
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();

        // Set the owner of the skull
        skullMeta.setOwningPlayer(player);
        head.setItemMeta(skullMeta);

        return head;
    }

}
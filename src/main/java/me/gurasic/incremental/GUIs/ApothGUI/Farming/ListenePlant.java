package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import me.gurasic.incremental.Incremental;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class ListenePlant implements Listener {
    private Incremental plugin;

    public ListenePlant(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Plant plant = new Plant("Wheat", Arrays.asList(
                new Stage("Mature", 0, 10, Material.GREEN_CONCRETE),
                new Stage("Sprout", 11, 20, Material.GREEN_CONCRETE_POWDER),
                new Stage("Seed", 21, 30, Material.GREEN_WOOL)
        ));

        if (item != null && item.getType() == Material.WHEAT_SEEDS) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block.getType() == Material.FARMLAND) {
                    plugin.plantSeed(block.getLocation().add(0.5,1.5,0.5), plant, 30);
                }
            }
        }
    }
}

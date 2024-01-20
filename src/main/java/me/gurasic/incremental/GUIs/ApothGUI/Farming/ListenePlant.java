package me.gurasic.incremental.GUIs.ApothGUI.Farming;

import me.gurasic.incremental.Incremental;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ListenePlant implements Listener {
    private Incremental plugin;

    private List<Plant> plants;

    public ListenePlant(Incremental plugin) {
        this.plugin = plugin;
        this.plants = new ArrayList<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        plants.add(new Plant("Wheat", Arrays.asList(
                new Stage("Mature", 0, 10, Material.GREEN_CONCRETE),
                new Stage("Sprout", 11, 20, Material.GREEN_CONCRETE_POWDER),
                new Stage("Seed", 21, 30, Material.GREEN_WOOL)
        )));

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
             if (item != null && item.getType() == Material.WHEAT_SEEDS) {
                if (block.getType() == Material.FARMLAND) {
                    plugin.plantSeed(block.getLocation().add(0.5,1.5,0.5), plants.get(0), 30);
                }
            }
            for (Plant plant : plants) {
                if (block.getType() == plant.getStages().get(0).getMaterial() && plant.getFinished()) {
                    plant.GetLocation().getBlock().setType(Material.AIR);
                    plant.GetLocation().add(0,-1,0).getBlock().setType(Material.FARMLAND);
                    plant.GetLocation().add(0,1,0);
                    plant.getArmorStand(0).remove();
                    plant.getArmorStand(1).remove();
                    plant.getArmorStands().clear();
                    storePlayerDataF(player.getUniqueId(), plant.getName(),3);
                }
            }
        }
    }
    public void storePlayerDataF(UUID uuid, String key, Object value) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        try {
            // Store data
            playerData.set(key, value);
            playerData.save(playerFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("ERROR storing Farming data! See stacktrace for more details:");
            e.printStackTrace();
        }
    }
}

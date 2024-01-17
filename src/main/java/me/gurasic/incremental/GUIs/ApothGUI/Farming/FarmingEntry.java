package me.gurasic.incremental.GUIs.ApothGUI.Farming;


import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class FarmingEntry implements Listener {

    private HashMap<Player, Long> shiftingPlayers = new HashMap<>();

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (event.isSneaking()) {
            shiftingPlayers.put(player, System.currentTimeMillis());
        } else {
            shiftingPlayers.remove(player);
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ArrayList<ArrayList<ArrayList<String>>> island = new ArrayList<>();
// Layer 0
        ArrayList<ArrayList<String>> layer0 = new ArrayList<>();
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:snow", "minecraft:snow", "minecraft:snow", "minecraft:snow", "minecraft:snow", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:spruce_planks", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:stripped_spruce_wood", "minecraft:cobblestone", "minecraft:spruce_planks", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:spruce_planks", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:snow")));
        layer0.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_stairs[EAST]", "minecraft:spruce_stairs[NORTH]", "minecraft:spruce_stairs[WEST]", "minecraft:snow", "minecraft:snow")));
        island.add(layer0);
// Layer 1
        ArrayList<ArrayList<String>> layer1 = new ArrayList<>();
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_slab")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:stripped_spruce_wood", "minecraft:furnace", "minecraft:air", "minecraft:blue_bed", "minecraft:stripped_spruce_wood", "minecraft:spruce_slab")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:air", "minecraft:air", "minecraft:blue_bed", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:stripped_spruce_wood", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:stripped_spruce_wood", "minecraft:spruce_slab")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_door", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer1.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_slab")));
        island.add(layer1);
// Layer 2
        ArrayList<ArrayList<String>> layer2 = new ArrayList<>();
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_slab", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:cobblestone_wall", "minecraft:air", "minecraft:air", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_door", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks")));
        layer2.add(new ArrayList<>(Arrays.asList("minecraft:spruce_planks", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_planks")));
        island.add(layer2);
// Layer 3
        ArrayList<ArrayList<String>> layer3 = new ArrayList<>();
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:glass_pane", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:snow")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:cobblestone_wall", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:glass_pane", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:glass_pane", "minecraft:snow")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:snow", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:snow")));
        layer3.add(new ArrayList<>(Arrays.asList("minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air", "minecraft:wall_torch", "minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab")));
        island.add(layer3);
// Layer 4
        ArrayList<ArrayList<String>> layer4 = new ArrayList<>();
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:air", "minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:cobblestone_wall", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:air", "minecraft:air", "minecraft:air", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:air")));
        layer4.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:air", "minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air")));
        island.add(layer4);
// Layer 5
        ArrayList<ArrayList<String>> layer5 = new ArrayList<>();
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:cobblestone", "minecraft:wall_torch", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:air", "minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:wall_torch", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:stripped_spruce_wood", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        layer5.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:spruce_slab", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air")));
        island.add(layer5);
// Layer 6
        ArrayList<ArrayList<String>> layer6 = new ArrayList<>();
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:cobblestone_wall", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        layer6.add(new ArrayList<>(Arrays.asList("minecraft:air", "minecraft:air", "minecraft:snow", "minecraft:spruce_planks", "minecraft:snow", "minecraft:air", "minecraft:air")));
        island.add(layer6);



        int x = (int) accessPlayerDataC("x");
        int y = (int) accessPlayerDataC("y");
        int z = (int) accessPlayerDataC("z");

        String worldName = player.getName() + "_world";
        String templateWorldName = "Farm";
        if (shiftingPlayers.containsKey(player)) {
            long shiftStartTime = shiftingPlayers.get(player);
            if (System.currentTimeMillis() - shiftStartTime >= 3000) {
                Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                if (block.getType() == Material.BLACK_CONCRETE) {
                    if(accessPlayerDataF(player.getUniqueId(), "x") == null) {
                        storePlayerDataF(player.getUniqueId(), "x", x);
                        storePlayerDataF(player.getUniqueId(), "y", y);
                        storePlayerDataF(player.getUniqueId(), "z", z);
                        storePlayerDataC("x", x + 1000);
                        new CustomStructure(island, player.getWorld(), x, y, z, event.getPlayer()).generate();
                        Location loc = new Location(player.getWorld(), x,y+2,z);
                        player.teleport(loc);
                    }

                }

            }
        }
    }

    private Object accessPlayerDataF(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
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
    private Object accessPlayerDataC(String key) {
        File playerFile = new File("plugins/Incremental", "ImportantData.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
    public void storePlayerDataC( String key, Object value) {
        File playerFile = new File("plugins/Incremental", "ImportantData.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
        try {
            // Store data
            playerData.set(key, value);
            playerData.save(playerFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("ERROR storing Important data! See stacktrace for more details:");
            e.printStackTrace();
        }
    }

}

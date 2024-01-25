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

import java.io.*;
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
    public void onPlayerMove(PlayerMoveEvent event) throws IOException {
        Player player = event.getPlayer();
        ArrayList<ArrayList<ArrayList<String>>> structure = readStructureFromFile("/Structures/Island.txt");
        int x = (int) accessPlayerDataC("x");
        int y = (int) accessPlayerDataC("y");
        int z = (int) accessPlayerDataC("z");

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
                        new CustomStructure(structure, player.getWorld(), x, y, z, event.getPlayer()).generate();
                        Location loc = new Location(player.getWorld(), x,y+2,z);
                        player.teleport(loc);
                    }

                }

            }
        }
    }
    public ArrayList<ArrayList<ArrayList<String>>> readStructureFromFile(String filename) throws IOException {
        ArrayList<ArrayList<ArrayList<String>>> structure = new ArrayList<>();
        InputStream in = getClass().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            ArrayList<ArrayList<String>> layer = new ArrayList<>();
            String[] rows = line.split(";");
            for (String row : rows) {
                ArrayList<String> blocks = new ArrayList<>(Arrays.asList(row.split("\\|")));
                layer.add(blocks);
            }
            structure.add(layer);
        }
        reader.close();
        return structure;
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

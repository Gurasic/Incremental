package me.gurasic.incremental.GUIs.ApothGUI.Farming;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.gurasic.incremental.Listener.HeadCache;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;


public class CustomStructure {
    private final ArrayList<ArrayList<ArrayList<String>>> structure;
    private final World world;
    private final int startX, startY, startZ;
    Player player;

    public CustomStructure(ArrayList<ArrayList<ArrayList<String>>> structure, World world, int startX, int startY, int startZ, Player player) {
        this.structure = structure;
        this.world = world;
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.player = player;
    }

    public void generate() {
        for (int y = 0; y < structure.size(); y++) {
            for (int x = 0; x < structure.get(y).size(); x++) {
                for (int z = 0; z < structure.get(y).get(x).size(); z++) {
                    String blockType = structure.get(y).get(x).get(z);
                    Block block = world.getBlockAt(startX + x, startY + y, startZ + z);
                    if (blockType.startsWith("item_frame")) {
                        String item = blockType.substring(blockType.indexOf("[") + 1, blockType.indexOf("]"));
                        String rotation = blockType.substring(blockType.lastIndexOf("[") + 1, blockType.lastIndexOf("]"));
                        ItemFrame itemFrame = (ItemFrame) world.spawnEntity(block.getLocation(), EntityType.ITEM_FRAME);
                        itemFrame.setItem(new ItemStack(Material.getMaterial(item.toUpperCase())));
                        itemFrame.setRotation(Rotation.valueOf(rotation.toUpperCase()));
                    } else if (blockType.contains("stairs")) {
                        String rotation = blockType.substring(blockType.indexOf("[") + 1, blockType.indexOf("]"));
                        Stairs stairs = (Stairs) Bukkit.createBlockData(blockType.substring(0, blockType.indexOf("[")));
                        stairs.setFacing(BlockFace.valueOf(rotation.toUpperCase()));
                        block.setBlockData(stairs);
                    } else {
                        BlockData blockData = Bukkit.createBlockData(blockType);
                        block.setBlockData(blockData);
                    }
                }
            }
        }
    }
}
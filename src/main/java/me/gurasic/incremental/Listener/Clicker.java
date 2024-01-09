package me.gurasic.incremental.Listener;
import me.gurasic.incremental.Gear;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Clicker implements Listener {
    private Incremental plugin;
    private boolean fpu = false;

    public Clicker(Incremental plugin) {
        this.plugin = plugin;
    }

    private HashMap<Material, UUID> blockPlayerMap = new HashMap<>();
    public ItemStack FastPass = new ItemStack(Material.PAPER);
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        Material blockMaterial = player.getLocation().getBlock().getRelative(0, -1, 0).getType();
        Object pointCount = plugin.accessPlayerData(playerId, "pointCount");
        BigInteger Points;

        if (pointCount instanceof Integer) {
            Points = BigInteger.valueOf(((Integer) pointCount).longValue());
        } else if (pointCount instanceof BigInteger) {
            Points = (BigInteger) pointCount;
        } else {
            Points = BigInteger.ZERO;
        }

        int Multi = (int) plugin.accessPlayerData(playerId, "multiCount");
        int ClickerPrestige = (int) plugin.accessPlayerData(playerId, "ClickerPrestigeLevel");
        int Level = (int) plugin.accessPlayerData(playerId, "playerLevel");
        int l = 1;
        int h = 1;
        int Ex = 1;
        if ((boolean) plugin.accessPlayerData(playerId, "Clicking_Expert")) {
            l = 2;
        }
        if ((boolean) plugin.accessPlayerData(playerId, "Clicking_Legend")) {
            l = 4;
        }
        if ((boolean) plugin.accessPlayerData(playerId, "Expertise")) {
            Ex = 5;
        }
        if ((boolean) plugin.accessPlayerData(playerId, "Healthy") && (boolean) plugin.accessPlayerData(playerId, "HealthyBool")) {
            AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (maxHealth != null) {
                maxHealth.setBaseValue(maxHealth.getBaseValue() + 4);
            }
            plugin.storePlayerData(player.getUniqueId(), "HealthyBool", false);
        }
        if ((boolean) plugin.accessPlayerData(playerId, "FleetFoot") && (boolean) plugin.accessPlayerData(playerId, "PlayerSpeedCapBool")) {
            float playerSpeed = player.getWalkSpeed();
            playerSpeed = playerSpeed += playerSpeed * (2f/10f);
            player.setWalkSpeed(playerSpeed);

            plugin.storePlayerData(player.getUniqueId(), "PlayerSpeedCapBool", false);
        }
        Multi = (Multi + (ClickerPrestige * 3) * l) * Ex; //e
        boolean hasGW = (boolean) plugin.accessPlayerData(playerId, "Good_Will");
        if (blockMaterial == Material.GOLD_BLOCK || blockMaterial == Material.DIAMOND_BLOCK) {
            UUID currentBlockPlayerId = blockPlayerMap.get(blockMaterial);
            if (!hasGW && currentBlockPlayerId != null && !currentBlockPlayerId.equals(playerId)) {
                return;
            }
            blockPlayerMap.put(blockMaterial, playerId);
            if (blockMaterial == Material.GOLD_BLOCK) {
                Multi = ((Multi+25) * 5) * h;
            } else if (blockMaterial == Material.DIAMOND_BLOCK) {
                Multi = ((Multi+10) * 2) * h;
            }
        }
        BigInteger x = BigInteger.valueOf(Multi);
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getItem() != null && event.getItem().getType() == Material.LIME_DYE) {
            if ((boolean) plugin.accessPlayerData(playerId, "Fast_Pass") && Level < 61 && !fpu) {
                ItemMeta FastpassMeta = FastPass.getItemMeta();
                FastpassMeta.displayName(Component.text("Fast pass", TextColor.color(255, 255, 85)));
                List<Component> FPlore = new ArrayList<>();
                FPlore.add(Component.text("Skip to level 61",TextColor.fromHexString("#969696")));
                FastpassMeta.lore(FPlore);
                FastPass.setItemMeta(FastpassMeta);
                player.getInventory().addItem(FastPass);
                fpu = true;
            }
            int playerCountOnBlock = getPlayerCountOnBlock(blockMaterial);
            int goodWillCountOnBlock = getGoodWillCountOnBlock(blockMaterial);
            if (playerCountOnBlock - goodWillCountOnBlock <= 1 || hasGW) {
                Points = Points.add(x);
                player.sendActionBar(Component.text("[You Gained +"+ Multi +"]", TextColor.fromHexString("#636363")));
                plugin.storePlayerData(playerId, "pointCount", Points);
                player.playSound(player, Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 10f,0f);
            }
        }
        int level = 61;
        level = level - Level;
        Gear gear = new Gear();
        gear.Food.setAmount(64);
        gear.arrow.setAmount(5);
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(6);
        ItemMeta bow2meta = gear.bow2.getItemMeta();
        bow2meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        gear.bow2.setItemMeta(bow2meta);
        ItemMeta bow3meta = gear.bow3.getItemMeta();
        bow3meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        gear.bow3.setItemMeta(bow3meta);
        ItemMeta bow4meta = gear.bow4.getItemMeta();
        bow4meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
        gear.bow4.setItemMeta(bow4meta);
        ItemMeta bow5meta = gear.bow5.getItemMeta();
        bow5meta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
        gear.bow5.setItemMeta(bow5meta);
        ItemMeta charmMeta = gear.charm.getItemMeta();
        int defenselevel = (int) plugin.accessPlayerData(player.getUniqueId(), "DefensePrestigeLevel");
        AttributeModifier armorModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.armor",
                defenselevel,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.OFF_HAND);
        charmMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifier);
        charmMeta.displayName(Component.text("Charm of defense (Level " + defenselevel + ")", TextColor.fromHexString("828282")));
        gear.charm.setItemMeta(charmMeta);
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getItem() != null && event.getItem().equals(FastPass)) {
            player.getInventory().removeItem(FastPass);
            fpu = false;
            for (int i = 0; i < level; i++) {
                int playerMulti = (int) plugin.accessPlayerData(player.getUniqueId(), "multiCount");
                int playerLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "playerLevel");
                int playerBeforeCost = (int) plugin.accessPlayerData(player.getUniqueId(), "beforeCost");
                int ArmorLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorLevel");

                int ArmorPrestigeUpgrade = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorPrestigeLevel");
                playerLevel = playerLevel + 1;
                playerMulti = playerMulti + 1;
                int lvl_rqm = playerBeforeCost + (int) Math.pow(playerLevel,2);
                plugin.storePlayerData(player.getUniqueId(), "multiCount", 61);
                plugin.storePlayerData(player.getUniqueId(), "playerLevel", 61);
                plugin.storePlayerData(player.getUniqueId(), "beforeCost", lvl_rqm);
                if ((playerLevel + ArmorPrestigeUpgrade) % 30 == 0) {
                    plugin.storePlayerData(player.getUniqueId(), "ArmorLevel", ArmorLevel + 1);
                }
                ArmorLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorLevel");
                if (ArmorLevel == 0)
                {
                    player.getInventory().clear();
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "MilSword")) {
                        player.getInventory().addItem(gear.SwordMil);
                    }
                    else {
                        player.getInventory().setItem(0, gear.Sword1);
                    }
                    player.getInventory().setItem(7, gear.clicker);
                    player.getInventory().setItem(8, gear.upgrader);
                    player.getInventory().addItem(gear.Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(gear.Axe1);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, gear.charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(gear.bow2);
                            player.getInventory().addItem(gear.arrow);
                        }
                        else {
                            player.getInventory().addItem(gear.bow1);
                            player.getInventory().addItem(gear.arrow);
                        }
                    }
                }
                else if (ArmorLevel == 1)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, gear.leather1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, gear.leather2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, gear.leather3);
                    player.getInventory().setItem(EquipmentSlot.FEET, gear.leather4);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "MilSword")) {
                        player.getInventory().addItem(gear.SwordMil);
                    }
                    else {
                        player.getInventory().setItem(0, gear.Sword2);
                    }
                    player.getInventory().setItem(7, gear.clicker);
                    player.getInventory().setItem(8, gear.upgrader);
                    player.getInventory().addItem(gear.Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(gear.Axe2);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, gear.charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(gear.bow2);
                            player.getInventory().addItem(gear.arrow);
                        }
                        else {
                            player.getInventory().addItem(gear.bow1);
                            player.getInventory().addItem(gear.arrow);
                        }
                    }
                }
                else if (ArmorLevel == 2)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, gear.chainmail1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, gear.chainmail2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, gear.chainmail3);
                    player.getInventory().setItem(EquipmentSlot.FEET, gear.chainmail4);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "MilSword")) {
                        player.getInventory().addItem(gear.SwordMil);
                    }
                    else {
                        player.getInventory().setItem(0, gear.Sword3);
                    }
                    player.getInventory().setItem(7, gear.clicker);
                    player.getInventory().setItem(8, gear.upgrader);
                    player.getInventory().addItem(gear.Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(gear.Axe3);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, gear.charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(gear.bow3);
                            player.getInventory().addItem(gear.arrow);
                        }
                        else {
                            player.getInventory().addItem(gear.bow2);
                            player.getInventory().addItem(gear.arrow);
                        }
                    }
                    player.getInventory().addItem(gear.Food);
                }
                else if (ArmorLevel == 3)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, gear.iron1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, gear.iron2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, gear.iron3);
                    player.getInventory().setItem(EquipmentSlot.FEET, gear.iron4);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "MilSword")) {
                        player.getInventory().addItem(gear.SwordMil);
                    }
                    else {
                        player.getInventory().setItem(0, gear.Sword4);
                    }
                    player.getInventory().setItem(7, gear.clicker);
                    player.getInventory().setItem(8, gear.upgrader);
                    player.getInventory().addItem(gear.Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(gear.Axe4);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, gear.charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(gear.bow3);
                            player.getInventory().addItem(gear.arrow);
                        }
                        else {
                            player.getInventory().addItem(gear.bow2);
                            player.getInventory().addItem(gear.arrow);
                        }
                    }
                }
            }
        }
    }

    // Method to get the count of players standing on a specific block
    public int getPlayerCountOnBlock(Material blockMaterial) {
        int count = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getLocation().getBlock().getRelative(0, -1, 0).getType() == blockMaterial) {
                count++;
            }
        }
        return count;
    }

    // Method to get the count of players with Good_Will standing on a specific block
    public int getGoodWillCountOnBlock(Material blockMaterial) {
        int count = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getLocation().getBlock().getRelative(0, -1, 0).getType() == blockMaterial && (boolean) plugin.accessPlayerData(p.getUniqueId(), "Good_Will")) {
                count++;
            }
        }
        return count;
    }
}
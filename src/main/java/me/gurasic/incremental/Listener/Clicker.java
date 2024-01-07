package me.gurasic.incremental.Listener;
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
        ItemStack Sword1 = new ItemStack(Material.WOODEN_SWORD);
        ItemStack Sword2 = new ItemStack(Material.STONE_SWORD);
        ItemStack Sword3 = new ItemStack(Material.IRON_SWORD);
        ItemStack Sword4 = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack Axe1 = new ItemStack(Material.WOODEN_AXE);
        ItemStack Axe2 = new ItemStack(Material.STONE_AXE);
        ItemStack Axe3 = new ItemStack(Material.IRON_AXE);
        ItemStack Axe4 = new ItemStack(Material.DIAMOND_AXE);
        ItemStack Food = new ItemStack(Material.COOKED_BEEF);
        Food.setAmount(64);
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(6);
        ItemStack clicker = new ItemStack(Material.LIME_DYE);
        ItemStack upgrader = new ItemStack(Material.EMERALD);
        ItemStack leather1 = new ItemStack(Material.LEATHER_HELMET);
        ItemStack leather2 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leather3 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack leather4 = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack chainmail1 = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack chainmail2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemStack chainmail3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemStack chainmail4 = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemStack iron1 = new ItemStack(Material.IRON_HELMET);
        ItemStack iron2 = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack iron3 = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack iron4 = new ItemStack(Material.IRON_BOOTS);
        ItemStack charm = new ItemStack(Material.IRON_INGOT);
        ItemStack bow1 = new ItemStack(Material.BOW);
        ItemStack bow2 = new ItemStack(Material.BOW);
        ItemMeta bow2meta = bow2.getItemMeta();
        bow2meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        bow2.setItemMeta(bow2meta);
        ItemStack bow3 = new ItemStack(Material.BOW);
        ItemMeta bow3meta = bow3.getItemMeta();
        bow3meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        bow3.setItemMeta(bow3meta);
        ItemStack bow4 = new ItemStack(Material.BOW);
        ItemMeta bow4meta = bow4.getItemMeta();
        bow4meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
        bow4.setItemMeta(bow4meta);
        ItemStack bow5 = new ItemStack(Material.BOW);
        ItemMeta bow5meta = bow5.getItemMeta();
        bow5meta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
        bow5.setItemMeta(bow5meta);
        ItemMeta charmMeta = charm.getItemMeta();
        int defenselevel = (int) plugin.accessPlayerData(player.getUniqueId(), "DefensePrestigeLevel");
        AttributeModifier armorModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.armor",
                defenselevel,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.OFF_HAND);
        charmMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifier);
        charmMeta.displayName(Component.text("Charm of defense (Level " + defenselevel + ")", TextColor.fromHexString("828282")));
        charm.setItemMeta(charmMeta);
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
                player.sendActionBar(Component.text("[ You Leveled Up To Level "+ 61 +"]", TextColor.fromHexString("#25de00")));
                if ((playerLevel + ArmorPrestigeUpgrade) % 30 == 0) {
                    plugin.storePlayerData(player.getUniqueId(), "ArmorLevel", ArmorLevel + 1);
                }
                ArmorLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorLevel");
                if (ArmorLevel == 0)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(0, Sword1);
                    player.getInventory().setItem(7, clicker);
                    player.getInventory().setItem(8, upgrader);
                    player.getInventory().addItem(Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(Axe1);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(bow2);
                            player.getInventory().addItem(arrow);
                        }
                        else {
                            player.getInventory().addItem(bow1);
                            player.getInventory().addItem(arrow);
                        }
                    }
                }
                else if (ArmorLevel == 1)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, leather1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, leather2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, leather3);
                    player.getInventory().setItem(EquipmentSlot.FEET, leather4);
                    player.getInventory().setItem(0, Sword2);
                    player.getInventory().setItem(7, clicker);
                    player.getInventory().setItem(8, upgrader);
                    player.getInventory().addItem(Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(Axe2);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(bow2);
                            player.getInventory().addItem(arrow);
                        }
                        else {
                            player.getInventory().addItem(bow1);
                            player.getInventory().addItem(arrow);
                        }
                    }
                }
                else if (ArmorLevel == 2)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, chainmail1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, chainmail2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, chainmail3); //Master_Ranger
                    player.getInventory().setItem(EquipmentSlot.FEET, chainmail4);
                    player.getInventory().setItem(0, Sword3);
                    player.getInventory().setItem(7, clicker);
                    player.getInventory().setItem(8, upgrader);
                    player.getInventory().addItem(Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(Axe3);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(bow3);
                            player.getInventory().addItem(arrow);
                        }
                        else {
                            player.getInventory().addItem(bow2);
                            player.getInventory().addItem(arrow);
                        }
                    }
                    player.getInventory().addItem(Food);
                }
                else if (ArmorLevel == 3)
                {
                    player.getInventory().clear();
                    player.getInventory().setItem(EquipmentSlot.HEAD, iron1);
                    player.getInventory().setItem(EquipmentSlot.CHEST, iron2);
                    player.getInventory().setItem(EquipmentSlot.LEGS, iron3);
                    player.getInventory().setItem(EquipmentSlot.FEET, iron4);
                    player.getInventory().setItem(0, Sword4);
                    player.getInventory().setItem(7, clicker);
                    player.getInventory().setItem(8, upgrader);
                    player.getInventory().addItem(Food);
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Crushing_Blows")) {
                        player.getInventory().addItem(Axe4);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "GiveDefenseCharm")) {
                        player.getInventory().setItem(EquipmentSlot.OFF_HAND, charm);
                    }
                    if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "RangerSP")) {
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Master_Ranger")) {
                            player.getInventory().addItem(bow3);
                            player.getInventory().addItem(arrow);
                        }
                        else {
                            player.getInventory().addItem(bow2);
                            player.getInventory().addItem(arrow);
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
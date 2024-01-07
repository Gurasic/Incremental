package me.gurasic.incremental.Listener;

import me.gurasic.incremental.GUIs.PrestigeGUI.PrestigeShopItem;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

public class Upgrader implements Listener {
    private Incremental plugin;

    public Upgrader(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        @NotNull
        int before_cost = (int) plugin.accessPlayerData(player.getUniqueId(), "beforeCost");
        int player_level = (int) plugin.accessPlayerData(player.getUniqueId(), "playerLevel");
        int Points = (int) plugin.accessPlayerData(player.getUniqueId(), "pointCount");
        int Multi = (int) plugin.accessPlayerData(player.getUniqueId(), "multiCount");
        int PrestigeCount = (int) plugin.accessPlayerData(player.getUniqueId(), "playerPrestige");
        int PrestigePoints = (int) plugin.accessPlayerData(player.getUniqueId(), "prestigePoints");
        int ArmorLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorLevel");
        int PricesLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "PricesPrestigeLevel");
        int ArmorPrestigeUpgrade = (int) plugin.accessPlayerData(player.getUniqueId(), "ArmorPrestigeLevel");
        int WishingLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "WishingPrestigeLevel");
        int SuperPrestigePoints = (int) plugin.accessPlayerData(player.getUniqueId(), "SuperPrestigePoints");
        int SuperPrestigeCount = (int) plugin.accessPlayerData(player.getUniqueId(), "SuperPrestigeLevel");
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
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getItem() != null && event.getItem().getType() == Material.EMERALD && event.getPlayer().isSneaking() && (boolean) plugin.accessPlayerData(player.getUniqueId(), "QuickBuy")) {
            int currentPlayerLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "playerLevel");
            int x = 240 - currentPlayerLevel;
            for(int i = 0; i < x; i++) {
                before_cost = (int) plugin.accessPlayerData(player.getUniqueId(), "beforeCost");
                currentPlayerLevel = (int) plugin.accessPlayerData(player.getUniqueId(), "playerLevel");
                int lvl_rqm = before_cost + (int) Math.pow(player_level,2);
                double PriceReduction = Math.ceil(lvl_rqm*Math.pow(0.99, PricesLevel));
                if (Points >= PriceReduction) {
                    player_level = player_level + 1;
                    Multi = Multi + 1;
                    plugin.storePlayerData(player.getUniqueId(), "pointCount", Points - lvl_rqm);
                    plugin.storePlayerData(player.getUniqueId(), "multiCount", Multi);
                    plugin.storePlayerData(player.getUniqueId(), "playerLevel", player_level);
                    plugin.storePlayerData(player.getUniqueId(), "beforeCost", lvl_rqm);
                    if (currentPlayerLevel == 240) {
                        int PP = 0;
                        int l = 0;
                        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "The_Perfect_Wish")) {
                            l = 25;
                        }
                        player.damage(5000);
                        plugin.storePlayerData(player.getUniqueId(), "pointCount", 0);
                        plugin.storePlayerData(player.getUniqueId(), "multiCount", 1);
                        plugin.storePlayerData(player.getUniqueId(), "playerLevel", 1);
                        plugin.storePlayerData(player.getUniqueId(), "beforeCost", 1);
                        PP = (PrestigeCount * 2) + (WishingLevel * 2) + 2 + l;
                        plugin.storePlayerData(player.getUniqueId(), "playerPrestige", PrestigeCount + 1);
                        plugin.storePlayerData(player.getUniqueId(), "prestigePoints", PrestigePoints + PP);
                        player.sendMessage(Component.text(player.getName()+" got a +1 +1 +1!", TextColor.fromHexString("#ff57c7"), TextDecoration.BOLD));
                        player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
                    }
                }
                else {
                    player.sendActionBar(Component.text("[ You Leveled Up To Level "+ player_level +"]", TextColor.fromHexString("#25de00")));
                    player.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER, 10f,0f);
                    break;
                }
            }
        }
        if (event.getAction().toString().contains("RIGHT_CLICK") && event.getItem() != null && event.getItem().getType() == Material.EMERALD) {
            int lvl_rqm = before_cost + (int) Math.pow(player_level,2);
            double PriceReduction = Math.ceil(lvl_rqm*Math.pow(0.99, PricesLevel));
            Random random = new Random();
            if (player_level >= 240) {
                int PP = 0;
                int l = 0;
                int PA = 0;
                int PAS = 1;
                int BN1 = 0;
                int BN2 = 1;
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "The_Perfect_Wish")) {
                    l = 25;
                }
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Hasty_Hands")) {
                    player.addPotionEffect(PotionEffectType.SPEED.createEffect(100,2));
                }
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Prestige_Accelerator")) {
                    PA = random.nextInt(0,3);
                    PAS = 2;
                }
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Brightest_Night")) {
                    BN1 = 10;
                    BN2 = 2;
                }
                player.damage(5000);
                plugin.storePlayerData(player.getUniqueId(), "pointCount", 0);
                plugin.storePlayerData(player.getUniqueId(), "multiCount", 1);
                plugin.storePlayerData(player.getUniqueId(), "playerLevel", 1);
                plugin.storePlayerData(player.getUniqueId(), "beforeCost", 5);
                PP = ((((PrestigeCount+BN1) * 2)  + WishingLevel * 2) + (2 + l) * PAS) * BN2;
                plugin.storePlayerData(player.getUniqueId(), "playerPrestige", PrestigeCount + 1 + PA);
                plugin.storePlayerData(player.getUniqueId(), "prestigePoints", PrestigePoints + PP);
                int Prestige = 1 + PA;
                player.sendMessage(Component.text(player.getName()+" got a +"+Prestige+" +1 +1!", TextColor.fromHexString("#ff57c7"), TextDecoration.BOLD));
                player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
            }
            else if (PrestigeCount >= 30)
            {
                int SPP = 0;
                int l = 0;
                int SDR = 0;
                int BN = 1;
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Lucky_day")) {
                    l = 2;
                }
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Speedrunner")) {
                    SDR = 1;
                }
                if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Brightest_Night")) {
                    BN = 2;
                }
                SuperPrestigeItem.HasSp = true;
                plugin.storePlayerData(player.getUniqueId(), "pointCount", 0);
                plugin.storePlayerData(player.getUniqueId(), "multiCount", 1);
                plugin.storePlayerData(player.getUniqueId(), "playerLevel", 1);
                plugin.storePlayerData(player.getUniqueId(), "beforeCost", 1);
                plugin.storePlayerData(player.getUniqueId(), "playerPrestige", 0);
                plugin.storePlayerData(player.getUniqueId(), "LuckPrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "GenerosityPrestigeCost", 3);
                plugin.storePlayerData(player.getUniqueId(), "DefensePrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "DefensePrestigeCost", 100);
                plugin.storePlayerData(player.getUniqueId(), "GiveDefenseCharm", false);
                plugin.storePlayerData(player.getUniqueId(), "PricesPrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "PricesPrestigeCost", 10);
                plugin.storePlayerData(player.getUniqueId(), "WishingPrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "ClickerPrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "ClickerPrestigeCost", 2);
                plugin.storePlayerData(player.getUniqueId(), "ArmorPrestigeLevel", 0);
                plugin.storePlayerData(player.getUniqueId(), "ArmorPrestigeCost", 3);
                SPP = ((SuperPrestigeCount * 2) + 2 + l) * BN;
                plugin.storePlayerData(player.getUniqueId(), "SuperPrestigeLevel", SuperPrestigeCount + (1 + SDR));
                plugin.storePlayerData(player.getUniqueId(), "SuperPrestigePoints", SPP);
                int SuperPrestige = 1 + SDR;
                player.sendMessage(Component.text(player.getName()+" got a +"+SuperPrestige+" +1 +1 +1!", TextColor.fromHexString("#a300a0"), TextDecoration.BOLD));
                player.playSound(player, Sound.ENTITY_ENDER_DRAGON_DEATH, 15f,0f);
                player.playSound(player, Sound.ENTITY_WITHER_HURT, 15f,0f);
            }
            else if (Points >= PriceReduction) {
                player_level = player_level + 1;
                Multi = Multi + 1;
                plugin.storePlayerData(player.getUniqueId(), "pointCount", Points - lvl_rqm);
                plugin.storePlayerData(player.getUniqueId(), "multiCount", Multi);
                plugin.storePlayerData(player.getUniqueId(), "playerLevel", player_level);
                plugin.storePlayerData(player.getUniqueId(), "beforeCost", lvl_rqm);
                player.sendActionBar(Component.text("[ You Leveled Up To Level "+ player_level +"]", TextColor.fromHexString("#25de00")));
                player.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER, 10f,0f);
            }
            else {
                int x = lvl_rqm - Points;
                player.sendMessage(Component.text("You need "+x+" Points to Level up!!", TextColor.fromHexString("#ff4a4a")));
                player.playSound(player, Sound.ENTITY_SHULKER_HURT_CLOSED,  10f, 1f);
            }
            if ((player_level + ArmorPrestigeUpgrade) % 30 == 0) {
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


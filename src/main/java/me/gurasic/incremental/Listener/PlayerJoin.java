package me.gurasic.incremental.Listener;

import me.gurasic.incremental.GUIs.ChallengeMenu.ChallengeItem;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem;
import me.gurasic.incremental.Gear;
import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJoin implements Listener {
    private Incremental plugin;

    public PlayerJoin(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
     Gear gear = new Gear();
     Player player = event.getPlayer();
//        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
//            if (player.isOnline()) {
//                Location loc = new Location(player.getWorld(), 1, -58, 2);
//                player.sendBlockChange(loc, Material.STONE.createBlockData());
//            }
//        }, 0L, 20L); // 20 ticks = 1 second

     plugin.createPlayerFile(player.getUniqueId());
     plugin.giveRainbowArmor(player);
     Map<String, Object> defaultValues = new HashMap<>();
     defaultValues.put("pointCount", new BigInteger("0"));
     defaultValues.put("multiCount", 1);
     defaultValues.put("playerLevel", 1);
     defaultValues.put("beforeCost", 1);
     defaultValues.put("ArmorLevel", 0);
     defaultValues.put("Unluck", 40);
     defaultValues.put("IncLvs", 10); // the amount of levels you get after a +1 +1s
     defaultValues.put("playerPrestige", 0);
     defaultValues.put("prestigePoints", 0);
     defaultValues.put("ClickerPrestigeLevel", 0);
     defaultValues.put("ClickerPrestigeCost", 2);
     defaultValues.put("ArmorPrestigeLevel", 0);
     defaultValues.put("ArmorPrestigeCost", 3);
     defaultValues.put("GenerosityPrestigeLevel", 0);
     defaultValues.put("GenerosityPrestigeCost", 3);
     defaultValues.put("LuckPrestigeLevel", 0);
     defaultValues.put("LuckPrestigeCost", 50);
     defaultValues.put("DefensePrestigeLevel", 5);
     defaultValues.put("DefensePrestigeCost", 100);
     defaultValues.put("GiveDefenseCharm", true);
     defaultValues.put("PricesPrestigeLevel", 0);
     defaultValues.put("PricesPrestigeCost", 10);
     defaultValues.put("WishingPrestigeLevel", 0);
     defaultValues.put("WishingPrestigeCost", 10);
     defaultValues.put("SuperPrestigeLevel", 0);
     defaultValues.put("SuperPrestigePoints", 0);
     defaultValues.put("RangerSP", false);
     defaultValues.put("Cleaning_Service", false);
     defaultValues.put("Booster", false);
     defaultValues.put("Good_Will", false);
     defaultValues.put("Out_For_Blood", false);
     defaultValues.put("Lucky_day", false);
     defaultValues.put("Well_Feed", false);
     defaultValues.put("Refreshing_Sip", false);
     defaultValues.put("The_Perfect_Wish", false);
     defaultValues.put("Crushing_Blows", false);
     defaultValues.put("Fist_Mastery", false);
     defaultValues.put("Clicking_Expert", false);
     defaultValues.put("Fast_Pass", false);
     defaultValues.put("Blind_Rage", false);
     defaultValues.put("Clover_Blades", false);
     defaultValues.put("Better_Blocks", false);
     defaultValues.put("Tank", false);
     defaultValues.put("FleetFoot", false);
     defaultValues.put("PlayerSpeedCapBool", true);
     defaultValues.put("Healthy", false);
     defaultValues.put("HealthyBool", true);
     defaultValues.put("Adrenaline", false);
     defaultValues.put("QuickBuy", false);
     defaultValues.put("Hasty_Hands", false);
     defaultValues.put("Serial_Killer", false);
     defaultValues.put("Clicking_Legend", false);
     defaultValues.put("Master_Ranger", false);
     defaultValues.put("Blood_Is_Fuel", false);
     defaultValues.put("Incredible_Findings", false);
     defaultValues.put("Rebirth_Level", 0);
     defaultValues.put("Rebirth_Points", 0);
     defaultValues.put("RebirthAnimation", true);
     defaultValues.put("Expertise", false);
     defaultValues.put("Prestige_Accelerator", false);
     defaultValues.put("Speedrunner", false);
     defaultValues.put("Brightest_Night", false);
     defaultValues.put("The_Vault", false);
     defaultValues.put("MilSword", false);
     defaultValues.put("Cut_Trough", false);
     defaultValues.put("Block_Fiend", false);
     defaultValues.put("Extreme_Speed", false);
     defaultValues.put("Mastery", false);
     defaultValues.put("Hollow_Heart", false);
     defaultValues.put("HollowBool", true);
     defaultValues.put("ApothLevel", 0);
     defaultValues.put("ApothPoints", 0);
     defaultValues.put("PlayerHasApoth", false);
     for (Map.Entry<String, Object> entry : defaultValues.entrySet()) {
         if (plugin.accessPlayerData(player.getUniqueId(), entry.getKey()) == null) {
             plugin.storePlayerData(player.getUniqueId(), entry.getKey(), entry.getValue());
         }
     }
     Map<String, Object> FarmingdefaultValues = new HashMap<>();
     FarmingdefaultValues.put("Coins", 0);
     FarmingdefaultValues.put("Wheat", 0);
     FarmingdefaultValues.put("WheatBuff", false);
     FarmingdefaultValues.put("WheatValue", 0);
     FarmingdefaultValues.put("WheatValueCost", 5);
     FarmingdefaultValues.put("WheatSpeed", 0);
     FarmingdefaultValues.put("WheatSpeedCost", 100);
     for (Map.Entry<String, Object> entry : FarmingdefaultValues.entrySet()) {
         if (accessPlayerDataF(player.getUniqueId(), entry.getKey()) == null) {
             storePlayerDataF(player.getUniqueId(), entry.getKey(), entry.getValue());
         }
     }
        Map<String, Object> IDdefaultValues = new HashMap<>();
        IDdefaultValues.put("x", 10000);
        IDdefaultValues.put("y", 100);
        IDdefaultValues.put("z", 0);
        for (Map.Entry<String, Object> entry : IDdefaultValues.entrySet()) {
            if (accessPlayerDataC(entry.getKey()) == null) {
                storePlayerDataC(entry.getKey(), entry.getValue());
            }
        }
     player.getInventory().setItem(0, gear.Sword1);
     player.getInventory().setItem(7, gear.clicker);
     player.getInventory().setItem(8, gear.upgrader);
     if ((int) plugin.accessPlayerData(player.getUniqueId(), "SuperPrestigeLevel") > 0
     || (int) plugin.accessPlayerData(player.getUniqueId(), "Rebirth_Level") > 0) {
         SuperPrestigeItem.HasSp = true;
     }
        if ((int) plugin.accessPlayerData(player.getUniqueId(), "Rebirth_Level") > 0) {
            ChallengeItem.HasRebirth = true;
        }
  }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Map<String, Integer> RankTiers = new LinkedHashMap<>();
        RankTiers.put("playerPrestige", (int) plugin.accessPlayerData(player.getUniqueId(), "playerPrestige"));
        RankTiers.put("SuperPrestigeLevel", (int) plugin.accessPlayerData(player.getUniqueId(), "SuperPrestigeLevel"));
        RankTiers.put("Rebirth_Level", (int) plugin.accessPlayerData(player.getUniqueId(), "Rebirth_Level"));
        RankTiers.put("Apoth_Level", (int) plugin.accessPlayerData(player.getUniqueId(), "ApothLevel"));

        StringBuilder formatBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : RankTiers.entrySet()) {
            int count = entry.getValue();
            if (count > 0) {
                String colorCode = switch (entry.getKey()) {
                    case "Apoth_Level" -> ChatColor.AQUA.toString();
                    case "Rebirth_Level" -> ChatColor.RED.toString();
                    case "SuperPrestigeLevel" -> ChatColor.DARK_PURPLE.toString();
                    case "playerPrestige" -> ChatColor.GREEN.toString();
                    default -> ChatColor.WHITE.toString();
                };
                formatBuilder.insert(0, ChatColor.DARK_GRAY + "[" + colorCode + "+" + count + ChatColor.DARK_GRAY + "]");
            }
        }
        String playerName = ChatColor.WHITE + event.getPlayer().getDisplayName();
        String message = ChatColor.WHITE + event.getMessage();
        event.setFormat(formatBuilder.toString() + playerName + ": " + message);
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
    private Object accessPlayerDataF(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental/FarmingData", uuid.toString() + "_Farming.yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
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

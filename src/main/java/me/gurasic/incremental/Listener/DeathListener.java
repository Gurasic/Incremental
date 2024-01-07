package me.gurasic.incremental.Listener;


import me.gurasic.incremental.Incremental;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;
import java.util.Random;

public class DeathListener implements Listener {
    private Incremental plugin;


    public DeathListener(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Random random = new Random();
        Player victim = event.getEntity();
        if (victim.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) victim.getLastDamageCause();
            if (dmgEvent.getDamager() instanceof Player) {
                Player killer = (Player) dmgEvent.getDamager();
                int killerLuck = (int) plugin.accessPlayerData(killer.getUniqueId(), "LuckPrestigeLevel");
                int killerUnluck = (int) plugin.accessPlayerData(killer.getUniqueId(), "Unluck");
                int killerIncLvs = (int) plugin.accessPlayerData(killer.getUniqueId(), "IncLvs");
                int killerPoints = (int) plugin.accessPlayerData(killer.getUniqueId(), "pointCount");
                int killerLevel = (int) plugin.accessPlayerData(killer.getUniqueId(), "playerLevel");
                int killerMulti = (int) plugin.accessPlayerData(killer.getUniqueId(), "multiCount");
                int killerBC = (int) plugin.accessPlayerData(killer.getUniqueId(), "beforeCost");
                int victimLevel = (int) plugin.accessPlayerData(victim.getUniqueId(), "playerLevel");
                int killerGenerosity = (int) plugin.accessPlayerData(killer.getUniqueId(), "GenerosityPrestigeLevel");
                int victimGenerosity = (int) plugin.accessPlayerData(victim.getUniqueId(), "GenerosityPrestigeLevel");
                int l = 1;
                int Ex = 1;
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Out_For_Blood")) {
                    l = 2;
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Serial_Killer")) {
                    l = 3;
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Blind_Rage")) {
                    killer.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(100,1));
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Adrenaline")) {
                    killer.addPotionEffect(PotionEffectType.SPEED.createEffect(100,1));
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Blood_Is_Fuel")) {
                    killer.addPotionEffect(PotionEffectType.REGENERATION.createEffect(80,1));
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Clover_Blades")) {
                    killerUnluck = killerUnluck - 5;
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Incredible_Findings")) {
                    killerUnluck = killerUnluck - 4;
                }
                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Expertise")) {
                    Ex = 5;
                }
                int x = ((victimLevel*(killerLevel*(killerGenerosity+1))) * (2 * (victimGenerosity + 1)) * l) * Ex;
                int y = random.nextInt(0,killerUnluck-killerLuck);
                plugin.storePlayerData(killer.getUniqueId(), "pointCount",killerPoints + x);

                if ((boolean) plugin.accessPlayerData(killer.getUniqueId(), "Refreshing_Sip")) {
                    double killerHealth = killer.getHealth();
                    double maxHealth = Objects.requireNonNull(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    if (killerHealth < maxHealth) {
                        double healthIncrease = Math.min(4, maxHealth - killerHealth);
                        killer.setHealth(killerHealth + healthIncrease);
                    }
                }

                killer.sendMessage(Component.text("", TextColor.color(107, 107, 107)));
                killer.sendMessage(Component.text("⏩ You Gained ", TextColor.color(173, 173, 173))
                           .append(Component.text("+" + x, TextColor.color(0, 242, 8))));
                if (y <= 5) {
                    plugin.storePlayerData(killer.getUniqueId(), "playerLevel",killerLevel + killerIncLvs);
                    killer.sendMessage(Component.text("", TextColor.color(107, 107, 107)));
                    killer.sendMessage(Component.text("---------------------", TextColor.color(107, 107, 107)));
                    killer.sendMessage(Component.text("⏩ "+killer.getName()+" got ", TextColor.color(173, 173, 173))
                               .append(Component.text("+1 +1", TextColor.color(255, 234, 0))));
                    killer.sendMessage(Component.text("---------------------", TextColor.color(107, 107, 107)));
                    for(int i = 0; i < killerIncLvs; i++) {
                        killerMulti = (int) plugin.accessPlayerData(killer.getUniqueId(), "multiCount");
                        killerLevel = (int) plugin.accessPlayerData(killer.getUniqueId(), "playerLevel");
                        killerLevel = killerLevel + 1;
                        killerMulti = killerMulti + 1;
                        int lvl_rqm = killerBC + (int) Math.pow(killerLevel,2);
                        plugin.storePlayerData(killer.getUniqueId(), "multiCount", killerMulti);
                        plugin.storePlayerData(killer.getUniqueId(), "playerLevel", killerLevel);
                        plugin.storePlayerData(killer.getUniqueId(), "beforeCost", lvl_rqm);
                        killer.sendActionBar(Component.text("[ You Leveled Up To Level "+ killerLevel +"]", TextColor.fromHexString("#25de00")));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player killer = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();
            boolean victimTank = (boolean) plugin.accessPlayerData(victim.getUniqueId(), "Tank");
            boolean killerCSupgrade = (boolean) plugin.accessPlayerData(killer.getUniqueId(), "Cleaning_Service");

            Scoreboard scoreboard = victim.getScoreboard();
            Objective healthObjective = scoreboard.getObjective("health");

            // If the "health" objective doesn't exist, create it
            if (healthObjective == null) {
                healthObjective = scoreboard.registerNewObjective("health", "dummy", "health");
            }

            int damageeHealthScore = healthObjective.getScore(victim.getName()).getScore();

            if (damageeHealthScore < 10 && killerCSupgrade) {
                event.setDamage(event.getDamage() + 2);
            }

            if (victimTank) {
                victim.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20,1));
            }
        }
    }
}

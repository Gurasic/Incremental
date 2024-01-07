package me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic;

import me.gurasic.incremental.Incremental;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Steak_Listener implements Listener {
    private Incremental plugin;


    public Steak_Listener(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerEatSteak(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if ((boolean) plugin.accessPlayerData(player.getUniqueId(), "Well_Feed")) {
            if (event.getItem().getType() == Material.COOKED_BEEF) {
                PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 40, 1);
                player.addPotionEffect(regen);
            }
        }
    }
}

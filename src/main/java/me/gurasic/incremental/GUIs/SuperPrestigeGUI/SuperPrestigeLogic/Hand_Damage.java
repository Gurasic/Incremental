package me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeLogic;

import me.gurasic.incremental.Incremental;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
public class Hand_Damage implements Listener {
    private Incremental plugin;


    public Hand_Damage(Incremental plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand.getType().isAir() && (boolean) plugin.accessPlayerData(player.getUniqueId(), "Well_Feed")) {
                event.setDamage(event.getDamage() + 2);
            }
        }

    }
}

package me.gurasic.incremental.GUIs.ChallengeMenu;

import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.window.Window;

import static me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem.HasSp;

public class ChallengeItem extends AbstractItem {


    public static boolean HasRebirth = false;
    @Override
    public ItemProvider getItemProvider() {
        if(HasRebirth) {
            return new ItemBuilder(Material.SPIDER_EYE).setDisplayName("§n§4«Challenges»");
        }
        else if(HasSp) {
            return new ItemBuilder(Material.SPIDER_EYE).setDisplayName("§4§k123456789abc")
                    .addLoreLines("§7Reach §cRebirth§7 to unlock this Tab");
        }
        else {
            return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setDisplayName(" ");

        }
    }
    ItemProvider Sign = new ItemBuilder(Material.OAK_SIGN).setDisplayName("§n§4«Challenges»")
            .addLoreLines("§7Click a challenge to start it, a challenge will give")
            .addLoreLines("§7you Buffs or Debuffs and a goal for you to reach, it will")
            .addLoreLines("§7§cNever Reset§7 all your progress, but only")
            .addLoreLines("§7the tiers before the one you are in")
            .addLoreLines("")
            .addLoreLines("§7While in a challenge you receive a special Prefix")
            .addLoreLines("§7Beating a challenge will give you a permanent Buff")
            .addLoreLines("§7Unlock 2 new challenges for every new tier of +1s")
            .addLoreLines("")
            .addLoreLines("§8Difficulty Chart")
            .addLoreLines("§a•Easy")
            .addLoreLines("§6•Medium")
            .addLoreLines("§c•Hard")
            .addLoreLines("§4•Extreme")
            .addLoreLines("§5•Hellish");
    ItemProvider GlassGray = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
    ItemProvider GlassDark = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ");
    ItemProvider GlassRed = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName(" ");
    ItemProvider Chains = new ItemBuilder(Material.CHAIN).setDisplayName(" ");

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        Gui gui = Gui.normal() // Creates the GuiBuilder for a normal GUI
                .setStructure(
                        "g d d r s r d d g",
                        "! . . . . . . . !",
                        "! . . . . . . . !",
                        "! . . . . . . . !",
                        "g d d r b r d d g")
                .addIngredient('g', GlassGray)
                .addIngredient('d', GlassDark)
                .addIngredient('r', GlassRed)
                .addIngredient('!', Chains)
                .addIngredient('s', Sign)
                .addIngredient('b', new ReturnItem())
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("Challenges")
                .setGui(gui)
                .build();
        if (clickType.isLeftClick()) {
            window.open();
        }

        notifyWindows(); // this will update the ItemStack that is displayed to the player
    }

}

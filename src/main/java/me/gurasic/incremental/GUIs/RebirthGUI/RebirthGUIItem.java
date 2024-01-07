package me.gurasic.incremental.GUIs.RebirthGUI;

import me.gurasic.incremental.GUIs.ChallengeMenu.ChallengeItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
import me.gurasic.incremental.GUIs.RebirthGUI.RebirthUpgrades.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.io.File;
import java.util.UUID;

import static me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeItem.HasSp;

public class RebirthGUIItem extends AbstractItem {
    ItemProvider GlassGray = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName(" ");
    ItemProvider GlassDark = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ");
    public static Window window;
    @Override
    public ItemProvider getItemProvider() {
        if (ChallengeItem.HasRebirth) {
            return new ItemBuilder(Material.TOTEM_OF_UNDYING).setDisplayName("§cRebirth Shop");
        }
        else if(HasSp) {
            return new ItemBuilder(Material.TOTEM_OF_UNDYING).setDisplayName("§c§kRebirthEShop")
                    .addLoreLines("§7Reach §cRebirth§7 to unlock this Tab");
        }
        else {
            return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setDisplayName(" ");

        }

    }


    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        Gui gui = Gui.normal()
                .setStructure(
                        "# 1 2 3 4 5 . . #",
                        "# . . . . . . . #",
                        "# . . . . . . . #",
                        "# . . . . . . . #",
                        "# . . . . . . . #",
                        "T . . . . . . . B")
                .addIngredient('#', GlassDark)
                .addIngredient('B', new ReturnItem())
                .addIngredient('T', new AnimationToggleRB(player.getUniqueId()))
                .addIngredient('1', new Experitise(player.getUniqueId()))
                .addIngredient('2', new Prestige_Accelerator(player.getUniqueId()))
                .addIngredient('3', new Speedrunner(player.getUniqueId()))
                .addIngredient('4', new Brightest_Night(player.getUniqueId()))
                .addIngredient('5', new The_Vault(player.getUniqueId()))
                .build();
         window = Window.single()
                .setViewer(player)
                .setTitle("Rebirth Shop | " + accessPlayerData(player.getUniqueId(), "Rebirth_Points") + "♦")
                .setGui(gui)
                .build();

        if(clickType.isLeftClick()) {
            window.open();
        }
    }
    private Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
}

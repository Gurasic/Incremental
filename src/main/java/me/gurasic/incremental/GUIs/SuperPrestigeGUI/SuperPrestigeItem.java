package me.gurasic.incremental.GUIs.SuperPrestigeGUI;

import me.gurasic.incremental.GUIs.PrestigeGUI.PrestigeShopItem;
import me.gurasic.incremental.GUIs.PrestigeGUI.ReturnItem;
import me.gurasic.incremental.GUIs.RebirthGUI.RebirthTransItem;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP0.Booster;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP0.Cleaning_Service;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP0.Ranger;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP1.Good_Will;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP1.Lucky_Day;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP1.Out_For_Blood;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP2.Refreshing_Sip;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP2.The_Perfect_Wish;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP2.Well_Feed;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP3.Clicking_Expert;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP3.Crushing_Blows;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP3.Fist_Mastery;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP4.Blind_Rage;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP4.Clover_Blades;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP4.Fast_Pass;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP5.Better_Blocks;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP5.FleetFoot;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP5.Tank;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP6.Adrenaline;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP6.Healthy;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP6.QuickBuy;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP7.Clicking_Legend;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP7.Hasty_Hands;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP7.Serial_Killer;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP8.Blood_Is_Fuel;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP8.Incredible_Findings;
import me.gurasic.incremental.GUIs.SuperPrestigeGUI.SuperPrestigeUpgrades.SP8.Master_Ranger;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.ScrollGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.window.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperPrestigeItem extends AbstractItem {

    public ItemProvider GrayGlass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
            .setDisplayName("§r");
    public static Window window;
    public static Boolean HasSp = false;
    @Override
    public ItemProvider getItemProvider() {
        if (HasSp) {
            return new ItemBuilder(Material.BEACON)
                    .setDisplayName("§5Super Prestige Shop")
                    .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    .addEnchantment(Enchantment.LURE, 1, true);
        }
        else {
            return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setDisplayName(" ");

        }
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {

        List<Item> items = new ArrayList<>();
        items.add(new Ranger(player.getUniqueId()));
        items.add(new Cleaning_Service(player.getUniqueId()));
        items.add(new Booster(player.getUniqueId()));
        items.add(new Good_Will(player.getUniqueId()));
        items.add(new Out_For_Blood(player.getUniqueId()));
        items.add(new Lucky_Day(player.getUniqueId()));
        items.add(new Well_Feed(player.getUniqueId()));
        items.add(new Refreshing_Sip(player.getUniqueId()));
        items.add(new The_Perfect_Wish(player.getUniqueId()));
        items.add(new Crushing_Blows(player.getUniqueId()));
        items.add(new Fist_Mastery(player.getUniqueId()));
        items.add(new Clicking_Expert(player.getUniqueId()));
        items.add(new Fast_Pass(player.getUniqueId()));
        items.add(new Blind_Rage(player.getUniqueId()));
        items.add(new Clover_Blades(player.getUniqueId()));
        items.add(new Better_Blocks(player.getUniqueId()));
        items.add(new Tank(player.getUniqueId()));
        items.add(new FleetFoot(player.getUniqueId()));
        items.add(new Healthy(player.getUniqueId()));
        items.add(new Adrenaline(player.getUniqueId()));
        items.add(new QuickBuy(player.getUniqueId()));
        items.add(new Hasty_Hands(player.getUniqueId()));
        items.add(new Serial_Killer(player.getUniqueId()));
        items.add(new Clicking_Legend(player.getUniqueId()));
        items.add(new Master_Ranger(player.getUniqueId()));
        items.add(new Blood_Is_Fuel(player.getUniqueId()));
        items.add(new Incredible_Findings(player.getUniqueId()));
        Gui ShopGUI = ScrollGui.items()
                .setStructure(
                        "# . x . x . x . #",
                        "# . x . x . x . #",
                        "# . x . x . x . #",
                        "# . x . x . x . #",
                        "u . x . x . x . R",
                        "d . x . x . x . E")
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', GrayGlass)
                .addIngredient('u', new ScrollUpItem())
                .addIngredient('d', new ScrollDownItem())
                .addIngredient('E', new ReturnItem())
                .addIngredient('R', new RebirthTransItem())
                .setContent(items)
                .build();
        window = Window.single()
                .setViewer(player)
                .setTitle("Super Prestige Shop | " + accessPlayerData(player.getUniqueId(), "SuperPrestigePoints") + "☽")
                .setGui(ShopGUI)
                .build(player);
        if (clickType.isLeftClick() && HasSp) {
            window.open();
        }
        notifyWindows(); // this will update the ItemStack that is displayed to the player
    }

    private Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
}

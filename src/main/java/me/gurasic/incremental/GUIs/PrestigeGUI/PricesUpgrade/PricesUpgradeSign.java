package me.gurasic.incremental.GUIs.PrestigeGUI.PricesUpgrade;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AutoUpdateItem;

import java.io.File;
import java.util.UUID;
import java.util.function.Supplier;

public class PricesUpgradeSign extends AutoUpdateItem {

    private UUID playerUUID;

    public PricesUpgradeSign(int period, Supplier<? extends ItemProvider> builderSupplier, UUID playerUUID) {
        super(period, builderSupplier);
        this.playerUUID = playerUUID;
    }
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.OAK_SIGN).setDisplayName("§bLevel " +accessPlayerData(playerUUID, "PricesPrestigeLevel")+"/90");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        if (clickType.isLeftClick()) {

        }
        notifyWindows();
    }

    private Object accessPlayerData(UUID uuid, String key) {
        File playerFile = new File("plugins/Incremental", uuid.toString() + ".yml");
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);

        // Access data
        Object value = playerData.get(key);

        return value;
    }
}
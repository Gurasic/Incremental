package me.gurasic.incremental.GUIs.PrestigeGUI.ArmorUpgrade;

import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.impl.AutoUpdateItem;

import java.util.UUID;
import java.util.function.Supplier;

public class ArmorUpgradeSign extends AutoUpdateItem {
    private UUID playerUUID;
    public ArmorUpgradeSign(int period, Supplier<? extends ItemProvider> builderSupplier, UUID playerUUID) {
        super(period, builderSupplier);
        this.playerUUID = playerUUID;
    }
}

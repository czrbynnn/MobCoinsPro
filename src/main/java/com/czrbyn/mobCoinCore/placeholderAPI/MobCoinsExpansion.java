package com.czrbyn.mobCoinCore.placeholderAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.data.MobCoinManager;

public class MobCoinsExpansion extends PlaceholderExpansion {

    private final MobCoinCore plugin;
    private final MobCoinManager mcm;

    public MobCoinsExpansion(MobCoinCore plugin) {
        this.plugin = plugin;
        this.mcm = plugin.getMcoinm();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "mobcoins";
    }

    @Override
    public @NotNull String getAuthor() {
        return "YourName";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {

        if (player == null || !player.hasPlayedBefore()) {
            return "0";
        }

        // %mobcoins_balance%
        if (identifier.equalsIgnoreCase("balance")) {
            return String.valueOf(mcm.getPlayerMobcoins(player));
        }

        return null;
    }
}

package com.czrbyn.mobCoinCore.api;

import java.util.UUID;

import com.czrbyn.mobCoinCore.data.MobCoinManager;
import org.bukkit.entity.Player;

public class MobCoinAPI {

    private static MobCoinAPI instance;

    public static void setInstance(MobCoinAPI api) {
        instance = api;
    }

    public static MobCoinAPI getInstance() {
        return instance;
    }

    private final MobCoinManager manager;

    public MobCoinAPI(MobCoinManager manager) {
        this.manager = manager;
    }

    public void addMobCoins(Player player, int amount) {
        manager.addMobcoinsToPlayer(player, amount);
    }

    public int getMobCoins(Player p) {
        return manager.getPlayerMobcoins(p);
    }

    public void setMobCoins(Player p, int amount) {
        manager.setMobcoins(p, amount);
    }

    public boolean hasEnough(Player p, int amount) {
        return getMobCoins(p) >= amount;
    }

    public void subtract(Player p, int amount) {
        manager.removeMobcoinsFromPlayer(p, amount);
    }
}

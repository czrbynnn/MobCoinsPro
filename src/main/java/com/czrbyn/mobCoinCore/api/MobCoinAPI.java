package com.czrbyn.mobCoinCore.api;

import java.util.UUID;

import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.data.ValuesManager;
import com.sun.jdi.Value;
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
    private final ValuesManager vm;

    public MobCoinAPI(MobCoinManager manager, ValuesManager vm) {
        this.manager = manager;
        this.vm = vm;
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

    public Integer getCoin(String entityName) {
        return vm.getCoins(entityName);
    }

    public void setEnabled(boolean toSet) {
        vm.setEnabled(toSet);
        vm.save();
    }
}

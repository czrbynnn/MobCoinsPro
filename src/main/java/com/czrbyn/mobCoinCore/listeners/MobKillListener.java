package com.czrbyn.mobCoinCore.listeners;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.data.ValuesManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillListener implements Listener {

    private MobCoinCore mcc;
    private MobCoinManager mcm;
    private ValuesManager vm;

    public MobKillListener() {
        mcc = MobCoinCore.getInstance();
        mcm = mcc.getMcoinm();
        vm = mcc.getVm();
    }

    @EventHandler
    public void mobKill(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();

        if (killer != null && vm.isEnabled()) {
            if (vm.getCoins(e.getEntity().getType().toString().toLowerCase()) > 0) {
                mcm.addMobcoinsToPlayer(killer, vm.getCoins(e.getEntity().getType().toString().toLowerCase()));
            }
        }
    }

}

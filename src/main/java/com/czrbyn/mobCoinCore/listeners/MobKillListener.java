package com.czrbyn.mobCoinCore.listeners;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.data.ValuesManager;
import com.czrbyn.mobCoinCore.events.MobCoinGainEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
            String mobType = e.getEntity().getType().toString().toLowerCase();
            int baseAmount = vm.getCoins(mobType);

            if (baseAmount > 0) {
                MobCoinGainEvent event = new MobCoinGainEvent(killer, baseAmount);
                Bukkit.getPluginManager().callEvent(event);

                if (vm.isEnabled()) {
                    mcm.addMobcoinsToPlayer(killer, (int) Math.round(event.getFinalAmount()));
                }
            }
        }
    }

}

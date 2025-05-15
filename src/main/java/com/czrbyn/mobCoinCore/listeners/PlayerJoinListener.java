package com.czrbyn.mobCoinCore.listeners;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private MobCoinCore mcc;
    private MobCoinManager mcoinm;

    public PlayerJoinListener() {
        mcc = MobCoinCore.getInstance();
        mcoinm = mcc.getMcoinm();
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPlayedBefore()) {
            mcoinm.setMobcoins(e.getPlayer(), 0);
        }
    }

}

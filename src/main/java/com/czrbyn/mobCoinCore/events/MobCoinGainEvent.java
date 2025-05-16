package com.czrbyn.mobCoinCore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MobCoinGainEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private double baseAmount;
    private double finalAmount;

    public MobCoinGainEvent(Player player, double baseAmount) {
        this.player = player;
        this.baseAmount = baseAmount;
        this.finalAmount = baseAmount;
    }

    public Player getPlayer() {
        return player;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}

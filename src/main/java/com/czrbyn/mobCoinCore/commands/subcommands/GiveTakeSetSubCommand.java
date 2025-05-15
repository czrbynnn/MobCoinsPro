package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveTakeSetSubCommand {

    public void Give(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (!sender.hasPermission("mobcoinspro.admin") && !sender.hasPermission("mobcoinspro.givemobcoins")) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cYou do not have permission to use this command."));
            return;
        }

        if (args.length < 3) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cUsage: /mobcoins give <player> <amount>"));
            return;
        }

        String playerName = args[1];
        int amount;

        try {
            amount = Integer.parseInt(args[2]);
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlease provide a valid positive integer amount."));
            return;
        }

        Player onlinePlayer = Bukkit.getPlayerExact(playerName);
        if (onlinePlayer != null) {
            mcm.addMobcoinsToPlayer(onlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou gave &e" + amount + " &e\uD83E\uDE99 Mobcoins&f to &a" + onlinePlayer.getName()));
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        if (offlinePlayer.hasPlayedBefore()) {
            mcm.addMobcoinsToPlayer(offlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou gave &e" + amount + " &e\uD83E\uDE99 Mobcoins&f to &a" + offlinePlayer.getName()));
        } else {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlayer '" + playerName + "' has never joined before."));
        }
    }

    public void Set(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (!sender.hasPermission("mobcoinspro.admin") && !sender.hasPermission("mobcoinspro.setmobcoins")) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cYou do not have permission to use this command."));
            return;
        }

        if (args.length < 3) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cUsage: /mobcoins set <player> <amount>"));
            return;
        }

        String playerName = args[1];
        int amount;

        try {
            amount = Integer.parseInt(args[2]);
            if (amount < 0) amount = 0;
        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlease provide a valid integer amount."));
            return;
        }

        Player onlinePlayer = Bukkit.getPlayerExact(playerName);
        if (onlinePlayer != null) {
            mcm.setMobcoins(onlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou set &a" + onlinePlayer.getName() + "'s &e\uD83E\uDE99 Mobcoins&f to &e" + amount));
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        if (offlinePlayer.hasPlayedBefore()) {
            mcm.setMobcoins(offlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou set &a" + offlinePlayer.getName() + "'s &e\uD83E\uDE99 Mobcoins&f to &e" + amount));
        } else {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlayer '" + playerName + "' has never joined before."));
        }
    }

    public void Take(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (!sender.hasPermission("mobcoinspro.admin") && !sender.hasPermission("mobcoinspro.takemobcoins")) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cYou do not have permission to use this command."));
            return;
        }

        if (args.length < 3) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cUsage: /mobcoins take <player> <amount>"));
            return;
        }

        String playerName = args[1];
        int amount;

        try {
            amount = Integer.parseInt(args[2]);
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlease provide a valid positive integer amount."));
            return;
        }

        Player onlinePlayer = Bukkit.getPlayerExact(playerName);
        if (onlinePlayer != null) {
            mcm.removeMobcoinsFromPlayer(onlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou removed &e" + amount + " &e\uD83E\uDE99 Mobcoins&f from &a" + onlinePlayer.getName()));
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
        if (offlinePlayer.hasPlayedBefore()) {
            mcm.removeMobcoinsFromPlayer(offlinePlayer, amount);
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou removed &e" + amount + " &e\uD83E\uDE99 Mobcoins&f from &a" + offlinePlayer.getName()));
        } else {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cPlayer '" + playerName + "' has never joined before."));
        }
    }
}

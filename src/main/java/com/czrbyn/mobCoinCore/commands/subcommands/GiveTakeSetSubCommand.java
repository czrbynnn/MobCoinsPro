package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveTakeSetSubCommand {

    public void Give(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (sender.hasPermission("mobcoinspro.admin") || sender.hasPermission("mobcoinspro.givemobcoins")) {
            Player onlinePlayer = Bukkit.getPlayerExact(args[1]);

            if (onlinePlayer != null) {
                if (onlinePlayer.hasPlayedBefore()) {
                    if (Integer.parseInt(args[2]) > 0) {
                        sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou have set " + onlinePlayer.getName() + "'s &emobcoins&f to &e") + args[2]);
                        mcm.addMobcoinsToPlayer(onlinePlayer, Integer.parseInt(args[2]));
                    } else {
                        sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fPlease provide a valid integer."));
                    }
                } else {
                    sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fhas not played before!"));
                }
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlinePlayer.hasPlayedBefore()) {
                    sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &f" + args[1] + " has &e$" + mcm.getPlayerMobcoins((Player) offlinePlayer)) + " &emobcoins&f!");
                } else {
                    sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] " + args[1] + " &fhas not played before!"));
                }

            }
        }
    }

    public void Set(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (sender.hasPermission("mobcoinspro.admin") || sender.hasPermission("mobcoinspro.setmobcoins")) {

        }
    }

    public void Take(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (sender.hasPermission("mobcoinspro.admin") || sender.hasPermission("mobcoinspro.takemobcoins")) {

        }
    }

}

package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceSubCommand {

    public void Execute(CommandSender sender, String[] args, MobCoinManager mcm) {
        if (args.length == 2 && !args[1].isEmpty()) {
            Player onlinePlayer = Bukkit.getPlayerExact(args[1]);
            if (onlinePlayer != null) {
                if (onlinePlayer.hasPlayedBefore()) {
                    sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &f" + args[1] + " has &e$" + mcm.getPlayerMobcoins(onlinePlayer) + " &emobcoins&f!"));
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
        } else if (args.length == 1) {
            if (sender instanceof Player) {
                var i = mcm.getPlayerMobcoins((Player) sender);
                sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fYou have &e$" + i + " mobcoins&f!"));
            }
        }
    }

}

package com.czrbyn.mobCoinCore.commands;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.commands.subcommands.*;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.guis.LeaderBoardGUI;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MainCommand implements CommandExecutor {

    private final MobCoinCore mcc;
    private final MobCoinManager mcoinm;

    private final VersionSubCommand vsc;
    private final HelpSubCommand hsc;
    private final BalanceSubCommand bsc;
    private final ReloadSubCommand rsc;
    private final GiveTakeSetSubCommand gtssc;
    private final LeaderBoardGUI lbgui;

    private final File file;

    public MainCommand(MobCoinCore mcc) {
        this.mcc = mcc;
        this.mcoinm = mcc.getMcoinm();

        this.vsc = (VersionSubCommand) mcc.getSubcommands().get(0);
        this.hsc = (HelpSubCommand) mcc.getSubcommands().get(1);
        this.bsc = (BalanceSubCommand) mcc.getSubcommands().get(2);
        this.rsc = (ReloadSubCommand) mcc.getSubcommands().get(3);
        this.gtssc = (GiveTakeSetSubCommand) mcc.getSubcommands().get(4);

        this.lbgui = mcc.getLbgui();



        this.file = mcoinm.getFile();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            hsc.Execute(sender);
            return true;
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "version" -> {
                    vsc.Execute(sender);
                    return true;
                }
                case "reload" -> {
                    rsc.Execute(sender);
                    return true;
                }
                case "top" -> {
                    if (sender instanceof Player) {
                        lbgui.createLeaderboardGUI(file, 54, (Player) sender);
                        return true;
                    } else {
                        sender.sendMessage(ColorUtils.colorize("&cOnly players can use this command."));
                        return true;
                    }
                }
                case "balance" -> {
                    bsc.Execute(sender, args, mcoinm);
                    return true;
                }
            }
        }

        switch (args[0].toLowerCase()) {
            case "balance" -> {
                bsc.Execute(sender, args, mcoinm);
                return true;
            }
            case "give" -> {
                if (args.length > 2) {
                    gtssc.Give(sender, args, mcoinm);
                } else {
                    sender.sendMessage(ColorUtils.colorize("&cUsage: /mobcoins give <player> <amount>"));
                }
                return true;
            }
            case "take" -> {
                if (args.length > 2) {
                    gtssc.Take(sender, args, mcoinm);
                } else {
                    sender.sendMessage(ColorUtils.colorize("&cUsage: /mobcoins take <player> <amount>"));
                }
                return true;
            }
            case "set" -> {
                if (args.length > 2) {
                    gtssc.Set(sender, args, mcoinm);
                } else {
                    sender.sendMessage(ColorUtils.colorize("&cUsage: /mobcoins set <player> <amount>"));
                }
                return true;
            }
        }

        hsc.Execute(sender);
        return true;
    }
}

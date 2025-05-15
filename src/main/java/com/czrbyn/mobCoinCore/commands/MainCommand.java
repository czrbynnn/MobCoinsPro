package com.czrbyn.mobCoinCore.commands;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.commands.subcommands.*;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {

    private MobCoinCore mcc;
    private MobCoinManager mcoinm;

    private VersionSubCommand vsc;
    private HelpSubCommand hsc;
    private BalanceSubCommand bsc;
    private ReloadSubCommand rsc;
    private GiveTakeSetSubCommand gtssc;

    public MainCommand() {
        mcc = MobCoinCore.getInstance();
        mcoinm = mcc.getMcoinm();

        vsc = (VersionSubCommand) mcc.getSubcommands().get(0);
        hsc = (HelpSubCommand) mcc.getSubcommands().get(1);
        bsc = (BalanceSubCommand) mcc.getSubcommands().get(2);
        rsc = (ReloadSubCommand) mcc.getSubcommands().get(3);
        gtssc = (GiveTakeSetSubCommand) mcc.getSubcommands().get(4);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equals("help")) {
            hsc.Execute(sender);
            return true;
        }
        if (args.length == 1 && args[0].equals("version")) {
            vsc.Execute(sender);
            return true;
        }
        if (args.length == 1 && args[0].equals("balance") || args.length == 2 && args[0].equals("balance")) {
            bsc.Execute(sender, args, mcoinm);
            return true;
        }
        if (args.length == 1 && args[0].equals("reload")) {
            rsc.Execute(sender);
            return true;
        }
        if (args.length > 3) {
            switch (args[0]) {
                case "give" -> {
                    gtssc.Give(sender, args, mcoinm);
                    return true;
                }
                case "take" -> {
                    gtssc.Take(sender, args, mcoinm);
                    return true;
                }
                case "set" -> {
                    gtssc.Set(sender, args, mcoinm);
                    return true;
                }
            }
        }



        return false;
    }
}

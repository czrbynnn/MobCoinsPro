package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.data.ValuesManager;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand {

    private MobCoinCore mcc;
    private ValuesManager vm;

    public ReloadSubCommand() {
        mcc = MobCoinCore.getInstance();
        vm = mcc.getVm();
    }

    public boolean Execute(CommandSender sender) {
        if (!sender.hasPermission("mobcoins.admin")) {
            sender.sendMessage("§cYou do not have permission to do that.");
            return false;
        } else {

            long start = System.currentTimeMillis();

            mcc.reloadConfig();

            mcc.getMcoinm().reload(sender);

            vm.reload();

            long end = System.currentTimeMillis();
            sender.sendMessage("§aMobCoins config reloaded in §f" + (end - start) + "ms§a.");
            return true;
        }
    }

}

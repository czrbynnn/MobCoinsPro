package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpSubCommand {

    public void Execute(CommandSender sender) {
        List<String> helpList = new ArrayList<>();
        helpList.add(ColorUtils.colorize("&a--------[ M&2o&ab&2C&ao&2i&an&2s&aP&2r&ao&a ]--------"));
        helpList.add(ColorUtils.colorize("&a ▸ &e/mobcoins shop"));
        helpList.add(ColorUtils.colorize("&a ▸ &e/mobcoins top"));
        helpList.add(ColorUtils.colorize("&a ▸ &e/mobcoins version"));
        if (sender.hasPermission("mobcoins.admin")) {
            helpList.add(ColorUtils.colorize("&c (admin only) ▸ &e/mobcoins give <&cp&e> <amount>"));
            helpList.add(ColorUtils.colorize("&c (admin only) ▸ &e/mobcoins take <&cp&e> <amount>"));
            helpList.add(ColorUtils.colorize("&c (admin only) ▸ &e/mobcoins set <&cp&e> <amount>"));
            helpList.add(ColorUtils.colorize("&c (admin only) ▸ &e/mobcoins &areload"));
        }
        helpList.add(ColorUtils.colorize("&a-----------------------------"));

        for (String s : helpList) {
            sender.sendMessage(s);
        }
    }

}

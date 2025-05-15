package com.czrbyn.mobCoinCore.commands.subcommands;

import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;

import javax.swing.plaf.ColorUIResource;

public class VersionSubCommand {

    public void Execute(CommandSender sender) {
        sender.sendMessage(ColorUtils.colorize("&a--------[ M&2o&ab&2C&ao&2i&an&2s&aP&2r&ao&a ]--------"));
        sender.sendMessage(ColorUtils.colorize("&a ▸ &eVersion: &a1.0.0"));
        sender.sendMessage(ColorUtils.colorize("&a ▸ &eAuthor: &a5lagg"));
        sender.sendMessage(ColorUtils.colorize("&a ▸ &eSupport: &aguns.lol/czrbyn"));
        sender.sendMessage(ColorUtils.colorize("&a-------------------------------------"));
    }

}

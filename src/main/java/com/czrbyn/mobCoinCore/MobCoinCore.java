package com.czrbyn.mobCoinCore;

import com.czrbyn.mobCoinCore.api.MobCoinAPI;
import com.czrbyn.mobCoinCore.commands.MainCommand;
import com.czrbyn.mobCoinCore.commands.MainCommandTabCompletor;
import com.czrbyn.mobCoinCore.commands.subcommands.*;
import com.czrbyn.mobCoinCore.data.BaseValues;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.data.ValuesManager;
import com.czrbyn.mobCoinCore.guis.LeaderBoardGUI;
import com.czrbyn.mobCoinCore.listeners.MobKillListener;
import com.czrbyn.mobCoinCore.listeners.PlayerJoinListener;
import com.czrbyn.mobCoinCore.placeholderAPI.MobCoinsExpansion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MobCoinCore extends JavaPlugin {

    private static MobCoinCore plugin;
    private MobCoinManager mcoinm;

    private VersionSubCommand vsc;
    private HelpSubCommand hsc;
    private BalanceSubCommand bsc;
    private ReloadSubCommand rsc;
    private GiveTakeSetSubCommand gtssc;

    private MainCommand mcmd;

    private PlayerJoinListener pjl;
    private LeaderBoardGUI lbgui;
    private MobKillListener mkl;

    private BaseValues bv;
    private ValuesManager vm;

    @Override
    public void onEnable() {

        plugin = this;
        mcoinm = new MobCoinManager();

        bv = new BaseValues();
        vm = new ValuesManager();


        registerListeners();

        registerCommands();

        MobCoinAPI.setInstance(new MobCoinAPI(this.mcoinm, this.vm));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new MobCoinsExpansion(this).register();
            System.out.println("PlaceholderAPI Found, placeholders enabled (%mobcoins%)");
        } else {
            System.out.println("PlaceholderAPI Not found, placeholders will not work.");
        }

    }

    @Override
    public void onDisable() {

    }

    public void registerCommands() {

        vsc = new VersionSubCommand();
        hsc = new HelpSubCommand();
        bsc = new BalanceSubCommand();
        rsc = new ReloadSubCommand();
        gtssc = new GiveTakeSetSubCommand();



        mcmd = new MainCommand(this);
        getCommand("mobcoin").setExecutor(mcmd);
        getCommand("mobcoin").setTabCompleter(new MainCommandTabCompletor());
    }

    public void registerListeners() {
        pjl = new PlayerJoinListener();
        Bukkit.getPluginManager().registerEvents(pjl, this);

        lbgui = new LeaderBoardGUI();
        Bukkit.getPluginManager().registerEvents(lbgui, this);

        mkl = new MobKillListener();
        Bukkit.getPluginManager().registerEvents(mkl, this);
    }

    public static MobCoinCore getInstance() {
        return plugin;
    }

    public List<Object> getSubcommands() {
        List<Object> l = new ArrayList<>();

        l.add(vsc);
        l.add(hsc);
        l.add(bsc);
        l.add(rsc);
        l.add(gtssc);

        return l;
    }

    public MobCoinManager getMcoinm() {
        return mcoinm;
    }

    public LeaderBoardGUI getLbgui() {
        return lbgui;
    }

    public BaseValues getBv() {
        return bv;
    }

    public ValuesManager getVm() {
        return vm;
    }
}

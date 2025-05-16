package com.czrbyn.mobCoinCore;

import com.czrbyn.mobCoinCore.api.MobCoinAPI;
import com.czrbyn.mobCoinCore.commands.MainCommand;
import com.czrbyn.mobCoinCore.commands.MainCommandTabCompletor;
import com.czrbyn.mobCoinCore.commands.subcommands.*;
import com.czrbyn.mobCoinCore.data.MainConfigManager;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import com.czrbyn.mobCoinCore.data.ShopsConfigManager;
import com.czrbyn.mobCoinCore.guis.LeaderBoardGUI;
import com.czrbyn.mobCoinCore.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MobCoinCore extends JavaPlugin {

    private static MobCoinCore plugin;
    private MainConfigManager mcm;
    private MobCoinManager mcoinm;

    private VersionSubCommand vsc;
    private HelpSubCommand hsc;
    private BalanceSubCommand bsc;
    private ReloadSubCommand rsc;
    private GiveTakeSetSubCommand gtssc;

    private ShopsConfigManager scm;

    private MainCommand mcmd;

    private PlayerJoinListener pjl;
    private LeaderBoardGUI lbgui;



    @Override
    public void onEnable() {
        saveDefaultConfig();

        plugin = this;
        mcm = new MainConfigManager();
        mcoinm = new MobCoinManager();

        registerListeners();

        registerCommands();

        MobCoinAPI.setInstance(new MobCoinAPI(this.mcoinm));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {

        vsc = new VersionSubCommand();
        hsc = new HelpSubCommand();
        bsc = new BalanceSubCommand();
        rsc = new ReloadSubCommand();
        gtssc = new GiveTakeSetSubCommand();

        scm = new ShopsConfigManager();

        mcmd = new MainCommand(this);
        getCommand("mobcoin").setExecutor(mcmd);
        getCommand("mobcoin").setTabCompleter(new MainCommandTabCompletor());
    }

    public void registerListeners() {
        pjl = new PlayerJoinListener();
        Bukkit.getPluginManager().registerEvents(pjl, this);

        lbgui = new LeaderBoardGUI();
        Bukkit.getPluginManager().registerEvents(lbgui, this);
    }

    public static MobCoinCore getInstance() {
        return plugin;
    }

    public MainConfigManager getMcm() {
        return mcm;
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

    public ShopsConfigManager getScm() {
        return scm;
    }

    public MobCoinManager getMcoinm() {
        return mcoinm;
    }

    public LeaderBoardGUI getLbgui() {
        return lbgui;
    }
}

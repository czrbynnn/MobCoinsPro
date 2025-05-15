package com.czrbyn.mobCoinCore;

import com.czrbyn.mobCoinCore.api.MobCoinAPI;
import com.czrbyn.mobCoinCore.data.MainConfigManager;
import com.czrbyn.mobCoinCore.data.MobCoinManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobCoinCore extends JavaPlugin {

    private static MobCoinCore plugin;
    private MainConfigManager mcm;
    private MobCoinManager mcoinm;

    @Override
    public void onEnable() {
        // REGISTER GIT
        saveDefaultConfig();

        plugin = this;
        mcm = new MainConfigManager();
        mcoinm = new MobCoinManager();

        MobCoinAPI.setInstance(new MobCoinAPI(this.mcoinm));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MobCoinCore getInstance() {
        return plugin;
    }

    public MainConfigManager getMcm() {
        return mcm;
    }

    public MobCoinManager mcoinm() {
        return mcoinm;
    }

}

package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;

public class MainConfigManager {

    private MobCoinCore mcc;

    public MainConfigManager() {
        mcc = MobCoinCore.getInstance();
    }

    public void reload() {
        mcc.saveConfig();
    }

}

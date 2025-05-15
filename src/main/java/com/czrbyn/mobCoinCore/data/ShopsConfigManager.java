package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ShopsConfigManager {

    private MobCoinCore mcc;

    private File file;
    private FileConfiguration cfg;

    public ShopsConfigManager() {
        mcc = MobCoinCore.getInstance();
        file = new File(mcc.getDataFolder(), "shops.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }

    public void createShop(String shopName, String[] args, CommandSender sender) {
        if (exists(shopName)) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoinsPro&8] &fShop already exists."));
        } else {

        }
    }

    public boolean exists(String shopName) {

    }



}

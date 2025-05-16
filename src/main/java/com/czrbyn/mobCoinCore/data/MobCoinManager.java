package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MobCoinManager {

    private MobCoinCore mcc;
    private File file;
    private FileConfiguration cfg;

    public MobCoinManager() {
        mcc = MobCoinCore.getInstance();
        file = new File(mcc.getDataFolder(), "mobcoindata.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();

                System.out.println("MobCoinData file does not exist, trying to create it now: Created Successfully:" + file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public Integer getPlayerMobcoins(OfflinePlayer p) {
        if (file.exists()) {
            return cfg.getInt(p.getUniqueId() + ".mobcoins", 0);
        }
        return 0;
    }

    public void removeMobcoinsFromPlayer(OfflinePlayer p, int toRemove) {
        if (!file.exists()) return;

        int current = cfg.getInt(p.getUniqueId() + ".mobcoins", 0);
        int newAmount = Math.max(current - toRemove, 0);
        cfg.set(p.getUniqueId() + ".mobcoins", newAmount);
        saveConfig();
    }

    public void addMobcoinsToPlayer(OfflinePlayer p, int toAdd) {
        if (!file.exists()) return;

        int current = cfg.getInt(p.getUniqueId() + ".mobcoins", 0);
        int newAmount = current + toAdd;

        if (newAmount < 0) {
            newAmount = 0;
        }

        cfg.set(p.getUniqueId() + ".mobcoins", newAmount);
        saveConfig();
    }

    public void setMobcoins(OfflinePlayer p, int value) {
        if (!file.exists()) return;

        cfg.set(p.getUniqueId() + ".mobcoins", Math.max(value, 0));
        saveConfig();
    }

    public void saveConfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reload(CommandSender sender) {
        if (file.exists()) {
            try {
                cfg.save(file);
                sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &fShould be a &asuccess&f! Make sure to check first."));
            } catch (IOException e) {
                sender.sendMessage(ColorUtils.colorize("&8[&bMobCoins&8] &cUnsuccessful."));
                e.printStackTrace();
            }
        }
    }

}

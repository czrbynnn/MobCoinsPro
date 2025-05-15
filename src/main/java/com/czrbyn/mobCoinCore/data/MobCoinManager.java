package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MobCoinManager {

    private MobCoinCore mcc;
    private File file;

    public MobCoinManager() {
        mcc = MobCoinCore.getInstance();
        file = new File(mcc.getDataFolder(), "mobcoindata.yml");

        if (!file.exists()) {
            try {
                System.out.println("MobCoinData file does not exist, trying to create it now: Created Successfully:" + file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    public Integer getPlayerMobcoins(Player p) {
        if (file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            return cfg.getInt(p.getUniqueId() + ".mobcoins", 0);
        } else {
            return 0;
        }
    }

    public void removeMobcoinsFromPlayer(Player p, Integer toRemove) {
        if (file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            var amount = cfg.getInt(p.getUniqueId() + ".mobcoins", 0);
            if (toRemove >= amount) {
                var i = toRemove - amount;
                cfg.set(p.getUniqueId() + ".mobcoins", i);
            }
        }
    }

    public void addMobcoinsToPlayer(Player p, Integer toAdd) {
        if (file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (toAdd < 0) {
                removeMobcoinsFromPlayer(p, toAdd);
            } else {
                var i = (cfg.getInt(p.getUniqueId() + ".mobcoins", 0) + toAdd);
                cfg.set(p.getUniqueId() + ".mobcoins", i);
            }
        }
    }

    public void setMobcoins(Player p, Integer value) {
        if (file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (value < 0) {
                cfg.set(p.getUniqueId() + ".mobcoins", 0);
            } else {
                cfg.set(p.getUniqueId() + ".mobcoins", value);
            }
        }
    }

    public void reload(CommandSender sender) {
        if (file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
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

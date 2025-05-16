package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        if (!exists(shopName, sender) && hasDescription(shopName, args)) {
            StringBuilder descriptionBuilder = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                descriptionBuilder.append(args[i]);
                if (i < args.length - 1) {
                    descriptionBuilder.append(" ");
                }
            }

            String description = descriptionBuilder.toString();

            createShopInFile(shopName, description, sender);
        }
    }

    public boolean hasDescription(String shopName, String[] args) {
        return args.length > 1;
    }

    public boolean createShopInFile(String shopName, String description, CommandSender sender) {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("shops." + shopName + ".description", description);
    }

    public boolean exists(String shopName, CommandSender sender) {
        if (!file.exists()) {
            sender.sendMessage(ColorUtils.colorize("&8[&bMobCoinsPro&8] &fConfiguration File does not exist."));
            return false;
        } else {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            if (config.get("shops." + shopName) != null) {
                sender.sendMessage(ColorUtils.colorize("&8[&bMobCoinsPro&8] &fThis shop already exists."));
                return false;
            }

            if (config.isConfigurationSection("shops")) {
                ConfigurationSection shopsSection = config.getConfigurationSection("shops");

                int shopCount = shopsSection.getKeys(false).size();

                if (shopCount >= 4) {
                    sender.sendMessage(ColorUtils.colorize("&8[&bMobCoinsPro&8] &fYou have reached the maximum number of shops (4)."));
                    return false;
                }

            }
            return true;
        }
    }



}

package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValuesManager {

    private MobCoinCore mcc = MobCoinCore.getInstance();
    private File file;
    private BaseValues bv;
    private YamlConfiguration cfg;

    private Map<String, Object> defaultValues;

    public ValuesManager() {



        bv = mcc.getBv();

        file = new File(mcc.getDataFolder(), "values.yml");

        if (file.exists()) {
            cfg = YamlConfiguration.loadConfiguration(file);
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

                cfg = YamlConfiguration.loadConfiguration(file);

                defaultValues = bv.getMap();

                for (Map.Entry<String, Object> entry : defaultValues.entrySet()) {
                    String mob = entry.getKey();
                    Object value = entry.getValue();

                    cfg.set(mob, value);

                }

                cfg.set("enabled", true);

                save();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getCoins(String entityName) {
        return cfg.getInt(entityName);
    }

    public boolean isEnabled() {
        return cfg.getBoolean("enabled");
    }

    public void reload() {
        if (file.exists()) {
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

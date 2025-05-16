package com.czrbyn.mobCoinCore.data;

import com.czrbyn.mobCoinCore.MobCoinCore;
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

    private Map<String, Object> defaultValues;

    public ValuesManager() {
        bv = mcc.getBv();

        file = new File(mcc.getDataFolder(), "values.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();

                defaultValues = bv.getMap();

                for (Map.Entry<String, Object> entry : defaultValues.entrySet()) {
                    String mob = entry.getKey();
                    Object value = entry.getValue();

                    YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    cfg.set(mob, value);

                }

                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                cfg.set("enabled", true);

                save();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getCoins(String entityName) {
        return YamlConfiguration.loadConfiguration(file).getInt(entityName);
    }

    public boolean isEnabled() {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getBoolean("enabled");
    }

    public void save() {
        try {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

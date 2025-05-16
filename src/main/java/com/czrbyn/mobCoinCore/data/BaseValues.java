package com.czrbyn.mobCoinCore.data;

import java.util.HashMap;
import java.util.Map;

public class BaseValues {

    private Map<String, Object> map;

    public BaseValues() {
        map = new HashMap<>();
        initializeMap();
    }

    public void initializeMap() {

        map.put("zombie", 2);
        map.put("skeleton", 2);
        map.put("spider", 2);
        map.put("creeper", 3);
        map.put("enderman", 5);
        map.put("witch", 6);
        map.put("blaze", 5);
        map.put("magma_cube", 4);
        map.put("ghast", 8);
        map.put("slime", 2);
        map.put("silverfish", 2);

        map.put("zombie_pigman", 4);
        map.put("piglin", 3);
        map.put("hoglin", 4);

        map.put("sheep", 1);
        map.put("pig", 1);
        map.put("cow", 1);
        map.put("chicken", 1);
        map.put("rabbit", 1);
        map.put("horse", 2);
        map.put("llama", 2);

        map.put("iron_golem", 6);
        map.put("snow_golem", 3);
        map.put("wither", 50);
        map.put("ender_dragon", 100);

        map.put("guardian", 6);
        map.put("elder_guardian", 15);
        map.put("shulker", 10);

        map.put("pillager", 3);
        map.put("ravager", 8);
        map.put("evoker", 10);
        map.put("vex", 5);

        map.put("warden", 150);


    }

    public Map<String, Object> getMap() {
        return map;
    }

}

package com.czrbyn.mobCoinCore.guis;

import com.czrbyn.mobCoinCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class LeaderBoardGUI implements Listener {

    List<Player> inGUI = new ArrayList<>();

    public void createLeaderboardGUI(File file, int limit, Player p) {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Map<UUID, Integer> mobcoinMap = new HashMap<>();

        for (String uuidStr : cfg.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(uuidStr);
                int coins = cfg.getInt(uuidStr + ".mobcoins", 0);
                mobcoinMap.put(uuid, coins);
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        List<Map.Entry<UUID, Integer>> sorted = mobcoinMap.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .limit(limit)
                .toList();

        int size = Math.max(27, ((sorted.size() + 8) / 9) * 9);
        Inventory inv = Bukkit.createInventory(p, size, ColorUtils.colorize("&6MobCoins Top"));

        fillBorderWithOrangeGlass(inv);

        int[] headSlots = getHeadSlots(size);

        int index = 0;
        for (Map.Entry<UUID, Integer> entry : sorted) {
            if (index >= headSlots.length) break;

            UUID uuid = entry.getKey();
            int coins = entry.getValue();

            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            String name = player.getName() != null ? player.getName() : "Unknown";

            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

            if (skullMeta != null) {
                skullMeta.setOwningPlayer(player);
                skullMeta.setDisplayName(ColorUtils.colorize("🏆 &6" + name));
                skullMeta.setLore(List.of(
                        ColorUtils.colorize("&6MobCoins: &e" + coins),
                        ColorUtils.colorize(" "),
                        ColorUtils.colorize("&6Position: #&e" + (index + 1))
                ));
                skull.setItemMeta(skullMeta);
            }

            inv.setItem(headSlots[index], skull);
            index++;
        }

        inGUI.add(p);
        p.openInventory(inv);
    }

    private int[] getHeadSlots(int size) {
        if (size == 27) {
            return new int[]{10, 11, 12, 13, 14, 15, 16};
        }
        if (size == 54) {
            List<Integer> slots = new ArrayList<>();
            for (int row = 1; row <= 4; row++) {
                for (int col = 1; col <= 7; col++) {
                    slots.add(row * 9 + col);
                }
            }
            return slots.stream().mapToInt(Integer::intValue).toArray();
        }

        return new int[0];
    }

    private void fillBorderWithOrangeGlass(Inventory inv) {
        ItemStack glass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            glass.setItemMeta(meta);
        }

        int size = inv.getSize();

        for (int i = 0; i < size; i++) {
            boolean isTop = i < 9;
            boolean isBottom = i >= size - 9;
            boolean isLeft = i % 9 == 0;
            boolean isRight = i % 9 == 8;

            if (isTop || isBottom || isLeft || isRight) {
                if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
                    inv.setItem(i, glass);
                }
            }
        }
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent e) {
        inGUI.remove(e.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (inGUI.contains(e.getWhoClicked())) {
            e.setCancelled(true);
        }
    }
}

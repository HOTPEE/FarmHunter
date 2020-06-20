package com.github.hotpee.farmhunter.ConfigManager;

import com.github.hotpee.farmhunter.FarmHunter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class ItemManager {
    private static FileConfiguration icfg = YamlConfiguration.loadConfiguration(FarmHunter.getIns().ItemFile);

    public static String getItemName(String name){
        return icfg.getString("Items." + name + ".Name").replaceAll("&","§");
    }

    public static List<String> getItemLore(String name){
        List<String> list = icfg.getStringList("Items." + name + ".Lore");
        for(int i=0 ; i < list.size() ; i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&',list.get(i)));
        }
        return list;
    }
    public static int getCooldown(String name){
        return icfg.getInt("Items." + name + ".Cooldown");
    }
}

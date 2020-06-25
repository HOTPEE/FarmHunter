package com.github.hotpee.farmhunter;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Commands.CommandManager;
import com.github.hotpee.farmhunter.Listeners.DamageListener;
import com.github.hotpee.farmhunter.Listeners.InteractListener;
import com.github.hotpee.farmhunter.Listeners.OtherListener;
import com.github.hotpee.farmhunter.Metrics.Metrics;
import com.github.hotpee.farmhunter.Metrics.Metrics_cStats;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class FarmHunter extends JavaPlugin {
    public File ArenaFile = new File(getDataFolder(), "arenas.yml");
    public FileConfiguration arenafile = YamlConfiguration.loadConfiguration(ArenaFile);
    public File ItemFile = new File(getDataFolder(), "item.yml");
    public Location mainLobby;
    private static FarmHunter ins;
    public static boolean BungeeState = false;

    private ArrayList<Arena> arena = new ArrayList<>();
    public CommandManager commandManager;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("LibsDisguises")) {
            getLogger().info("Libsdisguises检测成功!插件正常启动");
        } else {
            getLogger().info("Libsdisguises获取失败!插件已关闭");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        ins = this;
        load();
        saveDefaultConfig();
        regListener();
        Bukkit.getConsoleSender().sendMessage("§7[§bFarmHunter§7] §a农场躲猫猫已正常启动!");
        Bukkit.getConsoleSender().sendMessage("§7[§bFarmHunter§7] §7版本 : §6" + getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§b");
        Bukkit.getConsoleSender().sendMessage("§7[§bFarmHunter§7] §3如果遇到任何BUG欢迎在插件发布贴留言");
        mainLobby = Util.getLocation(getConfig().createSection("MainLobby"));
        commandManager = new CommandManager();
        commandManager.setUp();
        int pluginId = 7840;
        new Metrics_cStats(this);
        new Metrics(this, pluginId);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void regListener(){
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new OtherListener(), this);
    }
    public static FarmHunter getIns() {
        return ins;
    }

    public ArrayList<Arena> getArena() {
        return arena;
    }

    public Arena getArenaName(String name) {
        for (Arena arena : getArena()) {
            if (arena.getName().equalsIgnoreCase(name)){
                return arena;
            }
        }
        return null;
    }

    private void load(){
        checkFile();
        if (arenafile.getConfigurationSection("ArenaList") == null){
            return;
        }
        for (String Arena : arenafile.getConfigurationSection("ArenaList").getKeys(false)) {
            Arena arenas = new Arena(Arena);
            getArena().add(arenas);
        }
    }
    private void checkFile(){
        if (!(ArenaFile.exists())) {
            System.out.println("重新加载arenas.yml文件");
            ArenaFile.getParentFile().mkdirs();
            saveResource("arenas.yml", false);
        }
        if (!(ItemFile.exists())) {
            System.out.println("重新加载item.yml文件");
            ItemFile.getParentFile().mkdirs();
            saveResource("item.yml", false);
        }
    }

}

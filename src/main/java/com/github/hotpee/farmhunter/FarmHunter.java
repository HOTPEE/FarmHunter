package com.github.hotpee.farmhunter;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Commands.CommandManager;
import com.github.hotpee.farmhunter.Listeners.DamageListener;
import com.github.hotpee.farmhunter.Listeners.InteractListener;
import com.github.hotpee.farmhunter.Listeners.OtherListener;
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

    private ArrayList<Arena> arena = new ArrayList<>();
    public CommandManager commandManager;

    @Override
    public void onEnable() {
        ins = this;
        load();
        saveDefaultConfig();
        regListener();
        mainLobby = getConfig().getLocation("MainLobby");
        commandManager = new CommandManager();
        commandManager.setUp();

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
            getLogger().info("" + getArena().size());
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

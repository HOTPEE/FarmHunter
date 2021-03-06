package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetLobbyCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (!(player.hasPermission("FarmHunter.Admin"))){
            Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getNoPermission());
            return;
        }
        if (args.length != 2){
            Util.Message(player, ConfigManager.getPrefix() + "&c参数不正确，请检查你的参数");
            return;
        }
        String name = args[1];
        ConfigurationSection sec = FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList." + name);
        Util.setLocation(sec.createSection("lobbySpawn"), player.getLocation());
        try {
            FarmHunter.getIns().arenafile.save(FarmHunter.getIns().ArenaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getLobbySetUp());
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.setLobby;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

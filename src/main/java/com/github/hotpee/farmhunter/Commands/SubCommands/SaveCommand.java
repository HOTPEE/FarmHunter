package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SaveCommand extends SubCommand {
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
        Util.Message(player, ConfigManager.getPrefix() + "&b成功保存该竞技场");
        for (String Arena : FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList").getKeys(false)) {
            Arena arena = new Arena(Arena);
            if (FarmHunter.getIns().getArena().contains(arena)){
                return;
            }
            FarmHunter.getIns().getArena().add(arena);
        }
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.save;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

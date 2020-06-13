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

public class SetMaxPlayersCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (!(player.hasPermission("FarmHunter.Admin"))){
            Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getNoPermission());
            return;
        }
        if (args.length != 3){
            Util.Message(player, ConfigManager.getPrefix() + "&c参数不正确，请检查你的参数");
            return;
        }
        String name = args[1];
        try {
            ConfigurationSection sec = FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList." + name);
            sec.set("maxPlayers", Integer.valueOf(args[2]));
            try {
                FarmHunter.getIns().arenafile.save(FarmHunter.getIns().ArenaFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e){
            Util.Message(player,ConfigManager.getPrefix() + "§4发生了严重错误，请查看后台报错");
            e.printStackTrace();
        }
        Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getMaxPlayerSetUp().replaceAll("<0>", String.valueOf(args[2])));
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.setMaxPlayers;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

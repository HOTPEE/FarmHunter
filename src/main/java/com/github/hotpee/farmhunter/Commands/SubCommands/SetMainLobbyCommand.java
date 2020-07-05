package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SetMainLobbyCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (!(player.hasPermission("FarmHunter.Admin"))){
            Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getNoPermission());
            return;
        }
        if (args.length != 1){
            Util.Message(player, ConfigManager.getPrefix() + "&c参数不正确，请检查你的参数");
            return;
        }
        Util.setLocation(FarmHunter.getIns().getConfig().createSection("MainLobby"), player.getLocation());
        Util.Message(player, ConfigManager.getPrefix() + "&b成功设置总大厅");
        FarmHunter.getIns().saveConfig();
        FarmHunter.getIns().mainLobby = Util.getLocation(FarmHunter.getIns().getConfig().getConfigurationSection("MainLobby"));
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.setMainLobby;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

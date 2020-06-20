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

public class SetSpawnCommand extends SubCommand {
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
        try {
            String name = args[1];
            String type = args[2];
            ConfigurationSection sec;
            switch (type){
                case "SeekerWaitSpawn":
                    Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getSpawnSetUp().replaceAll("<0>", "寻找者等待出生点"));
                    sec = FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList." + name);
                    sec.set("seekerWaitSpawn", player.getLocation());
                    try {
                        FarmHunter.getIns().arenafile.save(FarmHunter.getIns().ArenaFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "SeekerSpawn":
                    Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getSpawnSetUp().replaceAll("<0>", "寻找者出生点"));
                    sec = FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList." + name);
                    sec.set("seekerSpawn", player.getLocation());
                    try {
                        FarmHunter.getIns().arenafile.save(FarmHunter.getIns().ArenaFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "HiderSpawn":
                    Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getSpawnSetUp().replaceAll("<0>", "躲藏者出生点"));
                    sec = FarmHunter.getIns().arenafile.getConfigurationSection("ArenaList." + name);
                    sec.set("hiderSpawn", player.getLocation());
                    try {
                        FarmHunter.getIns().arenafile.save(FarmHunter.getIns().ArenaFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Util.Message(player, ConfigManager.getPrefix() + "&b设置失败! 未知的出生点类型&7(SeekerWaitSpawn/HiderSpawn/SeekerSpawn)");
                    break;
            }
        } catch (StringIndexOutOfBoundsException e){
            Util.Message(player,ConfigManager.getPrefix() + "§4发生了严重错误，请查看后台报错");
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.setSpawn;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

package com.github.hotpee.farmhunter.Util;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.FarmHunter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
    public static void Message(CommandSender p, String s){
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
    public static void MessageTitle(Player p, String t, String s){
        p.sendTitle(ChatColor.translateAlternateColorCodes('&', t), ChatColor.translateAlternateColorCodes('&', s), 10, 50, 10);
    }

    public static Arena getArena(Player player) {
        for (Arena arena : FarmHunter.getIns().getArena()) {
            if (arena.checkPlayer(player))
                return arena;
        }
        return null;
    }

    public static void Help(Player player) {
        if (player.isOp()) {
            Util.Message(player, "&7&l---------------------------------");
            Util.Message(player, "&bFarmHunter 主命令帮助");
            Util.Message(player, "&a");
            Util.Message(player, " &3/farmhunter help  -  &c查看插件所有命令");
            Util.Message(player, " &3/farmhunter create <name>  -  &c创建一个新的竞技场");
            Util.Message(player, " &3/farmhunter join <name>  -  &c加入一个竞技场");
            Util.Message(player, " &3/farmhunter leave  -  &c离开一个竞技场");
            Util.Message(player, " &3/farmhunter setMinPlayers <name> <number>  -  &c设置某个竞技场最大玩家数量");
            Util.Message(player, " &3/farmhunter setMaxPlayers <name> <number>  -  &c设置某个竞技场最大玩家数量");
            Util.Message(player, " &3/farmhunter setTime <name> <number>  -  &c设置游戏总时长(单位: 秒)");
            Util.Message(player, " &3/farmhunter setSpawn <name> <type>  -  &c设置 SeekerWaitSpawn 寻找者开局等待出生点 / SeekerSpawn 寻找者出生点 / HiderSpawn 躲藏者出生点");
            Util.Message(player, " &3/farmhunter setLobby <name>  -  &c设置游戏大厅出生点");
            Util.Message(player, " &3/farmhunter setMainLobby  -  &c设置游戏主大厅出生点");
            Util.Message(player, " &3/farmhunter reload  -  &c重载插件");
            Util.Message(player, "&a");
            Util.Message(player, "&7&l--------------------------------");
        } else {
            Util.Message(player, "&7&l---------------------------------");
            Util.Message(player, "&bFarmHunter 主命令帮助");
            Util.Message(player, "&a");
            Util.Message(player, " &3/farmhunter help  -  &c查看插件所有命令");
            Util.Message(player, " &3/farmhunter join <name>  -  &c加入一个竞技场");
            Util.Message(player, " &3/farmhunter leave  -  &c离开一个竞技场");
            Util.Message(player, "&a");
            Util.Message(player, "&7&l--------------------------------");
        }
    }
}

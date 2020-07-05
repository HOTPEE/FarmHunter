package com.github.hotpee.farmhunter.ConfigManager;

import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Teams;
import org.bukkit.ChatColor;

import java.util.List;

public class ConfigManager {
    public static String getPrefix(){
        return FarmHunter.getIns().getConfig().getString("Prefix").replaceAll("§","&");
    }
    public static int getLobbyCooldown(){
        return FarmHunter.getIns().getConfig().getInt("LobbyCooldown");
    }
    public static String getServerName(){
        return FarmHunter.getIns().getConfig().getString("BungeeCordHub");
    }
    public static boolean isBungee(){
        return FarmHunter.getIns().getConfig().getBoolean("BungeeCordMode");
    }

    public static String getNoPermission(){
        return FarmHunter.getIns().getConfig().getString("Language.noPermission").replaceAll("§","&");
    }
    public static String getPluginReloaded(){
        return FarmHunter.getIns().getConfig().getString("Language.pluginReloaded").replaceAll("§","&");
    }
    public static String getArenaCreated(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaCreated").replaceAll("§","&");
    }
    public static String getArenaNotExist(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaNotExist").replaceAll("§","&");
    }
    public static String getArenaLimited(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaLimited").replaceAll("§","&");
    }
    public static String getArenaStarted(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaStarted").replaceAll("§","&");
    }
    public static String getLeave(){
        return FarmHunter.getIns().getConfig().getString("Language.leave").replaceAll("§","&");
    }
    public static String getJoin(){
        return FarmHunter.getIns().getConfig().getString("Language.join").replaceAll("§","&");
    }
    public static String getPlayersLeave(){
        return FarmHunter.getIns().getConfig().getString("Language.playersLeave").replaceAll("§","&");
    }
    public static String getPlayersJoin(){
        return FarmHunter.getIns().getConfig().getString("Language.playersJoin").replaceAll("§","&");
    }
    public static String getItemCooldown(){
        return FarmHunter.getIns().getConfig().getString("Language.itemCooldown").replaceAll("§","&");
    }
    public static String getLobbyCooldownMsg(){
        return FarmHunter.getIns().getConfig().getString("Language.lobbyCooldown").replaceAll("§","&");
    }
    public static String getGameStart(){
        return FarmHunter.getIns().getConfig().getString("Language.gameStart").replaceAll("§","&");
    }
    public static String getSeekerStarted(){
        return FarmHunter.getIns().getConfig().getString("Language.seekerStarted").replaceAll("§","&");
    }
    public static String getGameOver(){
        return FarmHunter.getIns().getConfig().getString("Language.gameOver").replaceAll("§","&");
    }
    public static String getInGame(){
        return FarmHunter.getIns().getConfig().getString("Language.inGame").replaceAll("§","&");
    }
    public static String getArenaNotEnough(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaNotEnough").replaceAll("§","&");
    }
    public static String getMaxPlayerSetUp(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaMaxPlayers").replaceAll("§","&");
    }
    public static String getMinPlayerSetUp(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaMinPlayers").replaceAll("§","&");
    }
    public static String getTimeSetUp(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaTime").replaceAll("§","&");
    }
    public static String getSpawnSetUp(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaSpawn").replaceAll("§","&");
    }
    public static String getLobbySetUp(){
        return FarmHunter.getIns().getConfig().getString("Language.arenaLobby").replaceAll("§","&");
    }


    public static String getScorePrefix(){
        return FarmHunter.getIns().getConfig().getString("ScoreBoard.Prefix").replaceAll("§","&");
    }
    public static List<String> getScoreLobbyLore(){
        List<String> list = FarmHunter.getIns().getConfig().getStringList("ScoreBoard.ScoreBoard-Lobby.List");
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;

    }
    public static List<String> getScoreStartingLore(){
        List<String> list = FarmHunter.getIns().getConfig().getStringList("ScoreBoard.ScoreBoard-Starting.List");
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;

    }
    public static List<String> getScoreGameLore(){
        List<String> list = FarmHunter.getIns().getConfig().getStringList("ScoreBoard.ScoreBoard-Game.List");
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;

    }

}

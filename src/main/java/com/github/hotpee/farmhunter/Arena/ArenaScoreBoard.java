package com.github.hotpee.farmhunter.Arena;

import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Task.GameLeftTimeTask;
import com.github.hotpee.farmhunter.Task.LobbyCooldownTask;
import com.github.hotpee.farmhunter.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ArenaScoreBoard {
    private static List<String> scoreboards;
    private static ScoreboardManager sm;
    private static Scoreboard s;
    private static Objective o;

    public static void lobbyScoreBoard(LobbyCooldownTask lct, Arena arena){
        scoreboards = ConfigManager.getScoreLobbyLore();
        sm = Bukkit.getScoreboardManager();
        s = sm.getNewScoreboard();
        o = s.registerNewObjective("FarmHunter", "dummy", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigManager.getScorePrefix()));
        for (int i = 0; i < scoreboards.size(); i++) {
            setSlot(i, scoreboards.get(i)
            .replace("<1>", String.valueOf(arena.getPlayerAmount().size()))
            .replace("<2>", String.valueOf(arena.getMaxPlayers()))
            );
        }
        for (Player player : arena.getPlayerAmount().keySet()) {
            player.setScoreboard(s);
        }
    }

    public static void CountdownScoreBoard(LobbyCooldownTask lct, Arena arena){
        scoreboards = ConfigManager.getScoreStartingLore();
        sm = Bukkit.getScoreboardManager();
        s = sm.getNewScoreboard();
        o = s.registerNewObjective("FarmHunter", "dummy", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigManager.getScorePrefix()));
        for (int i = 0; i < scoreboards.size(); i++) {
            setSlot(i, scoreboards.get(i)
                    .replace("<0>", String.valueOf(lct.getTime()))
                    .replace("<1>", String.valueOf(arena.getPlayerAmount().size()))
                    .replace("<2>", String.valueOf(arena.getMaxPlayers()))
            );
        }
        for (Player player : arena.getPlayerAmount().keySet()) {
            player.setScoreboard(s);
        }

    }

    public static void GameScoreBoard(GameLeftTimeTask gltt, Arena arena, Player player){

        scoreboards = ConfigManager.getScoreGameLore();
        sm = Bukkit.getScoreboardManager();
        s = sm.getNewScoreboard();
        o = s.registerNewObjective("FarmHunter", "dummy", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigManager.getScorePrefix()));
        for (int i = 0; i < scoreboards.size(); i++) {
            String role = arena.getPlayerAmount().get(player) == Teams.HIDER ? "躲避者" : "寻找者";
            int m = (gltt.getTime() % 3600) / 60;
            int s = (gltt.getTime() % 3600) % 60;
            setSlot(i, scoreboards.get(i)
                    .replace("<0>", role)
                    .replace("<1>", m + ":" + s)
                    .replace("<2>", String.valueOf(arena.getPlayerAmount().size()))
                    .replace("<3>", String.valueOf(arena.getMaxPlayers()))
            );

        }
        player.setScoreboard(s);

    }

    private static void setSlot(int slot, String text) {
        s.getObjective(DisplaySlot.SIDEBAR).getScore(text).setScore(Math.abs(slot - scoreboards.size()));
    }


}

package com.github.hotpee.farmhunter.Task;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Arena.ArenaScoreBoard;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyCooldownTask extends BukkitRunnable {
    private int time;
    private Arena arena;
    public LobbyCooldownTask(Arena arena){
        this.arena = arena;
        this.time = ConfigManager.getLobbyCooldown();
    }


    @Override
    public void run() {
        ArenaScoreBoard.CountdownScoreBoard(this, arena);
        if (time == 60){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getLobbyCooldownMsg().replaceAll("<0>", String.valueOf(time)));
            }
        }
        if (time == 30){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getLobbyCooldownMsg().replaceAll("<0>", String.valueOf(time)));
            }
        }
        if (time == 10){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getLobbyCooldownMsg().replaceAll("<0>", String.valueOf(time)));
                players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
        if (time <= 5){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getLobbyCooldownMsg().replaceAll("<0>", String.valueOf(time)));
                players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
        }
        if (time == 1){
            arena.addTeams();
        }
        if (time == 0){
            arena.startGame();
            cancel();
        }
        for (Player players : arena.getPlayerAmount().keySet()) {
            players.setLevel(time);
        }
        time--;
    }

    public int getTime() {
        return time;
    }
}

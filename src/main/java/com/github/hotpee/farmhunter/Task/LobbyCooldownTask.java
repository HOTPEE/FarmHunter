package com.github.hotpee.farmhunter.Task;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyCooldownTask extends BukkitRunnable {
    private int time = ConfigManager.getLobbyCooldown();
    private Arena arena;
    public LobbyCooldownTask(Arena arena){
        this.arena = arena;
    }


    @Override
    public void run() {
        if (arena.getPlayerAmount().size() < arena.getMinPlayers()){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getArenaNotEnough().replaceAll("<0>", String.valueOf(time)));
            }
            cancel();
        }
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
}

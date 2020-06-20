package com.github.hotpee.farmhunter.Task;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Arena.ArenaScoreBoard;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class GameLeftTimeTask extends BukkitRunnable {

    private int time;
    private int Totaltime;
    private Arena arena;
    public GameLeftTimeTask(Arena arena){
        this.arena = arena;
        this.time = arena.getTime();
        this.Totaltime = arena.getTime();
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        for (Player players : arena.getPlayerAmount().keySet()){
            ArenaScoreBoard.GameScoreBoard(this, arena, players);
        }
        if (time == Math.floor(Totaltime / 2.0)){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.MessageTitle(players, "&b", "&b距离游戏结束还有 &7" + time + " &b秒!");
                players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
            }
        }
        if (time == Math.floor(Totaltime / 4.0)){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.MessageTitle(players, "&b", "&b距离游戏结束还有 &7" + time + " &b秒!");
                players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
            }
        }
        if (time == 0){
            for (Player players : arena.getPlayerAmount().keySet()) {
                Util.MessageTitle(players, "&b", "&b游戏结束");
            }
            arena.stopGame();
            cancel();
        }
        time--;

    }
}

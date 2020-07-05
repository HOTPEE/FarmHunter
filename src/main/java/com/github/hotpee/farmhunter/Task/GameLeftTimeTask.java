package com.github.hotpee.farmhunter.Task;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Arena.ArenaScoreBoard;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class GameLeftTimeTask extends BukkitRunnable {

    private int time;
    private int Totaltime;
    private Arena arena;
    private BossBar bb;
    private int waitCount;
    public GameLeftTimeTask(Arena arena){
        this.arena = arena;
        this.time = arena.getTime();
        this.Totaltime = arena.getTime();
        int waitCount = FarmHunter.getIns().getConfig().getInt("SeekerWait");
        this.bb = Bukkit.createBossBar("§6§l剩余时间: §b" + this.time, BarColor.YELLOW, BarStyle.SOLID);
        for (Player players : arena.getPlayerAmount().keySet()){
            bb.addPlayer(players);
        }
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        if (Totaltime > time){
            if (time > Totaltime - FarmHunter.getIns().getConfig().getInt("SeekerWait")) {
                for (Player players : arena.getPlayerAmount().keySet()){
                    Util.send(players, ConfigManager.getPrefix() + ConfigManager.getSeekerStarted().replaceAll("<0>", Integer.toString(waitCount)));
                    waitCount--;
                }
            }
        }
        bb.setTitle("§6§l剩余时间: §e§l" + this.time + " §6§l秒");
        bb.setProgress((double)time / Totaltime);
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

    public BossBar getBb() {
        return bb;
    }
}


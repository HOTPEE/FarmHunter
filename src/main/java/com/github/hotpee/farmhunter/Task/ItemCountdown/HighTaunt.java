package com.github.hotpee.farmhunter.Task.ItemCountdown;

import com.github.hotpee.farmhunter.FarmHunter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class HighTaunt {
    public static HashMap<Player, Integer> cooldown = new HashMap<>();
    public static void setCooldown(Player player, int seconds) {
        cooldown.put(player, seconds);
        new BukkitRunnable(){
            @Override
            public void run() {
                cooldown.put(player, cooldown.get(player) - 1);
                if (cooldown.get(player) == 0){
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(FarmHunter.getIns(), 0L, 20L);
    }

    public static int getCooldown(Player player) {
        return cooldown.getOrDefault(player, 0);
    }
}

package com.github.hotpee.farmhunter.Task.ItemCountdown;

import com.github.hotpee.farmhunter.FarmHunter;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class TntSheep {
    public static HashMap<Player, Integer> cooldown = new HashMap<>();
    public static HashMap<Player, Integer> tntcooldown = new HashMap<>();
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

    public static void startTntSheep(Player player, int seconds){
        tntcooldown.put(player, seconds);
        Sheep sheep = (Sheep) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.SHEEP);
        sheep.setAI(false);
        sheep.setColor(DyeColor.RED);
        new BukkitRunnable(){
            @Override
            public void run() {
                tntcooldown.put(player, tntcooldown.get(player) - 1);
                sheep.setCustomName("§b§l爆炸倒计时: " + tntcooldown.getOrDefault(player,  3));
                if (tntcooldown.get(player) == 0){
                    sheep.remove();
                    sheep.getLocation().getWorld().createExplosion(sheep.getLocation(), 5);
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(FarmHunter.getIns(), 0L, 20L);
    }
}

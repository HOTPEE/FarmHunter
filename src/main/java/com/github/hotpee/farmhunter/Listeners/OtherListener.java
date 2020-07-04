package com.github.hotpee.farmhunter.Listeners;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class OtherListener implements Listener {
    @EventHandler
    public void joinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (!(ConfigManager.isBungee())){
            return;
        }
        if (!(FarmHunter.getIns().getArena().size() == 1)){
            return;
        }
        Arena arena = FarmHunter.getIns().getArena().get(0);
        if (FarmHunter.BungeeState){
            p.kickPlayer("§c加入失败! 游戏已经开启!");
            return;
        }
        arena.addPlayers(p);
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        if (!(arena.isStates())){
            return;
        }
        arena.leaveGame(p);
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        p.closeInventory();
        e.setCancelled(true);
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        e.setCancelled(true);
    }
    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if (ConfigManager.isBungee()){
            if (FarmHunter.BungeeState){
                e.setMotd(FarmHunter.getIns().getConfig().getString("BungeeCordMotd.InGame"));
            } else {
                e.setMotd(FarmHunter.getIns().getConfig().getString("BungeeCordMotd.Waiting"));
            }
        }

    }
}

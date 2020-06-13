package com.github.hotpee.farmhunter.Listeners;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OtherListener implements Listener {
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
}

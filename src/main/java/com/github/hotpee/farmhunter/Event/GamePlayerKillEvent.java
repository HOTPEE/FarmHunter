package com.github.hotpee.farmhunter.Event;

import com.github.hotpee.farmhunter.Arena.Arena;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GamePlayerKillEvent extends Event {
    private Arena arena;
    private Player victim;
    private Player killer;
    private static final HandlerList handlers = new HandlerList();

    public GamePlayerKillEvent(Arena arena, Player victim, Player killer) {
        this.arena = arena;
        this.victim = victim;
        this.killer = killer;
    }

    public Player getVictim() {
        return victim;
    }

    public Player getKiller() {
        return killer;
    }

    public Arena getArena() {
        return this.arena;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}

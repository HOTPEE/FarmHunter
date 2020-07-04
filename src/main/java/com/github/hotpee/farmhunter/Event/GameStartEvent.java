package com.github.hotpee.farmhunter.Event;

import com.github.hotpee.farmhunter.Arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event {
    private Arena arena;
    private static final HandlerList handlers = new HandlerList();

    public GameStartEvent(Arena arena) {
        this.arena = arena;
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

package com.github.hotpee.farmhunter.Event;

import com.github.hotpee.farmhunter.Arena.Arena;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DisguiseChangeEvent extends Event {
    private Arena arena;
    private MobDisguise disguise;
    private static final HandlerList handlers = new HandlerList();

    public DisguiseChangeEvent(Arena arena, MobDisguise disguise) {
        this.arena = arena;
        this.disguise = disguise;
    }

    public MobDisguise getDisguise() {
        return disguise;
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

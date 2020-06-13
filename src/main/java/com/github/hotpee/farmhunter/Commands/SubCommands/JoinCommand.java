package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

public class JoinCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length != 2){
            Util.Message(player, ConfigManager.getPrefix() + "&c参数不正确，请检查你的参数");
            return;
        }
        Arena arena = FarmHunter.getIns().getArenaName(args[1]);
        if (arena == null){
            Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getArenaNotExist());
            return;
        }
        arena.addPlayers(player);
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.join;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

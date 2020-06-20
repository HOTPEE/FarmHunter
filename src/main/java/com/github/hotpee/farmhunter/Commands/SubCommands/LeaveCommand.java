package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length != 1){
            Util.Message(player, ConfigManager.getPrefix() + "&c参数不正确，请检查你的参数");
            return;
        }
        Arena arena = Util.getArena(player);
        if (arena == null){
            Util.Message(player, ConfigManager.getPrefix() + "&b你没有在任何竞技场内");
            return;
        }
        arena.leaveGame(player);
        Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getLeave().replaceAll("<0>", arena.getName()));
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.leave;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

package com.github.hotpee.farmhunter.Commands.SubCommands;

import com.github.hotpee.farmhunter.Commands.SubCommand;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        Util.Help(player);
    }

    @Override
    public String name() {
        return FarmHunter.getIns().commandManager.help;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}

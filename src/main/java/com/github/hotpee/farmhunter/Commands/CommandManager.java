package com.github.hotpee.farmhunter.Commands;

import com.github.hotpee.farmhunter.Commands.SubCommands.*;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subcommand = new ArrayList<SubCommand>();

    public CommandManager(){

    }
    public String main = "farmhunter";
    public String help = "help";
    public String create= "create";
    public String join = "join";
    public String leave = "leave";
    public String setMaxPlayers = "setMaxPlayers";
    public String setMinPlayers = "setMinPlayers";
    public String setTime = "setTime";
    public String setSpawn = "setSpawn";
    public String setLobby = "setLobby";
    public String setMainLobby = "setMainLobby";
    public String save = "save";
    public String reload = "reload";

    public void setUp(){
        FarmHunter.getIns().getCommand(main).setExecutor(this);
        this.subcommand.add(new HelpCommand());
        this.subcommand.add(new CreateCommand());
        this.subcommand.add(new JoinCommand());
        this.subcommand.add(new LeaveCommand());
        this.subcommand.add(new SaveCommand());
        this.subcommand.add(new SetLobbyCommand());
        this.subcommand.add(new SetMainLobbyCommand());
        this.subcommand.add(new SetMaxPlayersCommand());
        this.subcommand.add(new SetMinPlayersCommand());
        this.subcommand.add(new SetSpawnCommand());
        this.subcommand.add(new SetTimeCommand());
        this.subcommand.add(new ReloadCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ConfigManager.getPrefix().replaceAll("&", "§") + "§c本插件只能是玩家运行");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase(main)){
            if (args.length == 0){
                Util.Help(player);
                return true;
            }
            SubCommand target = this.get(args[0]);
            if (target == null){
                Util.Message(player,ConfigManager.getPrefix() + "§c警告，未知指令");
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{
                target.onCommand(player,args);
            } catch (Exception e){
                Util.Message(player,ConfigManager.getPrefix() + "§4发生了严重错误，请查看后台报错");
                e.printStackTrace();
            }
        }



        return true;
    }

    private SubCommand get(String name){
        Iterator<SubCommand> sCommands = this.subcommand.iterator();

        while(sCommands.hasNext()){
            SubCommand sc = (SubCommand) sCommands.next();

            if (sc.name().equalsIgnoreCase(name)){
                return sc;
            }
            String [] aliases;
            int length = (aliases = sc.aliases()).length;
            for(int var5 = 0; var5 < length; ++var5){
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)){
                    return sc;
                }

            }
        }
        return null;
    }
}

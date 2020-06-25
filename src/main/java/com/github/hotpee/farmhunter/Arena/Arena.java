package com.github.hotpee.farmhunter.Arena;

import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.FarmHunter;
import com.github.hotpee.farmhunter.Task.GameLeftTimeTask;
import com.github.hotpee.farmhunter.Task.LobbyCooldownTask;
import com.github.hotpee.farmhunter.Teams;
import com.github.hotpee.farmhunter.Util.ItemUtil;
import com.github.hotpee.farmhunter.Util.Util;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public class Arena {
    private static File ArenaFile = new File(FarmHunter.getIns().getDataFolder(), "arenas.yml");
    LobbyCooldownTask lct;
    FileConfiguration arena = YamlConfiguration.loadConfiguration(ArenaFile);
    private Map<Player, Teams> playerAmount = new HashMap<>();
    private Boolean States = false;
    private String name;
    private int maxPlayers;
    private int minPlayers;
    private int time;
    private Location HiderSpawn = null;
    private Location SeekerSpawn = null;
    private Location SeekerWaitSpawn = null;
    private Location lobbyLocation = null;
    private GameLeftTimeTask gltt;
    private String Winner;

    public Arena(String name){
        this.name = name;
        this.maxPlayers = arena.getInt("ArenaList." + name + ".maxPlayers");
        this.minPlayers = arena.getInt("ArenaList." + name + ".minPlayers");
        this.time = arena.getInt("ArenaList." + name + ".time");
        this.HiderSpawn = Util.getLocation(arena.getConfigurationSection("ArenaList." + name + ".hiderSpawn"));
        this.SeekerSpawn = Util.getLocation(arena.getConfigurationSection("ArenaList." + name + ".seekerSpawn"));
        this.SeekerWaitSpawn = Util.getLocation(arena.getConfigurationSection("ArenaList." + name + ".seekerWaitSpawn"));
        this.lobbyLocation = Util.getLocation(arena.getConfigurationSection("ArenaList." + name + ".lobbySpawn"));
    }

    public void addTeams(){
        Player[] seeker = getPlayerAmount().keySet().toArray(new Player[0]);
        for (int i = 0; i < SeekerAmount(); i++) {
            Random random = new Random();
            Player seeker1 = seeker[random.nextInt(seeker.length)];
            playerAmount.put(seeker1, Teams.SEEKER);
        }

    }

    public void addPlayers(Player player){
        if (playerAmount.size() == this.maxPlayers){
            Util.Message(player,ConfigManager.getPrefix() + ConfigManager.getArenaLimited());
            return;
        }
        if (isStates()){
            Util.Message(player,ConfigManager.getPrefix() + ConfigManager.getArenaStarted());
            return;
        }
        if (playerAmount.containsKey(player)){
            Util.Message(player,ConfigManager.getPrefix() + ConfigManager.getInGame());
            return;
        }
        player.setHealth(20.0);
        player.teleport(lobbyLocation);
        ItemUtil.sendLobbyItem(player, "LeaveGame");
        Util.Message(player, ConfigManager.getPrefix() + ConfigManager.getJoin().replaceAll("<0>", this.getName()));
        playerAmount.put(player, Teams.HIDER);
        for (Player players : playerAmount.keySet()) {
            Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getPlayersJoin().replaceAll("<0>", String.valueOf(playerAmount.size())).replaceAll("<1>", String.valueOf(this.getMaxPlayers())));
        }
        if (playerAmount.size() >= this.minPlayers){
            player.removeScoreboardTag("FarmHunter");
            startCount();
        }
        player.removeScoreboardTag("FarmHunter");
        ArenaScoreBoard.lobbyScoreBoard(lct, this);

    }
    public void leaveGame(Player player){
        player.teleport(FarmHunter.getIns().mainLobby);
        player.getInventory().clear();
        player.setLevel(0);
        playerAmount.remove(player);
        for (Player players : playerAmount.keySet()) {
            Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getPlayersLeave().replaceAll("<0>", String.valueOf(playerAmount.size())).replaceAll("<1>", String.valueOf(this.getMaxPlayers())));
        }
        if (isStates()){
            if (playerAmount.size() < this.minPlayers){
                stopGame();
            }
        }
        if (ConfigManager.isBungee()){
            Util.send(player, ConfigManager.getServerName());
            return;
        }

    }

    public void startCount() {
        lct = new LobbyCooldownTask(this);
        lct.runTaskTimer(FarmHunter.getIns(), 0L, 20L);
    }

    public void startGame(){
        gltt = new GameLeftTimeTask(this);
        gltt.runTaskTimer(FarmHunter.getIns(), 0L, 20L);
        if (ConfigManager.isBungee()){
            FarmHunter.BungeeState = true;
        }
        setStates(true);
        for (Player players : playerAmount.keySet()) {
            Util.Message(players, ConfigManager.getPrefix() + ConfigManager.getGameStart());
            if (playerAmount.get(players) == Teams.HIDER){
                players.teleport(HiderSpawn);
                players.setGameMode(GameMode.ADVENTURE);
                ItemUtil.sendHiderItem(players, "SwapWand", "LowTaunt", "HighTaunt", "LocationTaunt");
                MobDisguise dis = new MobDisguise(DisguiseType.COW);
                dis.setEntity(players);
                dis.startDisguise();
                dis.setViewSelfDisguise(false);
                Util.MessageTitle(players, "&7", "&b身份： &e" + Teams.HIDER.name() + ", &b躲起来!!!!!");
            }
            if (playerAmount.get(players) == Teams.SEEKER){
                players.setGameMode(GameMode.ADVENTURE);
                players.teleport(SeekerWaitSpawn);
                ItemUtil.sendSeekerItem(players, "TntSheep");
                Util.MessageTitle(players, "&7", "&b身份: &e" + Teams.SEEKER.name() + " &b找到所有动物并收归麾下");
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (isStates()){
                            players.teleport(SeekerSpawn);
                            if (playerAmount.get(players) == Teams.HIDER){
                                Util.MessageTitle(players, "&7", "&a寻找者现在已经出来了");
                            }
                            if (playerAmount.get(players) == Teams.SEEKER){
                                Util.MessageTitle(players, "&7", "&a开始你的狩猎");
                            }
                        }
                        cancel();
                    }
                }.runTaskLater(FarmHunter.getIns(), FarmHunter.getIns().getConfig().getInt("SeekerWait") * 20);
            }
        }
    }
    public void stopGame(){
        Winner = getPlayerAmount().containsValue(Teams.HIDER) ? "躲避者" : "寻找者";
        Iterator<Player> iterator = playerAmount.keySet().iterator();
        while (iterator.hasNext()) {
            Player players = iterator.next();
            players.teleport(FarmHunter.getIns().mainLobby);
            players.getInventory().clear();
            getGameOverInfo();
            DisguiseAPI.undisguiseToAll(players);
            iterator.remove();
        }
        gltt.cancel();
        if (ConfigManager.isBungee()){
            new BukkitRunnable(){

                @Override
                public void run() {
                    FarmHunter.getIns().getServer().shutdown();
                }
            }.runTaskLater(FarmHunter.getIns(), 40L);
        }
        setStates(false);
    }
    public int SeekerAmount(){
        return (int) Math.ceil(getPlayerAmount().size() / 3.0);
    }
    public void getGameOverInfo(){
        List<String> list = FarmHunter.getIns().getConfig().getStringList("Language.gameOverInfo");
        for (String s : list) {
            broadcastMessage(s
                    .replaceAll("&", "§")
                    .replaceAll("<0>", Winner));
        }
    }
    public void broadcastMessage(String message) {
        for (Player player : getPlayerAmount().keySet()) {
            if (player != null) {
                Util.Message(player, message);
            }
        }
    }

    public Map<Player, Teams> getPlayerAmount() {
        return playerAmount;
    }

    public boolean checkPlayer(Player p) {
        return playerAmount.containsKey(p);
    }

    public Boolean isStates() {
        return States;
    }
    public void setStates(Boolean States) {
        this.States = States;
    }


    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getTime() {
        return time;
    }

    public Location getSeekerSpawn() {
        return SeekerSpawn;
    }


}

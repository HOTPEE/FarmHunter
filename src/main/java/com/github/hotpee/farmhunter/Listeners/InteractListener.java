package com.github.hotpee.farmhunter.Listeners;

import com.github.hotpee.farmhunter.Arena.Arena;
import com.github.hotpee.farmhunter.ConfigManager.ConfigManager;
import com.github.hotpee.farmhunter.ConfigManager.ItemManager;
import com.github.hotpee.farmhunter.Event.DisguiseChangeEvent;
import com.github.hotpee.farmhunter.Event.GameOverEvent;
import com.github.hotpee.farmhunter.Task.ItemCountdown.HighTaunt;
import com.github.hotpee.farmhunter.Task.ItemCountdown.LocationTaunt;
import com.github.hotpee.farmhunter.Task.ItemCountdown.LowTaunt;
import com.github.hotpee.farmhunter.Task.ItemCountdown.TntSheep;
import com.github.hotpee.farmhunter.Teams;
import com.github.hotpee.farmhunter.Util.Util;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.events.DisguiseInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class InteractListener implements Listener {
    @EventHandler
    public void Swap(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        if (!(arena.isStates())){
            return;
        }
        if (p.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals(ItemManager.getItemName("SwapWand"))){
            if (e.getRightClicked() instanceof Player){
                return;
            }
            MobDisguise dis = new MobDisguise(DisguiseType.getType(e.getRightClicked()));
            p.playEffect(p.getLocation(), Effect.SMOKE, 500);
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            dis.setEntity(p);
            dis.startDisguise();
            dis.setViewSelfDisguise(false);
            Bukkit.getPluginManager().callEvent(new DisguiseChangeEvent(arena, dis));
            Util.MessageTitle(p, "&b", "&7你已变成了 " + e.getRightClicked().getName());
        }
    }
    @EventHandler
    public void Swap2(DisguiseInteractEvent e){
        Player p = e.getPlayer();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        if (!(arena.isStates())){
            return;
        }
        if (p.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals(ItemManager.getItemName("SwapWand"))){
            MobDisguise dis = new MobDisguise(DisguiseType.getType((Entity) e.getDisguise()));
            p.playEffect(p.getLocation(), Effect.SMOKE, 500);
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            dis.setEntity(p);
            dis.startDisguise();
            dis.setViewSelfDisguise(false);
            Util.MessageTitle(p, "&b", "&7你已变成了 " + ((Entity) e.getDisguise()).getName());
        }
    }

    @EventHandler
    public void GameInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Arena arena = Util.getArena(p);
        if (arena == null){
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().hasItemMeta()){
                if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.getItemName("LeaveGame"))){
                    arena.leaveGame(p);
                    e.setCancelled(true);
                }
            } else {
                return;
            }
        }
        if (!(arena.isStates())){
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.getItemName("LowTaunt"))){
                if (LowTaunt.getCooldown(p) == 0){
                    LowTaunt.setCooldown(p, ItemManager.getCooldown("LowTaunt"));
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CAT_AMBIENT, 3, 1);
                    Util.Message(p, ConfigManager.getPrefix() + "&b嘲讽成功!! 你已使用小声的嘲讽...");
                    e.setCancelled(true);
                } else {
                    Util.Message(p, ConfigManager.getPrefix() +
                            ConfigManager.getItemCooldown().replaceAll("<0>", String.valueOf(LowTaunt.getCooldown(p))));
                    e.setCancelled(true);
                }

            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.getItemName("TntSheep"))){
                if (TntSheep.getCooldown(p) == 0){
                    TntSheep.setCooldown(p, ItemManager.getCooldown("TntSheep"));
                    TntSheep.startTntSheep(p, 3);
                    e.setCancelled(true);
                } else {
                    Util.Message(p, ConfigManager.getPrefix() +
                            ConfigManager.getItemCooldown().replaceAll("<0>", String.valueOf(TntSheep.getCooldown(p))));
                    e.setCancelled(true);
                }

            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.getItemName("HighTaunt"))){
                if (HighTaunt.getCooldown(p) == 0){
                    HighTaunt.setCooldown(p, ItemManager.getCooldown("HighTaunt"));
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10, 1);
                    Util.Message(p, ConfigManager.getPrefix() + "&b嘲讽成功!! 你已使用大声的嘲讽...");
                    e.setCancelled(true);
                } else {
                    Util.Message(p, ConfigManager.getPrefix() +
                            ConfigManager.getItemCooldown().replaceAll("<0>", String.valueOf(HighTaunt.getCooldown(p))));
                    e.setCancelled(true);

                }

            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem().getItemMeta().getDisplayName().equals(ItemManager.getItemName("LocationTaunt"))){
                if (LocationTaunt.getCooldown(p) == 0){
                    LocationTaunt.setCooldown(p, ItemManager.getCooldown("LocationTaunt"));
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITCH_AMBIENT, 20, 1);
                    for (Player players : arena.getPlayerAmount().keySet()){
                        if (arena.getPlayerAmount().get(players) == Teams.SEEKER){
                            Util.Message(players, players.getName() + " &b-> &aCNMD! 我在这里: " +  p.getLocation().getBlockX() +  "," + p.getLocation().getBlockY() + "," + p.getLocation().getBlockZ());
                        }
                    }
                    Util.Message(p, ConfigManager.getPrefix() + "&b嘲讽成功!! 你已使用暴露坐标的嘲讽...");
                    e.setCancelled(true);
                } else {
                    Util.Message(p, ConfigManager.getPrefix() +
                            ConfigManager.getItemCooldown().replaceAll("<0>", String.valueOf(LocationTaunt.getCooldown(p))));
                    e.setCancelled(true);

                }

            }
        }
    }

}

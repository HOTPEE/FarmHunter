package com.github.hotpee.farmhunter.Util;

import com.github.hotpee.farmhunter.ConfigManager.ItemManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ItemUtil {
    Map<UUID, Map<String, Long>> cooldown = new HashMap<>();
    private static long lastTime;
    public void xxx(Player player){
        long now = System.currentTimeMillis();
        if (now - lastTime > 10 * 1000){
            lastTime = now;
            cooldown.get(player.getUniqueId()).put("TntSheep", now);
        }
    }
    public static void sendLobbyItem(Player player, String name){
        ItemStack is = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ItemManager.getItemName(name));
        List<String> list = ItemManager.getItemLore(name);
        im.setLore(list);
        is.setItemMeta(im);
        player.getInventory().clear();
        player.getInventory().setItem(8, is);
    }
    public static void sendSeekerItem(Player player, String name){
        ItemStack tnt;
        try {
            tnt = new SpawnEgg(EntityType.SHEEP).toItemStack(1);
        } catch (Exception ignored) {
            tnt = new ItemStack(Material.valueOf("SHEEP_SPAWN_EGG"), 1);
        }
        ItemStack Bow = new ItemStack(Material.BOW, 1);
        ItemMeta bow = Bow.getItemMeta();
        ItemMeta tntmeta = tnt.getItemMeta();
        tntmeta.setDisplayName(ItemManager.getItemName(name));
        List<String> list = ItemManager.getItemLore(name);
        tntmeta.setLore(list);
        tnt.setItemMeta(tntmeta);
        bow.setUnbreakable(true);
        Bow.setItemMeta(bow);
        Bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        Bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        player.getInventory().clear();
        player.getInventory().addItem(Bow);
        player.getInventory().setItem(8, tnt);
        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
        player.getInventory().setItem(4, new ItemStack(Material.DIAMOND_SWORD, 1));
        player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
        player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
    }

    public static void sendHiderItem(Player player, String mobang, String sc, String bc, String baoc){
        ItemStack moBang = new ItemStack(Material.BLAZE_ROD, 1);
        ItemStack smallTanut = new ItemStack(Material.REDSTONE, 1);
        ItemStack bigTanut = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemStack lTanut = new ItemStack(Material.CLAY_BALL, 1);
        ItemStack Bow = new ItemStack(Material.BOW, 1);
        ItemMeta smallTanutMeta = smallTanut.getItemMeta();
        ItemMeta bigTanutMeta = bigTanut.getItemMeta();
        ItemMeta lTanutMeta = lTanut.getItemMeta();
        ItemMeta moBangMeta = moBang.getItemMeta();
        ItemMeta bow = Bow.getItemMeta();
        bigTanutMeta.setDisplayName(ItemManager.getItemName(bc));
        lTanutMeta.setDisplayName(ItemManager.getItemName(baoc));
        smallTanutMeta.setDisplayName(ItemManager.getItemName(sc));
        moBangMeta.setDisplayName(ItemManager.getItemName(mobang));
        List<String> list = ItemManager.getItemLore(mobang);
        List<String> list2 = ItemManager.getItemLore(sc);
        List<String> list3 = ItemManager.getItemLore(bc);
        List<String> list4 = ItemManager.getItemLore(baoc);
        moBangMeta.setLore(list);
        smallTanutMeta.setLore(list2);
        bigTanutMeta.setLore(list3);
        lTanutMeta.setLore(list4);
        bigTanut.setItemMeta(bigTanutMeta);
        lTanut.setItemMeta(lTanutMeta);
        smallTanut.setItemMeta(smallTanutMeta);
        moBang.setItemMeta(moBangMeta);
        bow.setUnbreakable(true);
        Bow.setItemMeta(bow);
        player.getInventory().clear();
        player.getInventory().addItem(Bow);
        player.getInventory().setItem(1, moBang);
        player.getInventory().setItem(4, smallTanut);
        player.getInventory().setItem(5, bigTanut);
        player.getInventory().setItem(6, lTanut);
        player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
    }
}

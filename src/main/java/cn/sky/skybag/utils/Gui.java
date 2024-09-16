package cn.sky.skybag.utils;

import cn.sky.skybag.configs.BagsData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gui {

    public Gui(){
    }

    /*public static ItemStack getArrowItemStack() {
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta meta = arrow.getItemMeta();
        meta.setDisplayName(Color.Chat("&a返回"));
        arrow.setItemMeta(meta);
        return arrow;
    }*/

    public static ItemStack getBarrierClose() {
        ItemStack arrow = new ItemStack(Material.BARRIER,1);
        ItemMeta meta = arrow.getItemMeta();
        meta.setDisplayName(Color.Chat("&c关闭"));
        arrow.setItemMeta(meta);
        return arrow;
    }

    public static ItemStack getGrayGlass() {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(Color.Chat("&c你还未解锁此槽位"));
        glass.setItemMeta(meta);
        return glass;
    }

    public static ItemStack getNullGrayGlass() {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(Color.Chat(" "));
        glass.setItemMeta(meta);
        return glass;
    }

    public static Inventory createInventory(InventoryHolder holder, String title, ArrayList<ItemStack> items,Player player) {
        Inventory gui = Bukkit.createInventory(holder, 54, Color.Chat(title));
        int i;
        for(i = BagsData.getSlots(player); i < 54; i++) {
            if (i == 49) {
                gui.setItem(i, getBarrierClose());
            } else if (i >= 45 && i != 49){
                gui.setItem(i, getNullGrayGlass());
            } else {
                gui.setItem(i, getGrayGlass());
            }
        }
        for (i = 0; i < BagsData.getSlots(player); i++) {
            if (items != null && !items.isEmpty()) {
                if (i > items.size() - 1) {
                    gui.setItem(i, new ItemStack(Material.AIR));
                    continue;
                }
                if (items.get(i).equals(getGrayGlass())){
                    continue;
                }
                gui.setItem(i, items.get(i));
            }
        }
        return gui;
    }
}

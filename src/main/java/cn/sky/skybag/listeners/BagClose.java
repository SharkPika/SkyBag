package cn.sky.skybag.listeners;

import cn.sky.skybag.configs.BagsData;
import cn.sky.skybag.gui.BagHolder;
import cn.sky.skybag.utils.APHook;
import cn.sky.skybag.utils.AttributeUtils;
import cn.sky.skybag.utils.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BagClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e)
    {
        Inventory bag = e.getInventory();
        Player player = (Player) e.getPlayer();
        if (e.getInventory().getHolder() instanceof BagHolder) {
            BagsData.addData(player, this.getItems(bag));
            for (int i = 0; i < BagsData.getSlots(player); i++) {
                ItemStack item = e.getInventory().getItem(i);
                if (item == null || item.getType().equals(Material.AIR)){
                    APHook.takeAttribute(player, String.valueOf(i));
                    continue;
                }
                APHook.addAttribute(player, AttributeUtils.getAttributes(item), String.valueOf(i));
            }
        }
    }
    public ArrayList<ItemStack> getItems(Inventory inv) {
        ArrayList<ItemStack> items = new ArrayList<>();

        for(int i = 0; i < 45; i++) {
            if (inv.getContents()[i] != null && inv.getContents()[i].getType() != Material.AIR && !inv.getContents()[i].equals(Gui.getBarrierClose()) && !inv.getContents()[i].equals(Gui.getNullGrayGlass()) && !inv.getContents()[i].equals(Gui.getGrayGlass())) {
                items.add(inv.getContents()[i]);
            }
        }
        return items;
    }
}

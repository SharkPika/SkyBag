package cn.sky.skybag.listeners;

import cn.sky.skybag.gui.BagHolder;
import cn.sky.skybag.utils.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BagClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getHolder() instanceof BagHolder) {
            e.getRawSlot();
            if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)){
                return;
            }
            if (e.getCurrentItem().equals(Gui.getNullGrayGlass()) || e.getCurrentItem().equals(Gui.getGrayGlass())) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().equals(Gui.getBarrierClose())) {
                e.setCancelled(true);
                player.closeInventory();
            }
        }
    }
}

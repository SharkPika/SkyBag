package cn.sky.skybag;

import cn.sky.skybag.commands.OpenBag;
import cn.sky.skybag.configs.BagsData;
import cn.sky.skybag.gui.BagHolder;
import cn.sky.skybag.listeners.BagClick;
import cn.sky.skybag.listeners.BagClose;
import cn.sky.skybag.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SkyBag extends JavaPlugin implements Listener {

    public static SkyBag main;
    public int checkLength;

    @Override
    public void onLoad() {
        main = this;
    }

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        new BagsData();
        getCommand("skybag").setExecutor(new OpenBag());
        Bukkit.getPluginManager().registerEvents(this,this);
        Bukkit.getPluginManager().registerEvents(new BagClick(),this);
        Bukkit.getPluginManager().registerEvents(new BagClose(),this);
        this.checkLength = SkyBag.getInstance().getConfig().getString("Check").length();
        MsgUtils.msgConsole("&bSkyBag &7>> &a插件加载成功");
        MsgUtils.msgConsole("&bSkyBag &7>> &e作者&7: &5皮卡");
        MsgUtils.msgConsole("&bSkyBag &7>> &bQQ1127367472");
    }

    @Override
    public void onDisable() {
        MsgUtils.msgConsole("&bSkyBag &7>> &c插件卸载成功");
        MsgUtils.msgConsole("&bSkyBag &7>> &e作者&7: &5皮卡");
        MsgUtils.msgConsole("&bSkyBag &7>> &bQQ1127367472");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Inventory bag = Gui.createInventory(new BagHolder(), Color.Chat(SkyBag.getInstance().getConfig().getString("BagTitle")), BagsData.getItems(player),player);
        player.openInventory(bag);
        player.closeInventory();
        /*if (!BagsData.containsPlayer(player)) {
            BagsData.addData(player, new ArrayList<ItemStack>());
        }
        for (int i = 0; i < BagsData.getSlots(player); i++) {
            ItemStack item = (ItemStack) BagsData.getData().getList(player.getUniqueId().toString()).get(i);
            if (item == null || item.getType().equals(Material.AIR)){
                APHook.takeAttribute(player, String.valueOf(i));
                continue;
            }
            APHook.addAttribute(player, AttributeUtils.getAttributes(item), String.valueOf(i));
        }*/
    }

    public static SkyBag getInstance() {
        return main;
    }
}

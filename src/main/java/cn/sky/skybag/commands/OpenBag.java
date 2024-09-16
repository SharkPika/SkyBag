package cn.sky.skybag.commands;

import cn.sky.skybag.SkyBag;
import cn.sky.skybag.configs.BagsData;
import cn.sky.skybag.gui.BagHolder;
import cn.sky.skybag.utils.Color;
import cn.sky.skybag.utils.Gui;
import cn.sky.skybag.utils.MsgUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OpenBag implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 1 && args[0].equals("reload")){
                BagsData.configReload();
                MsgUtils.msgConsole("&a配置文件重载成功");
            }
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("skybag.use")){
            MsgUtils.msgPlayer(player,"&c你没有权限");
            return false;
        }
        if (args.length == 0) {
            Inventory bag = Gui.createInventory(new BagHolder(),Color.Chat(SkyBag.getInstance().getConfig().getString("BagTitle")), BagsData.getItems(player),player);
            player.openInventory(bag);
        }
        if (args.length == 1 && args[0].equals("reload")){
            if (player.hasPermission("skybag.reload")) {
                BagsData.configReload();
                MsgUtils.msgPlayer(player,"&a配置文件重载成功");
                return false;
            }
            MsgUtils.msgPlayer(player,"&c你没有权限");
        }
        if (args.length == 2 && args[0].equals("open")){
            if (player.hasPermission("skybag.openother")) {
                if (Bukkit.getPlayer(args[1]) == null) {
                    MsgUtils.msgPlayer(player,"&c玩家不存在或不在线");
                    return false;
                }
                Inventory bag = Gui.createInventory(new BagHolder(), Color.Chat(SkyBag.getInstance().getConfig().getString("BagTitle")), BagsData.getItems(Bukkit.getPlayer(args[1])), Bukkit.getPlayer(args[1]));
                player.openInventory(bag);
                return false;
            }
            MsgUtils.msgPlayer(player,"&c你没有权限");
        }
        return false;
    }
}
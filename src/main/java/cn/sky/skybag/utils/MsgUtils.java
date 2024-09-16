package cn.sky.skybag.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class MsgUtils {

    public MsgUtils(){
    }

    public static void msgConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(Color.Chat(msg));
    }

    public static void msgPlayer(Player player, String msg) {
        player.sendMessage(Color.Chat(msg));
    }

    public static boolean filter(String message, List<String> words) {
        String msg = wipeAllSpecialSymbol(message);
        for (String word : words) {
            if (msg.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String wipeAllSpecialSymbol(String pretreatment) {
        String symbol = "[\n`_\\\\~!-@#$%^&*()+=|{}':§;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？a-zA-Z0-9]";
        return pretreatment.replaceAll(symbol, "");
    }
}

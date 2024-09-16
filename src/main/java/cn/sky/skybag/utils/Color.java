package cn.sky.skybag.utils;

import net.md_5.bungee.api.ChatColor;

public class Color {
    public Color() {
    }

    public static String Chat(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
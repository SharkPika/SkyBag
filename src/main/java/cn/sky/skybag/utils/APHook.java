package cn.sky.skybag.utils;

import org.bukkit.entity.Player;
import org.serverct.ersha.api.AttributeAPI;
import org.serverct.ersha.attribute.data.AttributeData;

import java.util.List;

public class APHook {
    public APHook(){
    }

    public static void addAttribute(Player player, List<String> attrList, String key) {
        AttributeData APdata = AttributeAPI.getAttrData(player);
        AttributeAPI.addSourceAttribute(APdata, "SkyBag_" + key, attrList);
    }

    public static void takeAttribute(Player player, String key) {
        AttributeData APdata = AttributeAPI.getAttrData(player);
        AttributeAPI.takeSourceAttribute(APdata, "SkyBag_" + key);
        AttributeAPI.updateAttribute(player);
    }
}

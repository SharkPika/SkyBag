package cn.sky.skybag.utils;

import cn.sky.skybag.SkyBag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AttributeUtils {

    public AttributeUtils(){
    }

   public static List<String> getAttributes(ItemStack item){
       ItemMeta meta = item.getItemMeta();
       List<String> lores = meta.getLore();
       List<String> attributes = new ArrayList<>();
       String propertyType = null;
       String value = null;
       for (String line : lores) {
           if (line.contains(SkyBag.getInstance().getConfig().getString("Check"))) {
               propertyType = line.split(": ")[1];
           }
           else if (line.contains(SkyBag.getInstance().getConfig().getString("CheckValue"))) {
               value = line.split(": ")[1];
               if (SkyBag.getInstance().getConfig().getString("TypeValues").contains(value.substring(2))){
                   value = SkyBag.getInstance().getConfig().getString("TypeValues").substring(line.indexOf(": ") + 1).trim();
               }
           }
       }
       attributes.add(propertyType + value);
       return attributes;
   }
}

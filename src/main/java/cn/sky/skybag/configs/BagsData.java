package cn.sky.skybag.configs;

import cn.sky.skybag.SkyBag;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BagsData {
    private static File file;

    private static YamlConfiguration config;

    public BagsData() {
        file = new File(SkyBag.getInstance().getDataFolder(), "BagsData.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                try {
                    config.save(file);
                    config = YamlConfiguration.loadConfiguration(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void addData(Player player, ArrayList<ItemStack> items) {
        config.set("Bags." + player.getUniqueId().toString(), items);
        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList getItems(Player player) {
        return (ArrayList) config.get("Bags." + player.getUniqueId().toString());
    }

    public static boolean containsPlayer(Player player) {
        return config.contains("Bags." + player.getUniqueId().toString());
    }

    public static int getSlots(Player player) {
        for (Map.Entry<String,Object> node : SkyBag.getInstance().getConfig().getConfigurationSection("perms").getValues(false).entrySet()){
            String perm = (String)node.getValue();
            if (player.hasPermission(perm)) {
                return SkyBag.getInstance().getConfig().getConfigurationSection("slots").getInt(node.getKey());
            }
        }
        return 45;
    }

    public static void configReload() {
        SkyBag.getInstance().reloadConfig();
        SkyBag.getInstance().saveDefaultConfig();
        config = YamlConfiguration.loadConfiguration(file);

        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getData() {
        return config;
    }
}

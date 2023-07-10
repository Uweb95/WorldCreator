package de.uweb95.minebay.util;

import de.uweb95.minebay.Minebay;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private static Map<String, FileConfiguration> configFiles = new HashMap<>();

    public static void loadPluginConfig() {
        Minebay plugin = Minebay.getInstance();
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdir();

        File configurationFile = new File(dataFolder + System.getProperty("file.separator") + "config.yml");
        if (!configurationFile.exists()) plugin.saveDefaultConfig();

        try {
            plugin.getConfig().load(configurationFile);
        } catch (Exception e) {
            plugin.getLogger().severe("Could not load the config file! Please check if the permissions are set! Error: " + e.getMessage());
        }
    }

    public static void loadConfig(String filename) {
        if (configFiles.containsKey(filename)) {
            return;
        }

        File configFile = new File(Minebay.getInstance().getDataFolder(), filename);

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            Minebay.getInstance().saveResource(filename, false);
        }

        configFiles.put(filename, YamlConfiguration.loadConfiguration(configFile));
    }

    public static FileConfiguration getConfig(String filename) {
        return configFiles.get(filename);
    }
}

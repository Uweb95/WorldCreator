package de.uweb95.worldcreator.util;

import de.uweb95.worldcreator.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private static Map<String, FileConfiguration> configFiles = new HashMap<>();

    /**
     * Load default plugin config file
     */
    public static void loadPluginConfig() {
        WorldCreator plugin = WorldCreator.getInstance();
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

    /**
     * Load a yaml configuration file
     */
    public static void loadConfig(String filename) {
        if (configFiles.containsKey(filename)) {
            return;
        }

        File configFile = getFileHandle(filename);

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            WorldCreator.getInstance().saveResource(filename, false);
        }

        configFiles.put(filename, YamlConfiguration.loadConfiguration(configFile));
    }

    public static File getFileHandle(String filename) {
        return new File(WorldCreator.getInstance().getDataFolder(), filename);
    }

    public static FileConfiguration getConfig(String filename) {
        return configFiles.get(filename);
    }
}

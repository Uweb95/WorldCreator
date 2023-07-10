package de.uweb95.worldcreator.util;

import de.uweb95.worldcreator.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorldConfiguration {
    private static WorldConfiguration instance;
    private final String configFileName = "worlds.yml";
    private FileConfiguration configFile;

    private List<WorldConfigurationEntry> worldConfigurationEntries = new ArrayList<>();

    private WorldConfiguration() {
        configFile = Configuration.getConfig(configFileName);
        loadConfigEntries();
    }

    public static WorldConfiguration getInstance() {
        if (instance == null) {
            instance = new WorldConfiguration();
        }

        return instance;
    }

    /**
     * Get the configuration entry by world name
     */
    public WorldConfigurationEntry getWorldConfigByName(String name) {
        for (WorldConfigurationEntry entry : worldConfigurationEntries) {
            if (entry.worldName.equals(name)) return entry;
        }

        WorldConfigurationEntry entry = new WorldConfigurationEntry();
        entry.worldName = name;
        worldConfigurationEntries.add(entry);

        return entry;
    }

    /**
     * Set a world loaded
     */
    public void setWorldLoad(String world, boolean load) {
        WorldConfigurationEntry entry = getWorldConfigByName(world);
        entry.loaded = load;
        saveConfigEntries();
    }

    /**
     * Delete a world from the configuration
     */
    public void deleteWorld(String world) {
        WorldConfigurationEntry entry = getWorldConfigByName(world);

        if (entry == null) {
            return;
        }

        worldConfigurationEntries.remove(entry);
        saveConfigEntries();
    }

    /**
     * Load all worlds from the config and load them, when enabled
     */
    private void loadConfigEntries() {
        List<String> entries = configFile.getStringList("worlds");

        for (String entry : entries) {
            WorldConfigurationEntry worldConfigurationEntry = WorldConfigurationEntry.deserialize(entry);

            if (worldConfigurationEntry.loaded) {
                new org.bukkit.WorldCreator(worldConfigurationEntry.worldName).createWorld();
            }

            worldConfigurationEntries.add(worldConfigurationEntry);
        }
    }

    /**
     * Serialize and save the configuration
     */
    private void saveConfigEntries() {
        List<String> serializedEntries = new ArrayList<>();

        for (WorldConfigurationEntry entry : worldConfigurationEntries) {
            serializedEntries.add(entry.serialize());
        }

        configFile.set("worlds", serializedEntries);

        try {
            configFile.save(Configuration.getFileHandle(configFileName));
        } catch (IOException e) {
            WorldCreator.logger().severe(String.format("Can't save config file '%s': %s", configFileName, e.getMessage()));
        }
    }
}

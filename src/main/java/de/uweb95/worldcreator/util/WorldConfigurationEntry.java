package de.uweb95.worldcreator.util;

import java.util.Base64;

public class WorldConfigurationEntry {
    public String worldName;
    public boolean loaded;

    public String serialize() {
        StringBuilder builder = new StringBuilder();
        builder.append("worldName:").append(worldName).append("|");
        builder.append("loaded:").append(loaded);

        return Base64.getEncoder().encodeToString(builder.toString().getBytes());
    }

    public static WorldConfigurationEntry deserialize(String serialized) {
        String rawData = new String(Base64.getDecoder().decode(serialized));
        String[] data = rawData.split("\\|");
        WorldConfigurationEntry entry = new WorldConfigurationEntry();

        for (String row : data) {
            String[] rowData = row.split(":");

            switch (rowData[0]) {
                case "worldName":
                    entry.worldName = rowData[1];
                case "loaded":
                    entry.loaded = Boolean.parseBoolean(rowData[1]);
            }
        }

        return entry;
    }
}

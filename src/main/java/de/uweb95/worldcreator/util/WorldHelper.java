package de.uweb95.worldcreator.util;

import org.bukkit.Bukkit;

import java.io.File;

public class World {

    public static String getWorldFromArgs(String[] args) {
        String world = null;

        if (args.length > 1) {
            world = args[1];
        }

        return world;
    }

    public static boolean checkIfWorldFolderExists(String name) {
        String path = Bukkit.getWorldContainer().getPath() + "/" + name;
        File folder = new File(path);

        return folder.exists() && folder.isDirectory();
    }
}

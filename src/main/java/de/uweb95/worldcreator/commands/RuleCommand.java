package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldHelper;
import org.apache.commons.lang3.SystemUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameRuleCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.delete");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String worldName = WorldHelper.getWorldFromArgs(args);

        if (worldName == null) {
            return true;
        }

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            sender.sendMessage(Message.pluginMessage(String.format("The world '%s' does not exist", worldName)));
            return true;
        }

        File worldFolder = world.getWorldFolder();
        Bukkit.unloadWorld(world, false);

        if (worldFolder.delete()) {
            sender.sendMessage(Message.pluginMessage(String.format("The folder for world '%s' does not exist. It might not be deleted.", worldName)));
            return true;
        }

        sender.sendMessage(Message.pluginMessage("World deleted successfully!"));

        if (SystemUtils.OS_NAME.contains("Windows") && worldFolder.exists()) {
            worldFolder.deleteOnExit();
            sender.sendMessage(Message.pluginMessage("Deleting the world folder does not work correctly on Windows. We'll try to delete it when the server stops."));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            for (World world : Bukkit.getWorlds()) {
                options.add(world.getName());
            }
        }

        return options;
    }
}

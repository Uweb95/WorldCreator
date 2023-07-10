package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.WorldCreator;
import de.uweb95.worldcreator.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class DeleteWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("minebay.user") || player.hasPermission("minebay.admin");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        World world = Bukkit.getWorld("world_test");

        if (world == null) {
            sender.sendMessage(Message.pluginMessage(String.format("The world '%s' does not exist", "world_test")));
            return false;
        }

        Bukkit.unloadWorld(world, false);
        File worldFolder = world.getWorldFolder();

        if (worldFolder.delete()) {
            sender.sendMessage(Message.pluginMessage(String.format("The folder for world '%s' does not exist. It might not be deleted.", "world_test")));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

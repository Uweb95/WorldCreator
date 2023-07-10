package de.uweb95.worldcreator.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ImportWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("minebay.user") || player.hasPermission("minebay.admin");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        Bukkit.createWorld(WorldCreator.name("world_test"));

        Bukkit.unloadWorld("world_test", false);
        Bukkit.getWorldContainer();
        Bukkit.getWorld("world name").getWorldFolder().delete();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

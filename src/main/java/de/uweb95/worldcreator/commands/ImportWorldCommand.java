package de.uweb95.worldcreator.commands;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("minebay.user") || player.hasPermission("minebay.admin");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        WorldCreator worldCreator = new WorldCreator("world_test");
        worldCreator.type(WorldType.NORMAL);
        worldCreator.createWorld();

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

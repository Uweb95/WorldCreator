package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListWorldsCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.list");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        StringBuilder output = new StringBuilder("Loaded worlds:\n");

        for (World world : Bukkit.getWorlds()) {
            output.append(world.getName()).append("\n");
        }

        output.deleteCharAt(output.length() - 1);

        sender.sendMessage(Message.pluginMessage(output.toString()));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

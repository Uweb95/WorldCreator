package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListWorldsCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.unload") || player.hasPermission("wc.admin");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        StringBuilder output = new StringBuilder("");

        sender.sendMessage(Message.pluginMessage(output.toString()));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

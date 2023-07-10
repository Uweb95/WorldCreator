package de.uweb95.minebay.commands;

import de.uweb95.minebay.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VersionCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("minebay.user") || player.hasPermission("minebay.admin");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        sender.sendMessage(Message.pluginMessage("Version 1.0"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

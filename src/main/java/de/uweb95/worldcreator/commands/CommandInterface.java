package de.uweb95.minebay.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface CommandInterface {
    boolean onlyPlayer = false;

    boolean checkPermissions(Player player);

    boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args);

    List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args);
}

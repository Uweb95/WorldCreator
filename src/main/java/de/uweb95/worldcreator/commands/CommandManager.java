package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager implements CommandInterface {
    private final Map<String, CommandInterface> commands = new HashMap<>();

    /**
     * Register a command
     *
     * @param command command name
     * @param commandClass command class instance
     */
    public void registerCommand(String command, CommandInterface commandClass) {
        commands.put(command, commandClass);
    }

    /**
     * Remove a registered command
     *
     * @param command command name
     */
    public void unregisterCommand(String command) {
        commands.remove(command);
    }

    @Override
    public boolean checkPermissions(Player player) {
        return true;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        CommandInterface selectedCommand = null;

        if (args.length > 0) {
            selectedCommand = commands.get(args[0]);
        }

        if (selectedCommand != null) {
            if (sender instanceof Player && (!sender.isOp() && !sender.hasPermission("wc.admin") && !selectedCommand.checkPermissions((Player) sender))) {
                sender.sendMessage(Message.pluginMessage("You don't have the permissions to use this command."));
                return false;
            }

            return selectedCommand.executeCommand(sender, command, commandLabel, args);
        }

        sendHelpMessage(sender);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length > 1) {
            CommandInterface selectedCommand;
            selectedCommand = commands.get(args[0]);

            if (selectedCommand == null) {
                return null;
            }

            return selectedCommand.onTabComplete(sender, command, commandLabel, args);
        }

        commands.forEach((key, value) -> options.add(key));

        return options;
    }

    private void sendHelpMessage(CommandSender sender) {
        StringBuilder builder = new StringBuilder("World creator options:\n");
        commands.forEach((key, value) -> builder.append(key).append("\n"));
        builder.append("Use the 'help' command to see further information for each command.");
        sender.sendMessage(Message.pluginMessage(builder.toString()));
    }
}

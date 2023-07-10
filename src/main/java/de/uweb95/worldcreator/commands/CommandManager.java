package de.uweb95.minebay.commands;

import de.uweb95.minebay.util.Message;
import de.uweb95.minebay.util.Translation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandManager implements CommandInterface {
    private final Map<String, CommandInterface> commands = new HashMap<>();

    public void registerCommand(String command, CommandInterface commandClass) {
        commands.put(command, commandClass);
    }

    public void unregisterCommand(String command) {
        commands.remove(command);
    }

    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("minebay");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        CommandInterface selectedCommand = null;

        if (args.length > 0) {
            selectedCommand = commands.get(args[0]);
        }

        if (selectedCommand != null) {
            if (sender instanceof Player && !selectedCommand.checkPermissions((Player) sender)) {
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
        StringBuilder builder = new StringBuilder(Translation.getTranslation("help")).append("\n");

        commands.forEach((key, value) -> builder.append(key).append("\n"));

        sender.sendMessage(Message.pluginMessage(builder.toString()));
    }
}

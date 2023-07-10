package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldConfiguration;
import de.uweb95.worldcreator.util.WorldHelper;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.create");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Message.pluginMessage("Not enough options, see /wc help for usage."));
            return true;
        }

        String worldName = WorldHelper.getWorldFromArgs(args);
        WorldType type = WorldType.getByName(args[2]);

        if (type == null) {
            sender.sendMessage(Message.pluginMessage("World type must be DEFAULT, FLAT, LARGEBIOMES or AMPLIFIED!"));
            return true;
        }

        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.type(type);
        worldCreator.createWorld();
        WorldConfiguration.getInstance().setWorldLoad(worldName, true);

        sender.sendMessage(Message.pluginMessage("World created successfully!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            options.add("<WORLD NAME>");
        } else if (args.length == 3) {
            options.add("DEFAULT");
            options.add("FLAT");
            options.add("LARGEBIOMES");
            options.add("AMPLIFIED");
        }

        return options;
    }
}

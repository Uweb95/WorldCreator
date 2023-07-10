package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldConfiguration;
import de.uweb95.worldcreator.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LoadWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.load");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String worldName = WorldHelper.getWorldFromArgs(args);

        if (worldName == null) {
            sender.sendMessage(Message.pluginMessage("/wc help"));
            return true;
        }

        new WorldCreator(worldName).createWorld();
        WorldConfiguration.getInstance().setWorldLoad(worldName, true);

        sender.sendMessage(Message.pluginMessage("World loaded successfully!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            options.add("<WORLD NAME>");
        }

        return options;
    }
}

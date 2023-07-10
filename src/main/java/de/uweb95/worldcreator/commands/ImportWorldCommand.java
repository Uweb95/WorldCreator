package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldConfiguration;
import de.uweb95.worldcreator.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.import");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String worldName = WorldHelper.getWorldFromArgs(args);

        if (worldName == null) {
            sender.sendMessage(Message.pluginMessage("/wc help"));
            return true;
        }

        if (!WorldHelper.checkIfWorldFolderExists(worldName)) {
            sender.sendMessage(Message.pluginMessage(String.format("The folder for world '%s' does not exist.", worldName)));
            return true;
        }

        WorldCreator.name(worldName).createWorld();
        WorldConfiguration.getInstance().setWorldLoad(worldName, true);

        sender.sendMessage(Message.pluginMessage("World imported successfully!"));
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

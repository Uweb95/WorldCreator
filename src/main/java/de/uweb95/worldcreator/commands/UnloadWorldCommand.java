package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldConfiguration;
import de.uweb95.worldcreator.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnloadWorldCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.unload");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String worldName = WorldHelper.getWorldFromArgs(args);

        if (worldName == null) {
            sender.sendMessage(Message.pluginMessage("/wc help"));
            return true;
        }

        Bukkit.unloadWorld(worldName, true);
        WorldConfiguration.getInstance().setWorldLoad(worldName, false);

        sender.sendMessage(Message.pluginMessage("World unloaded successfully!"));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            for (World world : Bukkit.getWorlds()) {
                options.add(world.getName());
            }
        }

        return options;
    }
}

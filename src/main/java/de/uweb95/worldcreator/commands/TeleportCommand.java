package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldConfiguration;
import de.uweb95.worldcreator.util.WorldHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportCommand implements CommandInterface {
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

        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.pluginMessage("This command only works ingame."));
            return true;
        }

        Player player = (Player) sender;
        World targetWorld = Bukkit.getWorld(worldName);

        if (targetWorld == null) {
            sender.sendMessage(Message.pluginMessage("This world does not exist."));
            return true;
        }

        player.teleport(targetWorld.getSpawnLocation());

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

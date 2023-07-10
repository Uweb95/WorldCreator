package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import de.uweb95.worldcreator.util.WorldHelper;
import org.apache.commons.lang3.SystemUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.delete");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(Message.pluginMessage("Not enough options, see /wc help for usage."));
            return true;
        }

        String worldName = WorldHelper.getWorldFromArgs(args);
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            sender.sendMessage(Message.pluginMessage(String.format("The world '%s' does not exist", worldName)));
            return true;
        }

        GameRule<?> selectedRule = GameRule.getByName(args[2]);

        if (selectedRule == null) {
            sender.sendMessage(Message.pluginMessage(String.format("The game rule '%s' does not exist", args[2])));
            return true;
        }

        if (args.length == 4) {
            String newValue = args[3];

            if (selectedRule.getType() == Boolean.class) {
                world.setGameRule((GameRule<Boolean>) selectedRule, Objects.equals(newValue, "true"));
            } else if (selectedRule.getType() == Integer.class) {
                world.setGameRule((GameRule<Integer>) selectedRule, Integer.getInteger(newValue));
            }

            sender.sendMessage(Message.pluginMessage(selectedRule.getName() + " is now " + world.getGameRuleValue(selectedRule)));
            return true;
        }

        sender.sendMessage(Message.pluginMessage(selectedRule.getName() + ": " + world.getGameRuleValue(selectedRule)));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 2) {
            for (World world : Bukkit.getWorlds()) {
                options.add(world.getName());
            }
        } else if (args.length == 3) {
            for (GameRule<?> rule : GameRule.values()) {
                if(rule.getName().contains(args[2])){
                    options.add(rule.getName());
                }
            }
        } else if (args.length == 4) {
            GameRule<?> selectedRule = GameRule.getByName(args[2]);

            if (selectedRule != null) {
                if (selectedRule.getType() == Boolean.class) {
                    options.add("true");
                    options.add("false");
                } else {
                    options.add("<number>");
                }
            } else {
                options.add("INVALID GAME RULE!");
            }
        }

        return options;
    }
}

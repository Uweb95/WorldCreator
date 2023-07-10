package de.uweb95.worldcreator;

import de.uweb95.worldcreator.commands.*;
import de.uweb95.worldcreator.util.Configuration;
import de.uweb95.worldcreator.util.UpdateChecker;
import de.uweb95.worldcreator.util.WorldConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Logger;

public final class WorldCreator extends JavaPlugin implements CommandExecutor, TabCompleter {
    private static Logger logger;
    private static WorldCreator instance;
    CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        UpdateChecker.CheckForUpdate();
        Configuration.loadConfig("worlds.yml");

        commandManager = new CommandManager();
        registerCommands();

        // Initialize configuration object and load all enabled worlds
        WorldConfiguration.getInstance();
    }

    private void registerCommands() {
        commandManager.registerCommand("create", new CreateWorldCommand());
        commandManager.registerCommand("import", new ImportWorldCommand());
        commandManager.registerCommand("tp", new TeleportCommand());
        commandManager.registerCommand("delete", new DeleteWorldCommand());
        commandManager.registerCommand("load", new LoadWorldCommand());
        commandManager.registerCommand("unload", new UnloadWorldCommand());
        commandManager.registerCommand("rule", new RuleCommand());
        commandManager.registerCommand("list", new ListWorldsCommand());
        commandManager.registerCommand("help", new HelpCommand());
        commandManager.registerCommand("version", new VersionCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getLabel().equalsIgnoreCase("wc") || command.getLabel().equalsIgnoreCase("worldcreator")) {
            if (sender instanceof Player && !commandManager.checkPermissions((Player) sender)) {
                return false;
            }

            return commandManager.executeCommand(sender, command, label, args);
        }

        return false;
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getLabel().equalsIgnoreCase("wc") || command.getLabel().equalsIgnoreCase("worldcreator")) {
            if (sender instanceof Player && commandManager.checkPermissions((Player) sender))
                return commandManager.onTabComplete(sender, command, alias, args);
        }

        return null;
    }

    public static Logger logger() {
        return logger;
    }

    public static WorldCreator getInstance() {
        return instance;
    }
}

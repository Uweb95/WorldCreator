package de.uweb95.worldcreator.commands;

import de.uweb95.worldcreator.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements CommandInterface {
    @Override
    public boolean checkPermissions(Player player) {
        return player.hasPermission("wc.help");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        // I hate all of this...
        Component output = Component.text("[WorldCreator] ", Style.style(TextColor.fromCSSHexString("#00ffbf")));
        output = output.append(Component.text("Available options for /wc:\n", Style.style(TextColor.fromCSSHexString("#ffffff"))));

        Style commandStyle = Style.style(TextColor.fromCSSHexString("#fdffff"));
        Style secondParameterStyle = Style.style(TextColor.fromCSSHexString("#00ffbf"));
        Style thirdParameterStyle = Style.style(TextColor.fromCSSHexString("#656bff"));
        Style fourthParameterStyle = Style.style(TextColor.fromCSSHexString("#ffa918"));
        Style descriptionStyle = Style.style(TextColor.fromCSSHexString("#c8c8c8"));

        Component worldName = Component.text("<worldName> ", secondParameterStyle);

        output = output.append(Component.text("create ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("<type>\n", thirdParameterStyle));
        output = output.append(Component.text("Create a new world, type can be DEFAULT, FLAT, LARGEBIOMES or AMPLIFIED.\n", descriptionStyle));

        output = output.append(Component.text("import ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("\nImport an existing world.\n", descriptionStyle));

        output = output.append(Component.text("tp ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("\nTeleport to world spawn.\n", descriptionStyle));

        output = output.append(Component.text("delete ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("\nUnload and delete an existing world and all its files.\n", descriptionStyle));

        output = output.append(Component.text("load ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("\nLoad an existing world.\n", descriptionStyle));

        output = output.append(Component.text("unload ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("\nUnload an existing world.\n", descriptionStyle));

        output = output.append(Component.text("rule ", commandStyle));
        output = output.append(worldName);
        output = output.append(Component.text("<gamerule> ", thirdParameterStyle));
        output = output.append(Component.text("<value>\n", fourthParameterStyle));
        output = output.append(Component.text("Change the gamerule for the given world.\n", descriptionStyle));

        output = output.append(Component.text("list\n", commandStyle));
        output = output.append(Component.text("Shows a list of all available worlds.\n", descriptionStyle));

        output = output.append(Component.text("help\n", commandStyle));
        output = output.append(Component.text("Shows this list.\n", descriptionStyle));

        output = output.append(Component.text("version\n", commandStyle));
        output = output.append(Component.text("Shows the plugins version.", descriptionStyle));

        sender.sendMessage(output);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] args) {
        return null;
    }
}

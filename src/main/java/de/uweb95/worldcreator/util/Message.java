package de.uweb95.worldcreator.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

public class Message {
    public static Component pluginMessage(String text, String color) {
        Component prefix = Component.text("[WorldCreator] ", Style.style(TextColor.fromCSSHexString("#00ffbf")));
        Component message = Component.text(text, Style.style(TextColor.fromCSSHexString(color)));
        return prefix.append(message);
    }

    public static Component pluginMessage(String text) {
        return pluginMessage(text, "#ffffff");
    }
}

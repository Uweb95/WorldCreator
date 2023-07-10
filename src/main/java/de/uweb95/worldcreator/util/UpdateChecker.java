package de.uweb95.worldcreator.util;

import de.uweb95.worldcreator.WorldCreator;

import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {
    private static final String currentVersionUrl = "https://mc.lunar-software.eu/worldcreator/currentVersion";
    private static final String installedVersion = "1.0";

    public static void CheckForUpdate() {
        URL currentVersion;
        String outputMessage;

        try {
            currentVersion = new URL(currentVersionUrl);
            Scanner scanner = new Scanner(currentVersion.openStream());

            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                buffer.append(scanner.next());
            }

            String currentAvailableVersion = buffer.toString();

            if (currentAvailableVersion.equals("")) {
                outputMessage = "Can't check for new version!";
            } else {
                boolean upToDate = installedVersion.equalsIgnoreCase(currentAvailableVersion);

                if (upToDate) {
                    outputMessage = "Installed version is up to date.";
                } else {
                    outputMessage = "New Version available! Installed Version: " + installedVersion + ", New Version: " + currentAvailableVersion;
                }
            }

        } catch (Exception e) {
            outputMessage = "Can't check for new version!";
        }

        WorldCreator.logger().warning(outputMessage);
    }
}

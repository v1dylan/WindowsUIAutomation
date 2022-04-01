package com.automationanywhere.botcommand;

import AdvantageAutomation.AdvantageLaunch;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;


import java.io.CharArrayWriter;
import java.io.PrintWriter;

import static com.automationanywhere.commandsdk.model.AttributeType.NUMBER;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;

@BotCommand

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "LaunchAdvantage", label = "Launch Advantage",
        node_label = "Launch Advantage", description = "Launches target Advantage instance", icon = "AdvantageIcon.svg")

public class LaunchAdvantageCommand {
    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Advantage Launchpad Executable Path")
            @NotEmpty
                    String LauncherPath,

            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Advantage Launchpad Window Title")
            @NotEmpty
                    String LaunchpadWindowTitle,

            @Idx(index = "3", type = TEXT)
            @Pkg(label = "Advantage Instance to Open")
            @NotEmpty
                    String Instance,
            @Idx(index = "4", type = NUMBER)
            @Pkg(label = "Time to Wait for Advantage to Launch (Milliseconds)")
            @NotEmpty
                double MaxWait) {

        AdvantageLaunch Launch = new AdvantageLaunch();

        try {
            Launch.LaunchAdvantage(LauncherPath, LaunchpadWindowTitle, Instance, ((int) MaxWait));
        }catch (Exception e) {
            CharArrayWriter cw = new CharArrayWriter();
            PrintWriter w = new PrintWriter(cw);
            e.printStackTrace(w);
            w.close();
            String trace = cw.toString();
            throw new BotCommandException("Unable to launch Advantage: " + trace);
        }
    }
}

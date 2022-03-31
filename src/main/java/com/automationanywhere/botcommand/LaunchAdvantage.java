package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;

import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class LaunchAdvantage {
    public void LaunchAdvantage(String LauncherPath, String LaunchPadWindowTitle, String Instance) throws Exception {
        UIAutomation Automation = UIAutomation.getInstance();
        //Launch launchpad
        Application application = new Application(new ElementBuilder().automation(Automation).applicationPath(LauncherPath));
        application.launchOrAttach();
        Automation.wait(10);
        //Set window
        Window Window = Automation.getDesktopWindow(LaunchPadWindowTitle);
        Window.focus();
        List<Element> Buttons = Window.getElementsByControlType(ControlType.Button);
        boolean buttonisfound = false;
        for (Element Element : Buttons) {
            Button currentButton = new Button(new ElementBuilder(Element));
            List<AutomationBase> Children = currentButton.getChildren(false);
            for (AutomationBase Element1 : Children) {
                if (Element1.getName().equals(Instance)) {
                    currentButton.click();
                    buttonisfound = true;
                    break;
                }
            }
            if (buttonisfound == true) {break;}
        }
    }
}

package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;

import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class LaunchAdvantage {
    public void LaunchAdvantage(String LaunchPadWindowTitle, String Instance) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
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

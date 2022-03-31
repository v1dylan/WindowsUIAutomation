package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;

import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class LaunchAdvantage {
    public UIAutomation StartAutomation() {
        UIAutomation Automation = UIAutomation.getInstance();
        return Automation;
    }
    public Window GetWindow(String WindowTitle, UIAutomation Automation) throws AutomationException {
        Window AdvantageWindow = Automation.getWindow(WindowTitle);
        return AdvantageWindow;
    }

    public void LaunchAdvantage(String LauncherPath, String LaunchPadWindowTitle, String Instance, int MaxWait) throws Exception {
        UIAutomation Automation = StartAutomation();
        //Launch launchpad
        Application application = Automation.launch(LauncherPath);
        application.waitForInputIdle(MaxWait);
        //Set window
        Window Window = GetWindow(LaunchPadWindowTitle, Automation);
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

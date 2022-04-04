package AdvantageAutomation;

import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class AdvantageLaunch {
    public void LaunchAdvantage(String LauncherPath, String LaunchPadWindowTitle, String Instance, int MaxWait) throws Exception {
        UIAutomation Automation = UIAutomation.getInstance();
        //Launch launchpad
        Application application = Automation.launch(LauncherPath);
        application.waitForInputIdle(MaxWait);
        //Set window
        Window Window = Automation.getWindow(LaunchPadWindowTitle);
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

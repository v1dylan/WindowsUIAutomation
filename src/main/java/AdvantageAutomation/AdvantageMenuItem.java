package AdvantageAutomation;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.controls.MenuItem;
import mmarquee.automation.controls.Window;

import java.util.List;

public class AdvantageMenuItem {
    void SelectMenuItem(String AdvantageWindow, String MenuItem) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        Window Window = Automation.getWindow(AdvantageWindow);
        List<Window> Windows = Automation.getDesktopWindows();
        MenuItem foundItem = null;
        for (Window current : Windows) {
            List<Element> menuItems = current.getElementsByControlType(ControlType.Menu);
            for (Element element : menuItems) {
                MenuItem currentMenuItem = new MenuItem(new ElementBuilder(element));
                if (currentMenuItem.getName().equals(MenuItem)){
                    foundItem = currentMenuItem;
                    break;
                }
            }
            if (foundItem != null) {break;}
        }
        if (foundItem != null) {
            //foundItem.click();
            foundItem.invoke();
        }
    }
}

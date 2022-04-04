package AdvantageAutomation;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class AdvantageMenuItem {
    void SelectMenuItem(String AdvantageWindowTitle, String TargetViewportID, String Tab, String MenuItem) throws AutomationException, InterruptedException {
        UIAutomation Automation = UIAutomation.getInstance();
        Window AdvantageWindow = Automation.getWindow(AdvantageWindowTitle);
        List<Window> Windows = Automation.getDesktopWindows();
        MenuItem foundFilterItem = null;
        MenuItem foundFilterChooseItem = null;

        Custom Viewport = AdvantageWindow.getCustomByAutomationId(TargetViewportID);
        //Find correct tab item and sub tab item
        TabItem RootTab = null;
        List<Element> Elements = Viewport.getElementsByControlType(ControlType.Tab);
        int i = 0;
        for (Element current : Elements) {
            Tab CurrentTab = new Tab(new ElementBuilder(current));
            List<TabItem> Tabs = CurrentTab.getTab(i).getTabItems();
            for (TabItem CurrentSubTab : Tabs) {
                if (CurrentSubTab.getName().equals(Tab)) {
                    RootTab = CurrentSubTab;
                    break;
                }
            }
            if (RootTab != null) {
                break;
            }
            i++;
        }
        if (RootTab != null) {
            for (Element current : RootTab.getElementsByControlType(ControlType.Button)) {
                if(current.getAutomationId().matches(".*" + "_CONFIGURE_BUTTON")) {
                    System.out.println("found button");
                }
            }
        }


        for (Window current : Windows) {
            List<Element> menuItems = current.getElementsByControlType(ControlType.Menu);
            for (Element element : menuItems) {
                MenuItem currentMenuItem = new MenuItem(new ElementBuilder(element));
                if (currentMenuItem.getName().equals("Choose filter")){
                    foundFilterChooseItem = currentMenuItem;
                    break;
                }
            }
            if (foundFilterChooseItem != null) {break;}
        }
        if (foundFilterChooseItem != null) {
            foundFilterChooseItem.click();
        }
       Automation.wait(1000);
       Windows = Automation.getDesktopWindows();
        for (Window current : Windows) {
            List<Element> menuItems = current.getElementsByControlType(ControlType.Menu);
            for (Element element : menuItems) {
                MenuItem currentMenuItem = new MenuItem(new ElementBuilder(element));
                if (currentMenuItem.getName().equals(MenuItem)){
                    foundFilterItem = currentMenuItem;
                    break;
                }
            }
            if (foundFilterItem != null) {break;}
        }
        if (foundFilterItem != null) {
            foundFilterItem.click();
        }
    }
}

package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

public class GetElementTesting {
    public void GetElement(String WindowTitle, String TargetViewportID, String RootTabName) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Set window
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        //get viewport in advantage
        Custom TargetViewport = Window.getCustomByAutomationId(TargetViewportID);
        //Find correct tab item under viewport
        TabItem Tab = null;
        for (int i = 0; i < 10; i++ ) {
           List<TabItem> TabItems = TargetViewport.getTab(i).getTabItems();
           for (TabItem Current : TabItems) {
               if (Current.getName().equals(RootTabName)) {
                   Tab = Current;
                   break;
               }
           }
            if (Tab != null) {break;}
        }
        DataGrid DataGrid = Tab.getDataGrid(0);
    }
}

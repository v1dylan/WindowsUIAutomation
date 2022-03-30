package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.ArrayList;
import java.util.List;

public class GetElementTesting {
    public void GetElement(String WindowTitle, String TargetViewportID, String RootTabName) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Set window
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        //get tab item that holds data table
        Custom TargetViewport = Window.getCustomByAutomationId(TargetViewportID);
        System.out.println("ViewPort: " + TargetViewport.getName() + "Auto ID: " + TargetViewport.getElement().getAutomationId());
        TabItem Tab = null;
        for (int i = 0; i < 10; i++ ) {
           List<TabItem> TabItems = TargetViewport.getTab(i).getTabItems();
            System.out.println("Looping " + i + "tab count " + TabItems.size());
           for (TabItem Current : TabItems) {
               if (Current.getName().equals(RootTabName)) {
                   Tab = Current;
                   System.out.println(Tab.getName());
                   break;
               }
           }
            if (Tab != null) {break;}
        }
        System.out.println(Tab.getDataGrid(0).getRowCount() + Tab.getDataGrid(0).getElement().getAutomationId());
        System.out.println("Tab: " + Tab.getName() + "Auto ID: " + Tab.getElement().getAutomationId());
        //java.util.List<TabItem> TabItems = Tab.getTabItems();
        //for (TabItem CurrentTab : TabItems) {
         //   System.out.println("Tabs: " + CurrentTab.getName() + "Auto ID: " + CurrentTab.getElement().getAutomationId());
        //}
    }
}

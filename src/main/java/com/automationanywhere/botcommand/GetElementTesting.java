package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import mmarquee.automation.AutomationException;
import mmarquee.automation.Element;
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
        System.out.println(DataGrid.getName());
    }
}

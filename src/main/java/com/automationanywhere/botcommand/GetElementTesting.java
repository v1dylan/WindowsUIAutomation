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
    public ListValue FindDataGrid(String WindowTitle, String TargetViewportID, String RootTabName) throws AutomationException {
        ListValue listValue = new ListValue<>();
        List<Value> Values = new ArrayList<>();
        UIAutomation Automation = UIAutomation.getInstance();
        //Set window
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        Values.add(new StringValue("Window: " + Window.getName() + Window.getElement().getAutomationId()));
        //get tab item that holds data table
        Custom TargetViewport = Window.getCustomByAutomationId(TargetViewportID);
        Values.add(new StringValue("TargetViewport: " + TargetViewport.getName() + TargetViewport.getElement().getAutomationId()));
        Tab Tab = TargetViewport.getTab(0);
        Values.add(new StringValue("Tab: " + Tab.getName() + Tab.getElement().getAutomationId()));
        java.util.List<TabItem> TabItems = Tab.getTabItems();
        TabItem RootTab = null;
        int i = 0;
        for (TabItem CurrentTab : TabItems){
            Values.add(new StringValue("Tab " + i + ": " + CurrentTab.getName() + CurrentTab.getElement().getAutomationId()));
            i++;
        }
        listValue.set(Values);
        return listValue;
    }
}

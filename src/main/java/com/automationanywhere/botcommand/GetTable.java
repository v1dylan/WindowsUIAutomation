package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.model.table.Table;
import mmarquee.automation.AutomationException;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.AutomationTreeWalker.AutomationElementVisitor;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;
import mmarquee.uiautomation.IUIAutomationElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetTable {
    public Element FindDataGrid(String AppPath, String WindowTitle, String TargetViewportID,String RootTabName, String TargetElementID) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Set window
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        //get tab item that holds data table
        Custom TargetViewport = Window.getCustomByAutomationId(TargetViewportID);
        Tab Tab = TargetViewport.getTab(0);
        List<TabItem> TabItems = Tab.getTabItems();
        TabItem RootTab = null;
        for (TabItem CurrentTab : TabItems){
            System.out.println(CurrentTab.getName());
            if (CurrentTab.getName().equals(RootTabName)) {
                System.out.println("Found");
                RootTab = CurrentTab;
                break;
            }
        }
        if (RootTab != null) {
            DataGrid DataGrid = RootTab.getDataGrid(0);
            int RowCount = DataGrid.getRowCount();
            List<DataGridCell> Header = DataGrid.getRowHeaders();
            for (DataGridCell CurrentCell : Header) {
                System.out.println(CurrentCell.getValue());
            }
            System.out.println("Row Count: " + RowCount);
        }
        return null;
    }
}


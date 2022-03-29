package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.table.Row;
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
    public DataGrid FindDataGrid(String AppPath, String WindowTitle, String TargetViewportID,String RootTabName, String TargetElementID) throws AutomationException {
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
            if (CurrentTab.getName().equals(RootTabName)) {
                RootTab = CurrentTab;
                break;
            }
        }
        DataGrid DataGrid = null;
        if (RootTab != null) {
            DataGrid = RootTab.getDataGrid(0);
            return DataGrid;
        }
        else {
            return null;
        }
    }

    /*-------------- Data grid to AA Table ----------------------*/

    public Table DataGridToAATable(DataGrid DataGrid, boolean IncludeHeader) throws AutomationException {
        Table Table = null;
        List<Row> TableRows = new ArrayList<>();
        Row HeaderRow = new Row();
        if (IncludeHeader) {
            List<DataGridCell> Headers = DataGrid.getColumnHeaders();
            List<Value> HeaderValues = new ArrayList<>();
            for (DataGridCell Header : Headers) {
                System.out.println(Header.getName());
                String CurrentHeader = Header.getName();
                StringValue String1 = new StringValue(CurrentHeader);
                if (String1 != null) {
                HeaderValues.add(String1);
                }
            }
            HeaderRow.setValues(HeaderValues);
            TableRows.add(HeaderRow);
        }
        System.out.println(TableRows.get(0).getValues().get(0));
        return null;
    }
}


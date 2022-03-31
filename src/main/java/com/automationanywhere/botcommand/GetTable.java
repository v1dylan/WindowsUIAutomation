package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.ArrayList;
import java.util.List;


public class GetTable {

    /*----------------------Get data grid in advantage-------------------------*/
    public DataGrid FindDataGrid(String WindowTitle, String TargetViewportID, String RootTabName) throws Exception {
        UIAutomation Automation = UIAutomation.getInstance();
        DataGrid DataGrid = null;
        //Sets window using window title
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        //get viewport
        Custom TargetViewport = Window.getCustomByAutomationId(TargetViewportID);
        //Find correct tab item and sub tab item
        TabItem Tab = null;
        Container Container = null;


        int MaxLoops = TargetViewport.getElementCountByControlType(ControlType.Tab);
        System.out.println(MaxLoops);
        List<Element> Elements = TargetViewport.getElementsByControlType(ControlType.Tab);
        int i = 0;
        for (Element current : Elements) {
            Tab CurrentTab = new Tab(new ElementBuilder(current));
            List<TabItem> Tabs = CurrentTab.getTab(0).getTabItems();
            for (TabItem tab : Tabs) {
                System.out.println(tab.getName());
            }
            i++;
        }
        try {
            for (int g = 0; g < MaxLoops; g++) {
                List<TabItem> TabItems = TargetViewport.getTab(g).getTabItems();
                for (TabItem Current : TabItems) {
                    if (Current.getName().equals(RootTabName)) {
                        Tab = Current;
                        break;
                    }
                }
                if (Tab != null) {
                    break;
                }
            }
        }
        catch (Exception e) {
            throw new Exception("Unable to find root tab: " + e);
        }
        try {
            DataGrid = Tab.getDataGrid(0);
        } catch (Exception e) {
            throw new Exception("Unable to find datagrid: " + e);
        }
        return DataGrid;
    }
    /*-------------- Data grid to AA Table ----------------------*/

    public Table DataGridToAATable(DataGrid DataGrid, boolean IncludeHeader) throws AutomationException {
        Table Table = new Table();
        List<Row> TableRows = new ArrayList<>();

        //Set header row
        if (IncludeHeader) {
            List<DataGridCell> Headers = DataGrid.getColumnHeaders();
            Row HeaderRow = new Row();
            List<Value> HeaderValues = new ArrayList<>();
            for (DataGridCell Header : Headers) {
                String CurrentHeader = Header.getName();
                StringValue String1 = new StringValue(CurrentHeader);
                if (String1 != null) {
                    HeaderValues.add(String1);
                }
            }
            HeaderRow.setValues(HeaderValues);
            TableRows.add(HeaderRow);
        }

        //Set rows
        int RowCount = DataGrid.getRowCount();
        int ColumnCount = DataGrid.getColumnCount();

        for (int i = 0; i < RowCount; i++) {
            Row CurrentRow = new Row();
            List<Value> RowValues = new ArrayList<>();
            for (int g = 0; g < ColumnCount; g++) {
                RowValues.add(new StringValue(DataGrid.getItem(i, g).getValue()));
            }
            CurrentRow.setValues(RowValues);
            TableRows.add(CurrentRow);
        }
        Table.setRows(TableRows);
        return Table;
    }

}


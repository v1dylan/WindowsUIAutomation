package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.AutomationTreeWalker.AutomationElementVisitor;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetTable {

    private static final int recurseLevel = 50;

    public String GetTable(String AppPath, String WindowTitle, String ParentElementID, String TargetElementID) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Attempt set application
        Application Application = new Application(
                new ElementBuilder()
                        .automation(Automation)
                        .applicationPath(AppPath));
        try {
            Application.launchOrAttach();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        // Tab RootTab = Window.getTabByAutomationId(ParentElementID);
        // DataGrid DataGrid = RootTab.getDataGridByAutomationId(TargetElementID);
        Panel RootPanel = Window.getPanelByAutomationId("ScrollViewer_1");
        Button Button = RootPanel.getButton(Pattern.compile(TargetElementID + ".*"));
        Button Button1 = RootPanel.getButtonByAutomationId("ID");
        System.out.println(Button.getName());

        List<AutomationBase> ElementChildren = RootPanel.getChildren(false);
        return "";
    }
}


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
    public Element FindDataGrid(String AppPath, String WindowTitle, String ParentElementID, String TargetElementID) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Attempt set application
        Application Application = new Application(new ElementBuilder().automation(Automation).applicationPath(AppPath));
        try {
            Application.launchOrAttach();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        Window.getDataGrid(Search.getBuilder(ParentElementID).automationId(TargetElementID).build());
        return null;
    }
}


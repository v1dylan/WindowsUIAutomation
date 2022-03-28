package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.AutomationTreeWalker.AutomationElementVisitor;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetTable {
    public Element FindDataGrid(String AppPath, String WindowTitle, String ParentElementID, String TargetElementID) throws AutomationException {
        UIAutomation Automation = UIAutomation.getInstance();
        //Attempt set application
        Application Application = new Application(
                new ElementBuilder()
                        .automation(Automation)
                        .applicationPath(AppPath));
        try {
            Application.launchOrAttach();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Window Window = Automation.getDesktopWindow(WindowTitle);
        Window.focus();
        List<TabItem> TabItems = Window.getTab(0).getTabItems();
        TabItem TabItem = TabItems.get(0);
        System.out.println("Tab Items found:" + TabItems.size());
        List<AutomationBase> ChildElements = TabItem.getChildren(true);
        Pattern Pattern = java.util.regex.Pattern.compile(String.format(TargetElementID + ".*"));
        List<Element> MatchingElements = new ArrayList<Element>();
        for (AutomationBase Element : ChildElements) {
            Element CurrentElement = Element.getElement();
            Matcher Matcher = Pattern.matcher(CurrentElement.getAutomationId());
            if (Matcher.find()) {
                System.out.println("found element" + CurrentElement.getAutomationId());
                MatchingElements.add(CurrentElement);
            }
        }
        if (MatchingElements.size() == 1) {
            return MatchingElements.get(0);
        }
        else {
            return null;
        }


        public
    }
}


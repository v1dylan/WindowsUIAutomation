package com.automationanywhere.botcommand;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.lang.annotation.Target;
import java.util.List;

public class OpenView {
    public void OpenView(String WindowTitle, String TargetView) throws AutomationException {
        LaunchAdvantage Advantage = new LaunchAdvantage();
        UIAutomation Automation = Advantage.StartAutomation();
        Window Window = Advantage.GetWindow(WindowTitle, Automation);
        EditBox SearchBox = null;
        ToolBar toolBar = Window.getToolBar(0);
        SearchBox = toolBar.getEditBoxByAutomationId("PART_EditableTextBox");

        if (SearchBox != null) {
            SearchBox.setValue(TargetView);
            ListItem FoundListItem = null;
           List<Element> ListItems = Window.getElementsByControlType(ControlType.ListItem);
           for (Element current : ListItems) {
               ListItem currentList = new ListItem(new ElementBuilder(current));
               List<AutomationBase> children = currentList.getChildren(false);
               for (AutomationBase current1 : children) {
                   if(current1.getElement().getName().equals(TargetView)) {
                   FoundListItem = currentList;
                   break;
                   }
               }
               if (FoundListItem != null) {break;}
           }
           FoundListItem.click();
        }
    }
}

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
        List<Element> toolBar = Window.getElementsByControlType(ControlType.ToolBar);
        for (Element element : toolBar) {
            ToolBar toolBar1 = new ToolBar(new ElementBuilder(element));
            SearchBox = toolBar1.getEditBoxByAutomationId("PART_EditableTextBox");
        }
        if (SearchBox != null) {
            SearchBox.setValue(TargetView);
        }
    }
}

package com.automationanywhere.botcommand;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.DataGrid;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.*;

@BotCommand

@CommandPkg(
        //Unique name inside a package and label to display.
        name = "GetElementTest", label = "GetElementTest",
        node_label = "[[Concatenate.node_label]]", description = "[[Concatenate.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[Concatenate.return_label]]", return_type = LIST, return_required = true)

public class GetElementTestingCommand {
    @Execute
    public Value<ListValue> action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Window Title")
            @NotEmpty
                    String WindowTitle,

            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Parent Viewport ID")
            @NotEmpty
                    String ParentViewportID,

            @Idx(index = "3", type = TEXT)
            @Pkg(label = "Root Tab Name")
            @NotEmpty
                    String RootTabName,
            @Idx(index = "4", type = AttributeType.BOOLEAN)
            @Pkg(label = "Get Header?")
            @NotEmpty
                    Boolean IncludeHeader) throws AutomationException {

        GetElementTesting GetElement = new GetElementTesting();
        ListValue List = new ListValue<>();

        try {
            List = GetElement.FindDataGrid(WindowTitle, ParentViewportID, RootTabName);
        }
        catch (Exception e) {
            CharArrayWriter cw = new CharArrayWriter();
            PrintWriter w = new PrintWriter(cw);
            e.printStackTrace(w);
            w.close();
            String trace = cw.toString();
            throw new BotCommandException(trace);
        }
        //Return StringValue.
        return new ListValue(List);
    }

}

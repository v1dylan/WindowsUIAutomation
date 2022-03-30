package com.automationanywhere.botcommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.DataGrid;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.BOOLEAN;
import static com.automationanywhere.commandsdk.model.DataType.TABLE;


@BotCommand


@CommandPkg(
        //Unique name inside a package and label to display.
        name = "GetAdvantageTable", label = "Get Advantage Table",
        node_label = "[[Concatenate.node_label]]", description = "[[Concatenate.description]]", icon = "pkg.svg",

        //Return type information. return_type ensures only the right kind of variable is provided on the UI.
        return_label = "[[Concatenate.return_label]]", return_type = TABLE, return_required = true)

public class GetTableCommand{
    @Execute
    public Value<Table> action(
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

            Table table = new Table();
            GetTable getTable = new GetTable();
            DataGrid DataGrid = null;

            try {
                DataGrid = getTable.FindDataGrid(WindowTitle, ParentViewportID, RootTabName);
                if (DataGrid == null) {
                    throw new BotCommandException("DataTable not found");
                }
            }
            catch (Exception e) {
                CharArrayWriter cw = new CharArrayWriter();
                PrintWriter w = new PrintWriter(cw);
                e.printStackTrace(w);
                w.close();
                String trace = cw.toString();
                throw new BotCommandException("Unable to locate datatable: " + trace);
            }
            try {
                table = getTable.DataGridToAATable(DataGrid, IncludeHeader);
            }
            catch (Exception e) {
                CharArrayWriter cw = new CharArrayWriter();
                PrintWriter w = new PrintWriter(cw);
                e.printStackTrace(w);
                w.close();
                String trace = cw.toString();
                throw new BotCommandException("Unable to locate datatable: " + trace);
            }








        //Return StringValue.
        return new TableValue(table);
    }

}

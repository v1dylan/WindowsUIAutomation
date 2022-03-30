import com.automationanywhere.botcommand.GetElementTesting;
import com.automationanywhere.botcommand.GetElementTestingCommand;
import com.automationanywhere.botcommand.GetTableCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import org.testng.annotations.Test;

import java.util.List;

@Test

public class TestCommand {
    public void TestGetGrid() throws Exception {
        GetTableCommand TestCommand = new GetTableCommand();

        String WindowTitle = "Advantage - >> Agora Production Area <<";
        String ParentViewportID = "mainDataViewPort";
        String RootTabName = "Omnistream Transactions (MSTOSM)";


        //eventToNewRelic.PostEvent(key, values);
        Value<Table> TableValue = new TableValue();
        TableValue = TestCommand.action(WindowTitle, ParentViewportID, RootTabName, true);
        Table Table = TableValue.get();
        List<Row> Rows = Table.getRows();
        for (Row CurrentRow: Rows) {
            System.out.println(CurrentRow.getValues().get(0));
        }
    }
    public void TestGetElement() throws Exception {
        GetElementTestingCommand TestCommand = new GetElementTestingCommand();

        String WindowTitle = "Advantage - >> Agora Production Area <<";
        String ParentViewportID = "mainDataViewPort";
        String RootTabName = "Omnistream Transactions (MSTOSM)";


        //eventToNewRelic.PostEvent(key, values);
        Value<List> TableValue = new ListValue();
        TableValue = TestCommand.action(WindowTitle, ParentViewportID, RootTabName, true);
        Table Table = TableValue.get();
        List<Row> Rows = Table.getRows();
        for (Row CurrentRow: Rows) {
            System.out.println(CurrentRow.getValues().get(0));
        }
    }
}

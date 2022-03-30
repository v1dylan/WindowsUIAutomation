import com.automationanywhere.botcommand.GetTableCommand;
import com.automationanywhere.botcommand.data.Value;
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
        String RootTabName = "Omnistream Transactions (MSTOSM)";
        String TargetViewportID = "mainDataViewPort";

        //eventToNewRelic.PostEvent(key, values);
        Value<Table> TableValue = new TableValue();
        TableValue = TestCommand.action("", WindowTitle, RootTabName, TargetViewportID);
        Table Table = TableValue.get();
        List<Row> Rows = Table.getRows();
        for (Row CurrentRow: Rows) {
            System.out.println(CurrentRow.getValues().get(0));
        }
    }
}

import com.automationanywhere.botcommand.GetElementTesting;
import org.testng.annotations.Test;

import java.util.List;

@Test

public class TestCommand {
    /*public void TestGetGrid() throws Exception {
        GetTableCommand TestCommand = new GetTableCommand();

        String WindowTitle = "Advantage - >> Agora Production Area <<";
        String ParentViewportID = "mainDataViewPort";
        String RootTabName = "Omnistream Transactions (MSTOSM)";

        Value<Table> TableValue = new TableValue();
        TableValue = TestCommand.action(WindowTitle, ParentViewportID, RootTabName, true);
        Table Table = TableValue.get();
        List<Row> Rows = Table.getRows();
        for (Row CurrentRow: Rows) {
            System.out.println(CurrentRow.getValues().get(0));
        }
    }*/
    public void TestGetElement() throws Exception {
        GetElementTesting TestCommand = new GetElementTesting();

        String WindowTitle = "Advantage - >> Agora Production Area <<";
        String ParentViewportID = "mainDataViewPort";
        String RootTabName = "Omnistream Transactions (MSTOSM)";

        TestCommand.GetElement(WindowTitle, ParentViewportID, RootTabName);
    }
}

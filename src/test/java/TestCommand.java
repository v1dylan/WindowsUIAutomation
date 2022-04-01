import AdvantageAutomation.AdvantageLaunch;
import com.automationanywhere.botcommand.GetTableCommand;
import com.automationanywhere.botcommand.LaunchAdvantageCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import mmarquee.automation.AutomationException;
import org.testng.annotations.Test;

import java.util.List;

@Test

public class TestCommand {
    public void TestGetGrid() throws Exception {
        GetTableCommand TestCommand = new GetTableCommand();

        String WindowTitle = "Advantage - >> Agora Production Area <<";
        String ParentViewportID = "mainDataViewPort";
        String RootTabName = "Omnistream Transactions (MSTOSM)";

        Value<Table> TableValue = new TableValue();
        TableValue = TestCommand.action(WindowTitle, ParentViewportID, RootTabName, true);
        Table Table = TableValue.get();
        List<Row> Rows = Table.getRows();
        for (Row CurrentRow : Rows) {
            System.out.println(CurrentRow.getValues().get(0));
        }
    }
    public void TestOpenAdvantage() throws Exception {
        String LauncherPath = "C:\\Program Files (x86)\\Advantage\\LaunchPad\\LaunchPad.exe";
        String WindowTitle = "Advantage LaunchPad";
        String Instance = "TRAINING";
        double MaxWait = 5000;

        LaunchAdvantageCommand launchAdvantage = new LaunchAdvantageCommand();
        launchAdvantage.action(LauncherPath, WindowTitle, Instance, MaxWait);
    }
}


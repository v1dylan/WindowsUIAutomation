import com.automationanywhere.botcommand.GetTable;
import mmarquee.automation.AutomationException;
import org.testng.annotations.Test;
@Test

public class TestCommand {
    public void TestGetGrid() throws AutomationException {
        GetTable GetTable1 = new GetTable();

        String AppPath = "\\\\ADV-APP-T2\\AdvTrain\\Advantage\\v_2019.8.10310\\run\\Advantage-UI.exe";
        String WindowTitle = "Advantage - >> Agora Training Area <<";
        String ParentElement = "Omnistream Transactions (MSTOSM)";
        String TargetElementID = "c80_COLUMN_LIST";

        //eventToNewRelic.PostEvent(key, values);
        GetTable1.FindDataGrid(AppPath, WindowTitle, ParentElement, TargetElementID);
    }
}

import com.automationanywhere.botcommand.GetTable;
import mmarquee.automation.AutomationException;
import org.testng.annotations.Test;
@Test

public class TestCommand {
    public void TestGetGrid() throws Exception {
        GetTable GetTable1 = new GetTable();

        String AppPath = "\\ADV-APP-T2\\AdvTrain\\Advantage\\v_2019.8.10310\\run\\Advantage-UI.exe";
        String WindowTitle = "Advantage - >> Agora Training Area <<";
        String RootTabName = "Omnistream Transactions (MSTOSM)";
        String TargetElementID = "c80_COLUMN_LIST";
        String TargetViewportID = "mainDataViewPort";

        //eventToNewRelic.PostEvent(key, values);
        GetTable1.FindDataGrid(AppPath, WindowTitle, TargetViewportID, RootTabName, TargetElementID);
    }
}

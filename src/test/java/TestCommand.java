import com.automationanywhere.botcommand.GetTable;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.DataGrid;
import org.testng.annotations.Test;
@Test

public class TestCommand {
    public void TestGetGrid() throws Exception {
        GetTable GetTable1 = new GetTable();

        String WindowTitle = "Advantage - >> Agora Training Area <<";
        String RootTabName = "Omnistream Transactions (MSTOSM)";
        String TargetViewportID = "mainDataViewPort";

        //eventToNewRelic.PostEvent(key, values);
        DataGrid ReturnedGrid = GetTable1.FindDataGrid(WindowTitle, TargetViewportID, RootTabName);
        GetTable1.DataGridToAATable(ReturnedGrid, true);
    }
}

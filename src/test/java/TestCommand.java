import com.automationanywhere.botcommand.GetTable;
import mmarquee.automation.AutomationException;
import org.testng.annotations.Test;
@Test

public class TestCommand {
    public void TestGetGrid() throws AutomationException {
        GetTable GetTable1 = new GetTable();

        String AppPath = "C:\\Users\\14103\\AppData\\Local\\Apps\\2.0\\2QBZNMY9.3DO\\X5W75V27.6OA\\crm...tion_a1f3476ec230d85b_07e6.0001_876125b32c332a27\\CRM.exe";
        String WindowTitle = "CRM Application - Built with Progress® Telerik® UI for WPF";

        //eventToNewRelic.PostEvent(key, values);
        GetTable1.FindDataGrid(AppPath, WindowTitle, "opportunitiesTabItem", "opportunitiesGrid");
    }
}

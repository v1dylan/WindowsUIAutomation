import com.automationanywhere.botcommand.GetTable;
import mmarquee.automation.AutomationException;
import org.testng.annotations.Test;
@Test

public class TestCommand {
    public void TestGetGrid() throws AutomationException {
        GetTable GetTable1 = new GetTable();

        String AppPath = "C:\\Program Files (x86)\\Microsoft Visual Studio\\2019\\Community\\Common7\\IDE\\devenv.exe";
        String WindowTitle = "Microsoft Visual Studio";

        //eventToNewRelic.PostEvent(key, values);
        GetTable1.GetTable(AppPath, WindowTitle, "PART_MruProjectItemABE", "ProgrammingAssignment");
    }
}

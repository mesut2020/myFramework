package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.Driver;

@CucumberOptions(
        //tags ="@FromExcel",
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        dryRun = false,
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"}
)

public class RunnerCrossBrowser extends AbstractTestNGCucumberTests {
    @BeforeClass
    @Parameters("browser")
    public static void beforClass(String browser)
    {
        Driver.threadBrowserName.set(browser);

    }
}

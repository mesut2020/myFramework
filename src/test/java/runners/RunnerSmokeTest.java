package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@smoke",
        features = {"src/test/java/features/"},
        glue = {"stepDefinitions"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"}
)

public class RunnerSmokeTest extends AbstractTestNGCucumberTests {
}

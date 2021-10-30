package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BasePage;
import utilities.Driver;
import utilities.ExcelUtility;

public class Hooks {
    @Before
    public void before(Scenario scenario){
        System.out.println(scenario.getName() + " has been started...");
    }

    @After
    public void after(Scenario scenario){
        System.out.println(scenario.getName() + " : " + scenario.getStatus());

        if (scenario.isFailed()) new BasePage().takeScreenshot(scenario.getName() + "_" + Driver.threadBrowserName.get());

        ExcelUtility.writeScenarioToExcel(scenario);

        Driver.quitDriver();
    }
}

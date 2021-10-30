package tests;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BasePage;
import utilities.Driver;


public class Tests extends BasePage {

    @BeforeTest
    public void beforeTest(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //Configuration.browserCapabilities = capabilities;
    }

    @AfterTest
    public void tearDown(){
        Driver.quitDriver();
    }

    @Test (priority = 1)
    public void loginPagetest(){
       LoginPage loginPage = new LoginPage();
       HomePage homePage = loginPage.loginToPage("Admin","admin123");
       Assert.assertTrue(loginPage.isExitWelcomeLink());
    }

    @Test
    public void testConvertStringToDouble(){
        System.out.println(isNumber("1.5"));
        System.out.println(isNumber("15"));
        System.out.println(isNumber("1a5"));

        System.out.println(stringToDouble("  $ 125.78 @  "));
        Assert.assertTrue(stringToDouble("$ 125.78")==125.78);
    }

    @Test(dependsOnMethods = {"loginPagetest"})  // bu testin çalışması, startCar ın hatasız çalışmasına bağımlı
    void driveCar()
    {
        System.out.println("Car driveCar");
    }

    @Test(groups = "Regression")  // Regression: Sistemin tumunu kontrol eden teste verilen isim...
    public void test1(){ System.out.println("Test 1 Regression");  }

}

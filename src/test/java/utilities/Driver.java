package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Driver {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();

    /**
     * this method returns the threadlocal webDriver
     * @return WebDriver
     */
    public static WebDriver getDriver(){

        if (threadBrowserName.get() == null){
            threadBrowserName.set("chrome");
        }

        if (threadDriver.get() == null){
            switch (threadBrowserName.get()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    threadDriver.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    threadDriver.set(new FirefoxDriver());
                    break;
                case "ie":
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    threadDriver.set(new InternetExplorerDriver());
                    break;
                case "edge":
                case "msedge":
                    WebDriverManager.edgedriver().setup();
                    threadDriver.set(new EdgeDriver());
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    threadDriver.set(new OperaDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    threadDriver.set(new ChromeDriver());
                    break;
            }

        }
        return threadDriver.get();
    }

    /**
     * quit the threadLocal WebDriver
     */
    public static void quitDriver(){
        if (threadDriver.get() != null){
            threadDriver.get().quit();
            threadDriver.set(null);
        }
    }
}
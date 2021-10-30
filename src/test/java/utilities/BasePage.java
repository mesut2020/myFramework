package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    WebElement element;

    public BasePage(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public void open(String url){
        driver.get(url);
        //driver.manage().deleteAllCookies();
    }

    public WebElement byToElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> byToList(By locator){
        return driver.findElements(locator);
    }

    public void clickTo(By locator){
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (StaleElementReferenceException st){
            wait.until(ExpectedConditions.refreshed( ExpectedConditions.elementToBeClickable(element))).click();
        } catch (ElementClickInterceptedException e){
            clickWithJS(element);
        }
    }

    public void sendTo(By locator, String key){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        scrollToElement(element);
        element.clear();
        element.sendKeys(key);
    }

    public boolean isElementExist(By locator){
        return byToList(locator).size()>0;
    }

    public void clickWithJS(WebElement element) {
        scrollToElement(element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     *
     * @param fileName is Scenario name + browser
     */
    public void takeScreenshot(String fileName){
        String directoryPath = "screenshots/";
        File directory = new File(directoryPath);
        //if (!directory.exists()) {
            directory.mkdir();
        //}

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String filePath = directoryPath + fileName + "_" + Thread.currentThread().getName() + "_" + DateUtil.todaysDateAndTime() + ".png";
        try {
            FileUtils.copyFile(file, new File(filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Double stringToDouble (String string) {
        return Double.parseDouble(string.replaceAll("[^0-9.]", ""));
    }

    public boolean isNumber (String string) {
        return string.replaceAll("[0-9.]", "").length()==0;
    }


}

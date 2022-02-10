package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());

    public void flash(WebElement element) {
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 8; i++) {
            changeColor("rgb(0,200,0)", element);// 1
            changeColor(bgcolor, element);// 2
        }
    }

    private void changeColor(String color, WebElement element) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void drawBorder(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void generateAlert(String message) {
        js.executeScript("alert('" + message + "')");
    }

    public void clickElementByJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);

    }

    public void refreshBrowserByJS() {
        js.executeScript("history.go(0)");
    }

    public String getTitleByJS() {
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public String getPageInnerText() {
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    public void scrollPageDown() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getBrowserInfo() {
        String uAgent = js.executeScript("return navigator.userAgent;").toString();
        return uAgent;
    }

    public void sendKeysUsingJS(String id, String value) {
        js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
    }
}

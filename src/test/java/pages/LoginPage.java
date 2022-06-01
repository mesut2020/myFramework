package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;
import utilities.ExcelUtility;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    private By userNameTextBox = By.id("txtUsername");
    private By passwordTextBox = By.id("txtPassword");
    private By loginBtn = By.id("btnLogin");
    private By welcomeLink = By.id("welcome");
    private By logout = By.linkText("Logout");
    private By invalidCredentialsMessage = By.id("spanMessage");

    public void iAmOnWebSite(String url){
        open(url);
    }

    public HomePage loginToPage(String username, String password){
        sendTo(userNameTextBox,username);
        sendTo(passwordTextBox, password);
        clickTo(loginBtn);
        return new HomePage();
    }

    public void getInvalidDataFromExcel(){
        List<List<String>> invalidData = ExcelUtility.getListData("src/main/resources/InvalidCredentials.xlsx", "Data", 2);

        HomePage homePage;
        for (int i = 0; i < invalidData.size(); i++) {
            homePage = loginToPage(invalidData.get(i).get(0), invalidData.get(i).get(1));
            System.out.println(invalidData.get(i).get(0) + " : " + invalidData.get(i).get(1));
        }
    }

    public void signOut(){
        clickTo(welcomeLink);
        clickTo(logout);
    }

    public boolean isExitWelcomeLink(){
        return isElementExist(welcomeLink);
    }
    public boolean isExitLoginButton(){
        return isElementExist(loginBtn);
    }
    public boolean isExitInvalidCredentialsMessage(){
        return isElementExist(invalidCredentialsMessage);
    }

}

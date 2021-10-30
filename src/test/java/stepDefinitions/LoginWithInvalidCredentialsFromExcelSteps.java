package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginWithInvalidCredentialsFromExcelSteps {
    LoginPage loginPage = new LoginPage();

    @When("user login with invalid credentials from Excel File")
    public void userLoginWithInvalidCredentialsFromExcelFile() {
        loginPage.getInvalidDataFromExcel();
        userSeeTheErrorMessage("");
    }

    @Then("User see the error message {string}")
    public void userSeeTheErrorMessage(String message) {
        Assert.assertTrue(loginPage.isExitInvalidCredentialsMessage());
    }
}

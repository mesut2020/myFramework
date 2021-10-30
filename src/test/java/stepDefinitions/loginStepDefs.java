package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utilities.BasePage;

public class loginStepDefs {

    LoginPage loginPage = new LoginPage();

    @Given("user on home page {string}")
    public void userOnHomePage(String url) {
        loginPage.iAmOnWebSite(url);
    }

    @When("user login as {string} and password as {string}")
    public void userLoginAsAndPasswordAs(String username, String password) {
        loginPage.loginToPage(username, password);
    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        Assert.assertTrue(loginPage.isExitWelcomeLink());
    }
}

package StepDefinitions;

import Page.BibitWebPage;
import Utils.PropertiesLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Setup.Setup;
import lombok.SneakyThrows;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

public class StepDefinitionWeb {
    BibitWebPage bibitWebPage = new BibitWebPage(Setup.driver);
    PropertiesLoader properties = Setup.properties;
    @Given("user is in bibit website")
    @SneakyThrows
    public void user_is_in_bibit_website() {
        bibitWebPage.openHomePage();
    }

    @Given("user is in logged in state")
    @SneakyThrows
    public void user_is_in_logged_in_state() {
        String pin = properties.getProperty("pin");
        bibitWebPage.enterPin(pin);
    }

    @When("user open {string} tab")
    public void user_open_tab(String tabType) {
        if(tabType.equalsIgnoreCase("explore")) {
            bibitWebPage.clickOnExplore();
        } else {
            throw new IllegalArgumentException("not implemented tab type");
        }
    }

    @When("user click on {string} menu")
    public void user_click_on_menu(String menuType) {
        if(menuType.equalsIgnoreCase("reksa dana")) {
            bibitWebPage.clickOnReksaDana();
        } else {
            throw new IllegalArgumentException("not implemented menu type");
        }
    }

    @When("user open product detail {string}")
    public void user_open_product_detail(String product) {
        if (product.equalsIgnoreCase("Reksadana Pasar Uang")) {
            bibitWebPage.clickOnPasarUang();
        } else {
            throw new IllegalArgumentException("not implemented product type");
        }
    }

    @Then("user will see list of products for {string}")
    public void user_will_see_list_of_products(String productType) {
        if (productType.equalsIgnoreCase("Reksadana Pasar Uang")) {
            assertNotNull("Product list is not empty", bibitWebPage.getPasarUangProducts());
        } else {
            throw new IllegalArgumentException("not implemented product type");
        }
    }

    @When("user logout")
    public void user_logout() {
        bibitWebPage.logOut();
    }

    @Then("user should see login page")
    public void user_should_see_login_page() {
        Assert.assertTrue("User is in login page", bibitWebPage.isLoginButtonShown());
    }
}

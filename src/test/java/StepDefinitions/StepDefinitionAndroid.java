package StepDefinitions;

import Page.BibitWebPage;
import Page.MyDemoAppAndroidPage;
import Setup.Setup;
import Utils.PropertiesLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

public class StepDefinitionAndroid {
    MyDemoAppAndroidPage demoAppPage = new MyDemoAppAndroidPage(Setup.driver);
    PropertiesLoader properties = Setup.properties;
    @Given("user is in app homepage")
    public void user_is_in_app_homepage() {
        Assert.assertTrue("User is in app", demoAppPage.isInHomepage());
    }
    @When("user login")
    public void user_login() {
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        demoAppPage.login(email,password);
    }
    @When("user buy product {string} with quantity {int} and color {string}")
    public void user_buy_product_with_quantity_and_color(String product,int qty, String color) {
        demoAppPage.selectProduct(product);
        demoAppPage.selectProductColor(color);
        demoAppPage.setProductQty(qty);
        demoAppPage.clickContinueButton();
        demoAppPage.clickCart();
        demoAppPage.clickContinueButton();
        demoAppPage.fillShippingAddress();
        demoAppPage.clickPaymentButton();

        String cardName = properties.getProperty("cardName");
        String cardNumber = properties.getProperty("cardNumber");
        String cardExp = properties.getProperty("cardExp");
        String cardCvv = properties.getProperty("cardCvv");
        demoAppPage.fillPaymentDetails(cardName, cardNumber, cardExp, cardCvv);

        demoAppPage.clickPaymentButton();

        Assert.assertTrue("User successfully buy product", demoAppPage.isOrderCheckoutCorrect(product, qty));

        demoAppPage.clickPaymentButton();
    }

    @Then("user successfully buy product")
    public void user_successfully_buy_product() {
        Assert.assertTrue("User successfully buy product",  demoAppPage.isCheckoutSuccess());
        demoAppPage.clickContinueShopping();
    }
    @When("user sort products by {string}")
    public void user_sort_products_by(String sortType) {
        demoAppPage.clickSort();
        demoAppPage.selectSortType(sortType);
    }
    @Then("user successfully sort products by {string}")
    public void user_successfully_sort_products(String sortType) {
        Assert.assertTrue("User successfully sort products by "+sortType, demoAppPage.isSortSuccess(sortType));
    }
}

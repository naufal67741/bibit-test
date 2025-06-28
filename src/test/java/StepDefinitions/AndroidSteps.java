package StepDefinitions;

import Utils.TestDataReader;
import io.cucumber.java.en.*;
import org.junit.Assert;
import Page.LoginPage;
import Page.ProductPage;
import Utils.DriverTools;

public class AndroidSteps {
    DriverTools driverTools = new DriverTools();
    LoginPage loginPage = new LoginPage(driverTools);
    ProductPage productPage = new ProductPage(driverTools);

    @Given("the user launches the app")
    public void launchApp() {
        // Already handled by Appium session startup
    }

    @When("the user logs in with valid credentials")
    public void userLogsIn() {
        String email = TestDataReader.get("email");
        String password = TestDataReader.get("password");
        loginPage.login(email, password);
    }

    @When("the user selects the {string} product")
    public void selectProduct(String productName) {
        productPage.selectProduct(productName);
    }

    @When("the user selects color {string}")
    public void selectColor(String color) {
        productPage.selectColor(color);
    }

    @When("the user sets quantity to {int}")
    public void setQuantity(int qty) {
        productPage.setQuantity(qty);
    }

    @When("the user adds the product to the cart")
    public void addToCart() {
        productPage.proceedToCheckout();
    }

    @When("the user inputs shipping address")
    public void the_user_inputs_shipping_address() {
        String fullName = TestDataReader.get("fullName");
        String addressLine1 = TestDataReader.get("addressLine1");
        String addressLine2 = TestDataReader.get("addressLine2");
        String city = TestDataReader.get("city");
        String state = TestDataReader.get("state");
        String zipCode = TestDataReader.get("zipCode");
        String country = TestDataReader.get("country");

        productPage.inputShippingAddress(fullName,addressLine1,addressLine2,city,state,zipCode,country);
        productPage.proceedToPayment();
    }

    @When("the user inputs payment details")
    public void the_user_inputs_payment_details() {
        String cardName = TestDataReader.get("cardName");
        String cardNumber = TestDataReader.get("cardNumber");
        String cardExpDate = TestDataReader.get("cardExpDate");
        String cardCVV = TestDataReader.get("cardCVV");

        productPage.inputPaymentDetails(cardName, cardNumber, cardExpDate, cardCVV);
        productPage.proceedToPayment();
    }


    @When("the user buys the {string} with {int} amount")
    public void buyProduct(String product, int qty) {
        Assert.assertTrue("User successfully buy product", productPage.verifyOrder(product, qty));
        productPage.proceedToPayment();
    }

    @Then("the user successfully bought the product")
    public void succeedPayment() {
        Assert.assertTrue("Checkout did not complete successfully", productPage.isSuccessScreenDisplayed());
        productPage.clickContinueShopping();
    }

    @When("user sort products by {string}")
    public void userSortProducts(String sortType) {
        productPage.sortBy(sortType);
    }

    @Then("user successfully sort products by {string}")
    public void validateSort(String sortType) {
        Assert.assertTrue("Sort validation failed", productPage.validateSort(sortType));
    }

}

package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.Assert;
import Page.BibitWeb;
import Utils.DriverTools;

public class WebSteps {
    DriverTools driverTools = new DriverTools();
    BibitWeb bibitWeb = new BibitWeb(driverTools);

    @Given("the user is logged into Bibit")
    public void the_user_is_logged_in() {
        bibitWeb.openBibitURL();
    }

    @When("the user clicks on the Explore tab")
    public void the_user_clicks_on_tab(){
        bibitWeb.clickExploreTab();
    }

    @When("the user searches for Reksadana")
    public void the_user_searches_for_product() {
        bibitWeb.searchProduct();
    }

    @When("user open Reksadana Saham")
    public void user_open_product_detail() {
        bibitWeb.clickReksadanaSaham();
    }

    @Then("investment search results should be displayed")
    public void results_should_be_displayed() {
        Assert.assertTrue("Reksadana Saham Shown", bibitWeb.isReksadanaSahamShown());
    }

    @After
    public void tearDown() {
        DriverTools.quitDriver();
    }
}

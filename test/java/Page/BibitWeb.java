package Page;

import Utils.DriverTools;
import Utils.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BibitWeb {
    private WebDriver driver;
    private final DriverTools driverTools;

    public BibitWeb(DriverTools driverTools) {
        this.driverTools = driverTools;
        this.driver = DriverTools.getDriver();
    }
    Locator EXPLORE_TABBAR = new Locator("EXPLORE_TABBAR", By.xpath("//*[@data-testid='tabbar-explore']"));
    Locator REKSADANA_CARD = new Locator("REKSADANA_CARD", By.xpath("//a[@data-title='reksadana' and contains(@href, '/explore/mutualfund')]"));
    Locator REKSADANA_SAHAM_TITLE = new Locator("REKSADANA_SAHAM_TITLE", By.xpath("//div[@data-testid='search-action-company-group-type-equity' and @data-title='saham']"));
    Locator REKSADANA_SAHAM_TEXT = new Locator("REKSADANA_SAHAM_TEXT", By.xpath("//div[contains(text(),'Reksa Dana Saham')]"));

    public void openBibitURL() {
        driver.get("https://app.bibit.id/login");
    }


    public void clickExploreTab() {
        driverTools.waitForElementToAppear(EXPLORE_TABBAR, 10);
        driverTools.click(EXPLORE_TABBAR);
    }
    public void clickReksadanaSaham() {
        driverTools.waitForElementToAppear(REKSADANA_SAHAM_TITLE, 10);
        driverTools.click(REKSADANA_SAHAM_TITLE);
    }

    public void searchProduct() {
        driverTools.waitForElementToAppear(REKSADANA_CARD, 10);
        driverTools.click(REKSADANA_CARD);
    }

    public boolean isReksadanaSahamShown() {
        return driverTools.waitForElementToAppear(REKSADANA_SAHAM_TEXT,10);
    }
}

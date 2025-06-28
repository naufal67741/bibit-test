package Page;

import Utils.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utility.DriverMethods;

import java.util.List;

public class BibitWebPage {
    private DriverMethods driverMethods;
    public BibitWebPage(WebDriver driver) {
        this.driverMethods = new DriverMethods(driver);
    }

    Locator MUTUAL_FUND = new Locator("Mutual Fund", By.xpath("//*[contains(@listtype,'card') and contains(@href,'mutualfund')]"));
    Locator EXPLORE = new Locator("Explore tab footer", By.xpath("//*[@data-testid='tabbar-explore']"));
    Locator PROFILE = new Locator("Profile tab footer", By.xpath("//*[@data-testid='tabbar-profile']"));
    Locator LOG_OUT = new Locator("Log out button", By.xpath("//*[contains(text(),'Log Out')]"));
    Locator LOGIN_BUTTON = new Locator("Login button", By.xpath("//*[@data-testid='nonlogin-login-btn']"));
    Locator PASAR_UANG = new Locator("Pasar uang tab", By.xpath("//*[@data-category='moneymarket']"));
    Locator REKSADANA_TITLE = new Locator("Title reksadana", By.xpath("//*[contains(@class,'reksadana-top-title')]"));


    public void openHomePage() {
        System.out.println("Opening Bibit home page");
        driverMethods.maximizeWindow();
        driverMethods.openLink("https://app.bibit.id");
    }

    public void enterPin(String pin){
        System.out.println("Entering pin "+pin);
        for (int i = 0; i < pin.length(); i++) {
            driverMethods.click(getPinLocator(String.valueOf(pin.charAt(i))));
        }
    }

    private Locator getPinLocator(String pinNumber){
        return new Locator(pinNumber, By.xpath("//div[@data-valpin='"+pinNumber+"']"));
    }

    public void clickOnReksaDana(){
        driverMethods.click(MUTUAL_FUND);
    }

    public void clickOnExplore(){
        driverMethods.click(EXPLORE);
    }

    public void clickOnPasarUang(){
        driverMethods.click(PASAR_UANG);
    }

    public List<String> getPasarUangProducts(){
        List<String> reksadanaTitles = driverMethods.getTexts(REKSADANA_TITLE);
        System.out.println("Reksadana titles: "+reksadanaTitles);
        return reksadanaTitles;
    }

    public void logOut(){
        driverMethods.click(PROFILE);
        driverMethods.scrollIntoView(LOG_OUT);
        driverMethods.click(LOG_OUT);
    }

    public boolean isLoginButtonShown(){
        return driverMethods.waitForElementToAppear(LOGIN_BUTTON, 5);
    }
}

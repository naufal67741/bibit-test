package Page;

import Utils.DriverTools;
import Utils.Locator;
import org.openqa.selenium.By;

public class LoginPage {
    private final DriverTools driverTools;

    public LoginPage(DriverTools driverTools) {
        this.driverTools = driverTools;
    }

    private static final Locator SIDE_TAB = new Locator("SIDE_TAB", By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
    private static final Locator LOGIN_TAB = new Locator("LOGIN_TAB", By.xpath("//android.widget.TextView[@content-desc='Login Menu Item']"));
    private static final Locator EMAIL_FIELD = new Locator("EMAIL_FIELD", By.id("com.saucelabs.mydemoapp.android:id/nameET"));
    private static final Locator PASSWORD_FIELD = new Locator("PASSWORD_FIELD", By.id("com.saucelabs.mydemoapp.android:id/passwordET"));
    private static final Locator LOGIN_BUTTON = new Locator("LOGIN_BUTTON", By.id("com.saucelabs.mydemoapp.android:id/loginBtn"));

    public void login(String email, String password) {
        System.out.println("Logging in with email: " + email + " and password: " + password);
        driverTools.click(SIDE_TAB);
        driverTools.click(LOGIN_TAB);
        driverTools.sendKeys(EMAIL_FIELD, email);
        driverTools.sendKeys(PASSWORD_FIELD, password);
        driverTools.click(LOGIN_BUTTON);
    }
}

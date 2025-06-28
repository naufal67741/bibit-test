package Utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DriverTools {
    public enum PlatformType {
        WEB, ANDROID
    }

    private static WebDriver driver;
    private static PlatformType platform;

    public void click(Locator locator) {
        waitForElementToBePresent(locator, 5);
        System.out.println("Clicking on element " + locator.name);
        driver.findElement(locator.by).click();
    }

    public List<String> getTexts(Locator locator){
        List<WebElement> elements = getElements(locator);
        System.out.println("Getting texts from elements "+ locator.name);
        return elements.stream().map(WebElement::getText).toList();
    }

    public int getInt(Locator locator) {
        String text = getText(locator);
        System.out.println("Parsing int from: " + text);
        return Integer.parseInt(text.trim());
    }


    public void waitForElementToBePresent(Locator locator, int seconds) {
        System.out.println("Waiting for element to be present "+ locator.name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator.by)));
    }

    public List<WebElement> getElements(Locator locator) {
        waitForElementToBePresent(locator, 5);
        System.out.println("Getting elements "+ locator.name);
        return driver.findElements(locator.by);
    }

    public boolean waitForElementToAppear(Locator locator, int seconds) {
        System.out.println("Waiting for element to appear "+ locator.name);
        List<WebElement> elements = getElements(locator);
        boolean result = elements.stream().map(WebElement::isDisplayed).findFirst().orElse(false);
        System.out.println("Element appeared: "+ result);
        return result;
    }

    public void sendKeys(Locator locator, String keys){
        waitForElementToBePresent(locator, 5);
        System.out.println("Sending keys to element "+ locator.name);
        driver.findElement(locator.by).sendKeys(keys);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String platformProperty = System.getProperty("platform", "web").toLowerCase();
            platform = platformProperty.equals("android") ? PlatformType.ANDROID : PlatformType.WEB;

            switch (platform) {
                case WEB:
                    setupWebDriver();
                    break;
                case ANDROID:
                    setupAndroidDriver();
                    break;
            }
        }
        return driver;
    }

    private static void setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/Users/naufalhafiz/automation-profile");
        options.addArguments("profile-directory=Automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
    }

    public String getText(Locator locator) {
        WebElement element = getElements(locator).get(0);
        String text = element.getText();
        System.out.println("Text from " + locator.name + ": " + text);
        return text;
    }
    private static void setupAndroidDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:deviceName", "Pixel 6");
            caps.setCapability("appium:udid", "emulator-5554");
            caps.setCapability("appium:platformVersion", "14.0");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:app", "/Users/naufalhafiz/Documents/bibit/web/bibit-web/src/test/resources/app/mda-2.2.0-25.apk");
            caps.setCapability("appium:noReset", true);
            caps.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
            caps.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
            caps.setCapability("appium:appWaitActivity", "*");
            caps.setCapability("appium:appWaitDuration", 20000); // 20 seconds



            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize AppiumDriver", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

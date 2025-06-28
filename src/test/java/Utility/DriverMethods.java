package Utility;

import Utils.Locator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverMethods {
    private WebDriver driver;
    public DriverMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void openLink(String link) {
        System.out.println("Opening link: " + link);
        driver.get(link);
    }

    public void maximizeWindow() {
        System.out.println("Maximizing window");
        driver.manage().window().maximize();
    }

    public void click(Locator locator){
        waitForElementToBePresent(locator, 5);
        System.out.println("Clicking on element "+ locator.name);
        driver.findElement(locator.by).click();
    }

    public void waitForElementToBePresent(Locator locator, int seconds){
        System.out.println("Waiting for element to be present "+ locator.name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator.by)));
    }

    public List<WebElement> getElements(Locator locator){
        waitForElementToBePresent(locator, 5);
        System.out.println("Getting elements "+ locator.name);
        return driver.findElements(locator.by);
    }

    public List<String> getTexts(Locator locator){
        List<WebElement> elements = getElements(locator);
        System.out.println("Getting texts from elements "+ locator.name);
        return elements.stream().map(WebElement::getText).toList();
    }

    public String getText(Locator locator){
        System.out.println("Getting text from element "+ locator.name);
        WebElement element = getElements(locator).get(0);
        return element.getText();
    }

    public boolean waitForElementToAppear(Locator locator, int seconds){
        System.out.println("Waiting for element to appear "+ locator.name);
        List<WebElement> elements = getElements(locator);
        boolean result = elements.stream().map(WebElement::isDisplayed).findFirst().orElse(false);
        System.out.println("Element appeared: "+ result);
        return result;
    }

    public void scrollIntoView(Locator locator){
        waitForElementToBePresent(locator, 5);
        System.out.println("Scrolling into view "+ locator.name);
        WebElement element = driver.findElement(locator.by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendKeys(Locator locator, String keys){
        waitForElementToBePresent(locator, 5);
        System.out.println("Sending keys to element "+ locator.name);
        driver.findElement(locator.by).sendKeys(keys);
    }
}

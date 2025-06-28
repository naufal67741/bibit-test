package Page;

import Utils.DriverTools;
import Utils.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductPage {
    private final DriverTools driverTools;
    private final WebDriver driver;

    public ProductPage(DriverTools driverTools) {
        this.driverTools = driverTools;
        this.driver = DriverTools.getDriver();
    }

    private static final Locator QTY_FIELD = new Locator("QTY", By.id("com.saucelabs.mydemoapp.android:id/noTV"));
    private static final Locator INCREMENT_BUTTON = new Locator("PLUS", By.id("com.saucelabs.mydemoapp.android:id/plusIV"));
    private static final Locator DECREMENT_BUTTON = new Locator("MINUS", By.id("com.saucelabs.mydemoapp.android:id/minusIV"));
    private static final Locator ADD_BUTTON = new Locator("ADD_TO_CART", By.id("com.saucelabs.mydemoapp.android:id/cartBt"));
    private static final Locator CART_ICON = new Locator("CART_ICON", By.id("com.saucelabs.mydemoapp.android:id/cartRL"));
    private static final Locator CONTINUE_BUTTON = new Locator("CONTINUE", By.id("com.saucelabs.mydemoapp.android:id/cartBt"));
    private static final Locator SHIPPING_SUCCESS = new Locator("CHECKOUT_COMPLETE", By.id("com.saucelabs.mydemoapp.android:id/completeTV"));

    private static final Locator SORT_TRIGGER = new Locator("SORT_TRIGGER", By.id("com.saucelabs.mydemoapp.android:id/sortIV"));
    private static final Locator PRODUCT_NAME_LOCATOR = new Locator("PRODUCT_TITLE", By.id("com.saucelabs.mydemoapp.android:id/titleTV"));
    private static final Locator PRODUCT_PRICE_LOCATOR = new Locator("PRODUCT_PRICE", By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
    private static final Locator FULL_NAME = new Locator("FULL_NAME", By.id("com.saucelabs.mydemoapp.android:id/fullNameET"));
    private static final Locator ADDRESS_LINE_1 = new Locator("ADDRESS_LINE_1", By.id("com.saucelabs.mydemoapp.android:id/address1ET"));
    private static final Locator ADDRESS_LINE_2 = new Locator("ADDRESS_LINE_2", By.id("com.saucelabs.mydemoapp.android:id/address2ET"));
    private static final Locator CITY = new Locator("CITY", By.id("com.saucelabs.mydemoapp.android:id/cityET"));
    private static final Locator STATE = new Locator("STATE", By.id("com.saucelabs.mydemoapp.android:id/stateET"));
    private static final Locator ZIP_CODE = new Locator("ZIP_CODE", By.id("com.saucelabs.mydemoapp.android:id/zipET"));
    private static final Locator COUNTRY = new Locator("COUNTRY", By.id("com.saucelabs.mydemoapp.android:id/countryET"));
    private static final Locator PROCEED_PAYMENT_BUTTON = new Locator("PROCEED_PAYMENT_BUTTON", By.id("com.saucelabs.mydemoapp.android:id/paymentBtn"));
    private static final Locator CARD_NAME = new Locator("CARD_NAME", By.id("com.saucelabs.mydemoapp.android:id/nameET"));
    private static final Locator CARD_NUMBER = new Locator("CARD_NUMBER", By.id("com.saucelabs.mydemoapp.android:id/cardNumberET"));
    private static final Locator CARD_EXP_DATE = new Locator("CARD_EXP_DATE", By.id("com.saucelabs.mydemoapp.android:id/expirationDateET"));
    private static final Locator CARD_CVV = new Locator("CARD_CVV", By.id("com.saucelabs.mydemoapp.android:id/securityCodeET"));
    private static final Locator CONTINUE_SHOPPING_BUTTON = new Locator("CONTINUE_SHOPPING_BUTTON", By.id("com.saucelabs.mydemoapp.android:id/shoopingBt"));

    public void selectProduct(String name) {
        Locator product = new Locator("Product: " + name,
                By.xpath("//android.widget.TextView[@content-desc='Product Title' and @text='" + name + "']/preceding-sibling::*"));
        System.out.println("Selected product: " + name);
        driverTools.click(product);
    }

    public void selectColor(String color) {
        Locator colorOption = new Locator("Color: " + color,
                By.xpath("//android.widget.ImageView[@content-desc='" + color + " color']"));
        System.out.println("Selected color: " + color);
        driverTools.click(colorOption);
    }

    public void proceedToPayment(){
        driverTools.waitForElementToAppear(PROCEED_PAYMENT_BUTTON,10);
        driverTools.click(PROCEED_PAYMENT_BUTTON);
    }

    public void setQuantity(int desiredQty) {
        System.out.println("Adjusting quantity to: " + desiredQty);
        int currentQty = driverTools.getInt(QTY_FIELD);
        Locator adjustmentBtn = desiredQty > currentQty ? INCREMENT_BUTTON : DECREMENT_BUTTON;
        for (int i = 0; i < Math.abs(desiredQty - currentQty); i++) {
            driverTools.click(adjustmentBtn);
        }
    }

    public void proceedToCheckout() {
        driverTools.click(ADD_BUTTON);
        driverTools.click(CART_ICON);
        driverTools.click(CONTINUE_BUTTON);
    }

    public boolean verifyOrder(String productName, int expectedQty) {
        String actualProduct = driverTools.getText(PRODUCT_NAME_LOCATOR);
        int actualQty = Integer.parseInt(driverTools.getText(new Locator("ORDER_QTY", By.id("com.saucelabs.mydemoapp.android:id/itemNumberTV"))).split(" ")[0]);
        return actualProduct.equals(productName) && actualQty == expectedQty;
    }

    public void clickContinueShopping(){
        driverTools.click(CONTINUE_SHOPPING_BUTTON);
    }

    public boolean isSuccessScreenDisplayed() {
        return driverTools.waitForElementToAppear(SHIPPING_SUCCESS, 5);
    }

    public void sortBy(String type) {
        driverTools.click(SORT_TRIGGER);
        driverTools.click(new Locator("Sort: " + type, By.xpath("//*[@text='" + type + "']")));
    }

    public void inputShippingAddress(String fullName, String addressLine1, String addressLine2, String city, String state, String zipCode, String country){
        driverTools.sendKeys(FULL_NAME,fullName);
        driverTools.sendKeys(ADDRESS_LINE_1,addressLine1);
        driverTools.sendKeys(ADDRESS_LINE_2,addressLine2);
        driverTools.sendKeys(CITY,city);
        driverTools.sendKeys(STATE,state);
        driverTools.sendKeys(ZIP_CODE,zipCode);
        driverTools.sendKeys(COUNTRY,country);
    }

    public void inputPaymentDetails(String cardName, String cardNumber, String cardExpDate, String cardCVV){
        driverTools.sendKeys(CARD_NAME ,cardName);
        driverTools.sendKeys(CARD_NUMBER ,cardNumber);
        driverTools.sendKeys(CARD_EXP_DATE,cardExpDate);
        driverTools.sendKeys(CARD_CVV,cardCVV);
    }


    public boolean validateSort(String type) {
        if (type.equals("Name - Descending")) {
            List<String> names = driverTools.getTexts(PRODUCT_NAME_LOCATOR);
            return names.equals(names.stream().sorted((a, b) -> b.compareTo(a)).toList());
        }
        if (type.equals("Price - Ascending")) {
            List<String> prices = driverTools.getTexts(PRODUCT_PRICE_LOCATOR);
            return prices.equals(prices.stream()
                    .sorted((a, b) -> Double.compare(Double.parseDouble(a.replace("$", "")), Double.parseDouble(b.replace("$", ""))))
                    .toList());
        }
        throw new IllegalArgumentException("Unsupported sort type: " + type);
    }
}

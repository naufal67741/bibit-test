package Page;

import Utility.DriverMethods;
import Utils.Locator;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MyDemoAppAndroidPage {
    private DriverMethods driverMethods;
    public MyDemoAppAndroidPage(WebDriver driver) {
        this.driverMethods = new DriverMethods(driver);
    }
    Locator APP_HEADER = new Locator("App header", By.id("com.saucelabs.mydemoapp.android:id/mTvTitle"));
    Locator SIDE_TAB = new Locator("Side tab", By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
    Locator LOGIN_TAB = new Locator("Login tab", By.xpath("//android.widget.TextView[@content-desc='Login Menu Item']"));
    Locator USERNAME_FORM = new Locator("Username form", By.id("com.saucelabs.mydemoapp.android:id/nameET"));
    Locator PASSWORD_FORM = new Locator("Password form", By.id("com.saucelabs.mydemoapp.android:id/passwordET"));
    Locator LOGIN_BUTTON = new Locator("Login button", By.id("com.saucelabs.mydemoapp.android:id/loginBtn"));
    Locator QUANTITY = new Locator("Quantity", By.id("com.saucelabs.mydemoapp.android:id/noTV"));
    Locator PLUS_BUTTON = new Locator("Plus button", By.id("com.saucelabs.mydemoapp.android:id/plusIV"));
    Locator MINUS_BUTTON = new Locator("Minus button", By.id("com.saucelabs.mydemoapp.android:id/minusIV"));
    Locator CONTINUE_BUTTON = new Locator("Add to cart button, proceed to checkout", By.id("com.saucelabs.mydemoapp.android:id/cartBt"));
    Locator CART_BUTTON = new Locator("Cart button", By.id("com.saucelabs.mydemoapp.android:id/cartRL"));
    Locator FULL_NAME = new Locator("Full name", By.id("com.saucelabs.mydemoapp.android:id/fullNameET"));
    Locator ADDRESS_1 = new Locator("Address 1", By.id("com.saucelabs.mydemoapp.android:id/address1ET"));
    Locator ADDRESS_2 = new Locator("Address 2", By.id("com.saucelabs.mydemoapp.android:id/address2ET"));
    Locator CITY = new Locator("City", By.id("com.saucelabs.mydemoapp.android:id/cityET"));
    Locator STATE = new Locator("State", By.id("com.saucelabs.mydemoapp.android:id/stateET"));
    Locator POSTAL_CODE = new Locator("Postal code", By.id("com.saucelabs.mydemoapp.android:id/zipET"));
    Locator COUNTRY = new Locator("Country", By.id("com.saucelabs.mydemoapp.android:id/countryET"));
    Locator PAYMENT_BUTTON = new Locator("Payment button", By.id("com.saucelabs.mydemoapp.android:id/paymentBtn"));
    Locator CARD_NUMBER = new Locator("Card number", By.id("com.saucelabs.mydemoapp.android:id/cardNumberET"));
    Locator EXPIRY_DATE = new Locator("Expiry date", By.id("com.saucelabs.mydemoapp.android:id/expirationDateET"));
    Locator CVV = new Locator("CVV", By.id("com.saucelabs.mydemoapp.android:id/securityCodeET"));
    Locator PRODUCT_NAME = new Locator("Product name", By.id("com.saucelabs.mydemoapp.android:id/titleTV"));
    Locator PRODUCT_COLOR = new Locator("Product color", By.id("com.saucelabs.mydemoapp.android:id/colorIV"));
    Locator PRODUCT_QTY = new Locator("Product quantity", By.id("com.saucelabs.mydemoapp.android:id/itemNumberTV"));
    Locator CHECKOUT_COMPLETE = new Locator("Checkout complete", By.id("com.saucelabs.mydemoapp.android:id/completeTV"));
    Locator CONTINUE_SHOPPING_BUTTON = new Locator("Continue shopping button", By.id("com.saucelabs.mydemoapp.android:id/shoopingBt"));
    Locator SORT_BUTTON = new Locator("Sort button", By.id("com.saucelabs.mydemoapp.android:id/sortIV"));
    Locator PRODUCT_PRICE = new Locator("Product price", By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
    public boolean isInHomepage() {
        return driverMethods.waitForElementToAppear(APP_HEADER,5);
    }

    @SneakyThrows
    public void login(String email, String password){
        System.out.println("Logging in with email: "+email+" and password: "+password);
        driverMethods.click(SIDE_TAB);
        driverMethods.click(LOGIN_TAB);
        driverMethods.sendKeys(USERNAME_FORM,email);
        driverMethods.sendKeys(PASSWORD_FORM,password);
        driverMethods.click(LOGIN_BUTTON);
    }

    public void selectProduct(String product){
        System.out.println("Selecting product: "+product);
        driverMethods.click(getProductLocator(product));
    }

    public void selectProductColor(String color){
        System.out.println("Selecting color: "+color);
        driverMethods.click(getProductColorLocator(color));
    }

    public void setProductQty(int qty){
        System.out.println("Setting quantity: "+qty);
        int currentQty = Integer.parseInt(driverMethods.getText(QUANTITY));
        boolean isMore = qty > currentQty;
        for(int i = 0; i < Math.abs(qty-currentQty); i++){
            if(isMore){
                driverMethods.click(PLUS_BUTTON);
            }else{
                driverMethods.click(MINUS_BUTTON);
            }
        }
    }

    public void clickContinueButton(){
        driverMethods.click(CONTINUE_BUTTON);
    }

    public void clickCart(){
        driverMethods.click(CART_BUTTON);
    }

    public void fillShippingAddress(){
        driverMethods.sendKeys(FULL_NAME,"John Doe");
        driverMethods.sendKeys(ADDRESS_1,"Jl. Jendral Sudirman");
        driverMethods.sendKeys(ADDRESS_2,"Kav. 52-53");
        driverMethods.sendKeys(CITY,"Jakarta");
        driverMethods.sendKeys(STATE,"DKI Jakarta");
        driverMethods.sendKeys(POSTAL_CODE,"10210");
        driverMethods.sendKeys(COUNTRY,"Indonesia");
    }

    public void clickPaymentButton(){
        driverMethods.click(PAYMENT_BUTTON);
    }

    public void fillPaymentDetails(String cardName, String cardNumber, String cardExp, String cardCvv){
        driverMethods.sendKeys(USERNAME_FORM,cardName);
        driverMethods.sendKeys(CARD_NUMBER,cardNumber);
        driverMethods.sendKeys(EXPIRY_DATE,cardExp);
        driverMethods.sendKeys(CVV,cardCvv);
    }

    public boolean isOrderCheckoutCorrect(String productName, int qty){
        String productNameGot = driverMethods.getText(PRODUCT_NAME);
        System.out.println("Product name got: "+productNameGot);
        String qtyGot = driverMethods.getText(PRODUCT_QTY).split(" ")[0];
        System.out.println("Qty got: "+qtyGot);

        return productNameGot.equals(productName) && qtyGot.equals(String.valueOf(qty));
    }

    public boolean isCheckoutSuccess(){
        return driverMethods.waitForElementToAppear(CHECKOUT_COMPLETE,5);
    }

    public void clickContinueShopping(){
        driverMethods.click(CONTINUE_SHOPPING_BUTTON);
    }

    private Locator getProductLocator(String product){
        return new Locator("Product "+product, By.xpath("//android.widget.TextView[@content-desc='Product Title' and @text='"+product+"']/preceding-sibling::*"));
    }

    private Locator getProductColorLocator(String color){
        return new Locator("Color "+color, By.xpath("//android.widget.ImageView[@content-desc='"+color+" color']"));
    }

    public void clickSort(){
        driverMethods.click(SORT_BUTTON);
    }

    public void selectSortType(String sortType){
        driverMethods.click(getSortTypeLocator(sortType));
    }

    private Locator getSortTypeLocator(String sortType){
        return new Locator("Sort type "+sortType, By.xpath("//*[@text='"+sortType+"']"));
    }

    public boolean isSortSuccess(String sortType){

        if(sortType.equals("Name - Descending")){
            List<String> productNames = driverMethods.getTexts(PRODUCT_NAME);
            List<String> sortedProductNames = productNames.stream().sorted((a,b) -> b.compareTo(a)).toList();
            System.out.println("Product names: "+productNames);
            System.out.println("Sorted product names: "+sortedProductNames);
            return productNames.equals(sortedProductNames);

        } else if (sortType.equals("Price - Ascending")) {
            List<String> productPrices = driverMethods.getTexts(PRODUCT_PRICE);
            List<String> sortedProductPrices = productPrices.stream().sorted((a,b) -> {
                double priceA = Double.parseDouble(a.replace("$",""));
                double priceB = Double.parseDouble(b.replace("$",""));
                return Double.compare(priceA,priceB);
            }).toList();
            System.out.println("Product prices: "+productPrices);
            System.out.println("Sorted product prices: "+sortedProductPrices);
            return productPrices.equals(sortedProductPrices);
        }
        throw new IllegalArgumentException("Sort type not implemented");
    }
}

package Setup;
import Utils.PropertiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Setup {
    public static WebDriver driver;
    public static PropertiesLoader properties;

    @Before
    public void setUp() throws IOException {
        String propertiesPath = "src/test/resources/TestData/testData.properties";
        properties = new PropertiesLoader(propertiesPath);
        String platform= System.getProperty("platform").toLowerCase();

        if(platform.equals("api")){
            return;
        }

        String capabilitiesPath = "src/test/resources/Capabilities/"+platform+"Capabilities.json";

        CapabilitiesLoader loader = new CapabilitiesLoader();
        DesiredCapabilities capabilities = loader.loadCapabilities(capabilitiesPath);
        try {
            if(platform.equalsIgnoreCase("chrome")){
                driver = new RemoteWebDriver(new URL("http://0.0.0.0:4724/wd/hub"), capabilities);
            } else if (platform.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(new URL("http://0.0.0.0:4724/wd/hub"), capabilities);
            }
            System.out.println("Driver initialized: " + driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

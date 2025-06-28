package Setup;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CapabilitiesLoader {

    public DesiredCapabilities loadCapabilities(String jsonFilePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        System.out.println("capabilitiesJson: " + content);

        JSONObject capabilitiesObject = new JSONObject(content);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilitiesObject.keys().forEachRemaining(key -> {
            Object value = capabilitiesObject.get(key);
            if (value instanceof JSONObject) {
                JSONObject nestedObject = (JSONObject) value;
                nestedObject.keys().forEachRemaining(innerKey -> {
                    capabilities.setCapability(innerKey, nestedObject.get(innerKey));
                });
            } else {
                capabilities.setCapability(key, value);
            }
        });

        return capabilities;
    }
}

package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private Properties properties;

    public PropertiesLoader(String filePath) throws IOException {
        properties = new Properties();
        FileInputStream inputStream = new FileInputStream(filePath);
        properties.load(inputStream);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

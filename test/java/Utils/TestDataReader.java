package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = TestDataReader.class.getClassLoader()
                .getResourceAsStream("testdata/testdata.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("Test data file not found!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api.feature",
        glue = {"StepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports/api"},
        tags = "@api"
)
public class APITestRunner {
}
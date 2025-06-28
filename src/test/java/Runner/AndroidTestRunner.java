package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/android.feature",
        glue = {"StepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports-android"},
        monochrome = true
)
public class AndroidTestRunner {}

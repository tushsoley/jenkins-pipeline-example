package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"features/stepDefinitions"},
//        monochrome = true,
//        strict = true,
        plugin = {"html:target/cucumber-html-report",
                "json:target/cucumber-results.json"},
        dryRun = false,
        tags = {"@test"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}

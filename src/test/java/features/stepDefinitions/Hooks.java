package features.stepDefinitions;

import com.fasterxml.jackson.databind.SerializationFeature;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import libs.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Hooks extends DriverFactory {
    /*private static String scenarioName;
    private static Scenario cukeScenario;*/
    /*@Before
     *//*public void setup(Scenario cukeScenarioObj) throws IOException {
        cukeScenario = cukeScenarioObj;
        scenarioName ="test";
//        boolean tagFound = false;
//        for (String tag : cukeScenario.getSourceTagNames()) {
//            if (tag.toLowerCase().contains("scenario_")) {
//                scenarioName = tag.substring(1);
//                tagFound = true;
//            }
//        }
//        if (!tagFound) {
//            System.out.println("Scenario tag not set. Please set a tag '@Scenario_<scenarioName> for the scenario");
//            System.exit(1);
//        }
        _driver = getWebDriver(cukeScenario.getName(), scenarioName);
    }*/

    @Before
    public void setup(cucumber.api.Scenario scenario) {
        String scenarioName = scenario.getName();

        _driver = getWebDriver(scenarioName);
    }


    /*@After
    public void tearDownAndScreenShotOnFailure(cucumber.api.Scenario cukeScenario) throws IOException {
        scenarios.setScenarioName(cukeScenario.getName());
        try {
            if (_driver != null && cukeScenario.isFailed()) {
                cukeScenario.embed(((TakesScreenshot) _driver).getScreenshotAs(OutputType.BYTES), "image/png");
                scenarios.setTestStatus("Fail");
            } else if (!cukeScenario.isFailed()) {
                scenarios.setTestStatus("Pass");
            }
            if (_driver != null) {
                _driver.manage().deleteAllCookies();
                _driver.quit();
                _driver = null;
            }
        } catch (Exception e) {
            System.out.println("Methods failed: tearDownAndScreenShotOnFailure, Exception: " + e.getMessage());
        }
    }*/
    @After
    public void tearDown(cucumber.api.Scenario scenario) {
        try {
            if (_driver != null && scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            }
        } catch (Exception e) {
            System.out.println("Methods failed: tearDown, Exception: " + e.getMessage());
        } finally {
            if (_driver != null) {
                _driver.quit();
                _driver = null;
            }
        }

    }
}

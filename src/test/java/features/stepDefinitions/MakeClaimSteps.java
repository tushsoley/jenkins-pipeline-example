package features.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverFactory;

public class MakeClaimSteps extends DriverFactory {
    @Given("application is launched")
    public void applicationIsLaunched() {
        _makeClaim.launchWebApplication("https://onzl-dev.outsystemscloud.com/CryptopiaPOCWeb/Entry2.aspx?_ts=637299081110673603");
    }

    @When("user select to make a claim")
    public void userSelectToMakeAClaim() {
        _makeClaim.makeclaim();
    }

    @Then("user make a claim without captcha")
    public void userMakeAClaimWithoutCaptcha() {
        _makeClaim.enterEmails();
    }
}

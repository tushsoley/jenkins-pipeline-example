package PageObjects;

import libs.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePage extends BasePage {

    private @FindBy(xpath = "//a[contains(@id,'LodgeClaim')]")
    WebElement linkMakeClaim;

    private @FindBy(xpath = "//input[contains(@name,'RegisteredEmailAddress')]")
    WebElement registeredEmailAddress;

    private @FindBy(xpath = "//input[contains(@name,'RegularEmailAddress')]")
    WebElement regularEmailAddress;

    private @FindBy(xpath = "//input[@value='Submit']")
    WebElement Submit;

    public void launchWebApplication(String url){
        launchApp(url);
    }

    public void makeclaim(){
        waitAndClickElement(linkMakeClaim);
    }

    public void enterEmails(){
        sendKeysToWebElement(registeredEmailAddress,"tushar.soley@optimationgroup.com");
        sendKeysToWebElement(regularEmailAddress,"test@optimationgroup.com");
    }

    public void clickSubmit(){
        waitAndClickElement(Submit);
    }
}

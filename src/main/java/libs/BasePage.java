package libs;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

public class BasePage extends DriverFactory{
    protected WebDriverWait wait;
    public BasePage() {
        if (_driver != null) {
            this.wait = new WebDriverWait(_driver, 30);
        }

    }

    public void launchApp(String url){
        _driver.get(url);
    }

    public void sendKeysToWebElement(WebElement element, String textToSend) {
        try {
//            waitForPageComponentLoadFluentWait(element);
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element));
//                waitForPageComponentLoadFluentWait(element);
            }catch (Exception e) {
                System.out.println("Unable to wait on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            element.clear();
//            waitForPageComponentLoadFluentWait(element);
            element.sendKeys(textToSend);
            System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to locate WebElement: " + "<" + element.toString() + "> and sendResultsToElasticSearch the following keys: " + textToSend);
            Assert.fail("Unable to keys to WebElement, Exception: " + e.getMessage());
        }
    }

    /*public void waitForPageComponentLoadFluentWait(WebElement elementToCheck) {
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(_driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(java.util.NoSuchElementException.class);

        WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                WebElement ele = null;
                boolean loop = true;
                int loopCounter = 0;
                while (loop) {
                    try {
                        ele = wait.until(ExpectedConditions.visibilityOf(elementToCheck));
                        System.out.println("DOM OK");
                        loop = false;
                    } catch (Exception e) {
                        loopCounter++;
                        if (loopCounter > 1) {
                            loop = false;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("DOM still busy");
                    }
                }
                return ele;
                *//*return wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@class='document-statetracker' and @data-state-busy-status='none']")));*//*
            }
        });
    }*/

    public void waitAndClickElement(WebElement element) {
//        waitForPageComponentLoadFluentWait(element);
        String elementUsed = element.toString();
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//                waitForPageComponentLoadFluentWait(element);
                System.out.println("Successfully clicked on the WebElement: " + "<" + elementUsed + ">");
                clicked = true;
            }catch (Exception e) {
                System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }

}

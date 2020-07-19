package libs;

import PageObjects.SamplePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverFactory extends CommonMethods  {
    final String configFilePath = "configuration/config.properties";
    protected static WebDriver _driver;
    protected static SamplePage _makeClaim;
    private DesiredCapabilities _capabilities;
    public Scenarios scenarios;

   /* protected WebDriver getWebDriver(String testName, String scenarioName) throws MalformedURLException {
        new DesiredCapabilities();
        _capabilities = DesiredCapabilities.chrome();
        try {
            this._driver = new RemoteWebDriver(new URL(prop.getProperty("selenium_local_server")),_capabilities);
        } catch (UnreachableBrowserException exception) {
            System.out.println("WebDriver creation failed. Possibly Remote webdriver endpoint is not configured properly. Check <IP>:4444/wd/hub endpoint");
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        _driver.manage().window().maximize();
        initializePageObjects();
        return _driver;
    }*/

    protected WebDriver getWebDriver(String scenarioName){
        _capabilities = new DesiredCapabilities();

        try {
            String browser  = "";
            String hub = "";
            if (!(System.getProperty("BROWSER_NAME") == null)){
                browser = System.getProperty("BROWSER_NAME").trim();
                System.out.println(browser);
            } else{
                browser = "chrome";
                System.out.println("browser property not set");
            }

            if (!(System.getProperty("HUB_HOST") == null)){
                hub = System.getProperty("HUB_HOST").trim();
                System.out.println(hub);
            } else{
                hub = "localhost";
                System.out.println("hub server property not set");
            }
            _capabilities.setBrowserName(browser);
//            _capabilities.setVersion("75");
            String url = "http://" + hub + ":4444/wd/hub";
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
            options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            //options.addArguments("--headless"); // only if you are ACTUALLY running headless
            options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
            options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
            options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
            options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
            options.addArguments("--disable-gpu");
            _capabilities.setCapability(ChromeOptions.CAPABILITY,options);
            //this.driver = new RemoteWebDriver(new URL(getPropertyKeyValue("selenium_local_server")),capabilities);
            this._driver = new RemoteWebDriver(new URL(url),_capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        _driver.manage().window().maximize();
        initializePageObjects();
        return _driver;
    }

    /**
     * Initialize PageObjects here.
     * Any new Page object that gets created should be initialised here.
     */
    public void initializePageObjects() {
        if (_driver != null) {
            try {
                _makeClaim = PageFactory.initElements(_driver, SamplePage.class);
            } catch (Exception e) {
                Assert.assertTrue(false, "Page object initialization failed");
            }

        }
    }
}
package libs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonMethods {
    final String configFilePath = "config/config.properties";
    protected Properties prop = null;

    public CommonMethods() {
        initPropertyFiles();
    }

    public void initPropertyFiles() {
        try (InputStream input = new FileInputStream(configFilePath)) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            functionThrowAssertMessage("Property file loading failed. Please check configuration directory");
        }
    }

    public void functionThrowAssertMessage(String message) {
        Assert.assertTrue(false, message);
    }
}

package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        driver = DriverFactory.initDriver(browser);
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
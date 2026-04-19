package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {

        String hubHost = System.getenv("HUB_HOST");

        try {
            // ===============================
            // SELENIUM GRID (DOCKER/JENKINS)
            // ===============================
            if (hubHost != null && !hubHost.isEmpty()) {

                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setBrowserName(browser);

                driver.set(new RemoteWebDriver(
                        new URL("http://" + hubHost + ":4444/wd/hub"), cap));

            } else {

                // ===============================
                // LOCAL EXECUTION
                // ===============================
                if (browser.equalsIgnoreCase("chrome")) {

                	ChromeOptions options = new ChromeOptions();

                	// Fresh profile (VERY IMPORTANT)
                	options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
                	options.addArguments("--incognito");

                	// Disable ALL password manager features
                	Map<String, Object> prefs = new HashMap<>();
                	prefs.put("credentials_enable_service", false);
                	prefs.put("profile.password_manager_enabled", false);

                	// Disable password leak detection (THIS FIXES YOUR ISSUE)
                	prefs.put("profile.password_manager_leak_detection", false);

                	// Disable notifications
                	prefs.put("profile.default_content_setting_values.notifications", 2);
                	prefs.put("profile.default_content_setting_values.geolocation", 2);

                	options.setExperimentalOption("prefs", prefs);

                	// Extra safety flags
                	options.addArguments("--disable-notifications");
                	options.addArguments("--disable-save-password-bubble");
                	options.addArguments("--disable-infobars");
                	options.addArguments("--disable-extensions");

                    driver.set(new ChromeDriver(options));

                } else if (browser.equalsIgnoreCase("firefox")) {

                    FirefoxOptions options = new FirefoxOptions();

                    if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                        options.addArguments("--headless");
                    }

                    driver.set(new FirefoxDriver(options));

                } else {
                    throw new RuntimeException("Browser not supported: " + browser);
                }
            }

            // ===============================
            // COMMON SETTINGS
            // ===============================
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Driver initialization failed");
        }

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
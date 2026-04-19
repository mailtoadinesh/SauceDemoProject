package utils;

import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static String captureScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + name + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
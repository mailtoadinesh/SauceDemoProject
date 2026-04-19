package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void startTest(String name) {
        test.set(ExtentManager.getInstance().createTest(name));
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
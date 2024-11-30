package utils;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReports() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}

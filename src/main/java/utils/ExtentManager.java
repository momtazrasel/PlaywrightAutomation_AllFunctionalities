package utils;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/ExtentReport.html");
        sparkReporter.config().setReportName("Playwright Automation Report");
        sparkReporter.config().setDocumentTitle("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }
}

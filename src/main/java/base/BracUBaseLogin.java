package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;

public class BracUBaseLogin {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeAll
    static void setUpAll() {
        // Initialize Playwright
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Initialize ExtentReports with SparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = context.newPage();

        // Log test setup in ExtentReports
        test = extent.createTest("Setup").info("Browser and page initialized");

        // Perform login
        test.info("Logging in...");
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://bracuerp-qa.apsissolutions.com/signin");
        loginPage.login("admin", "123456");
        test.pass("Login successful");
    }

    @AfterEach
    void tearDown() {
        context.close();
        test.info("Browser context closed");
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
        extent.flush();
    }
}

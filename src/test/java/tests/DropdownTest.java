package tests;

import PlaywrightSessions.TestData;
import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.DropdownPage;
import utils.ExtentManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropdownTest extends BaseTest {

    private ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentManager.initReports();
    }

    @Test
    void testDropdownOptions() {
//        DropdownPage dropdownPage = new DropdownPage(page);
//
//        List<String> actualOptions = dropdownPage.getAllDropdownOptions();
//        List<String> expectedOptions = TestData.getExpectedOptions();
//
//        assertTrue(actualOptions.containsAll(expectedOptions), "All expected dropdown values should be present.");

        test.info("Dropdown Test");

        DropdownPage dropdownPage = new DropdownPage(page);

        // Get expected options
        List<String> expectedOptions = TestData.getExpectedOptions();

        // Assert dropdown options
        dropdownPage.assertDropdownOptions(expectedOptions);
        dropdownPage.assertDropdownOptions(expectedOptions);
    }

    @Test
    void testAnotherFunctionality() {
        // Perform other test steps here
        System.out.println("Another test case running after login..."); 
    }

    @AfterClass
    public void tearDown() {
        ExtentManager.flushReports();
    }
}

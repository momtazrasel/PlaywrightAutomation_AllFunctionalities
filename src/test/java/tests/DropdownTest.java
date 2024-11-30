package tests;

import PlaywrightSessions.TestData;
import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;

import java.util.List;

public class DropdownTest extends BaseTest {

    @Test
    void testDropdownOptions() {
        // Create a test node in ExtentReports
        ExtentTest dropdownTest = extent.createTest("Verify Dropdown Options", "This test validates dropdown options.");
        try {
            dropdownTest.info("Navigating to the dropdown page.");

            // Pass both 'page' and 'dropdownTest' to the DropdownPage constructor
            DropdownPage dropdownPage = new DropdownPage(page, dropdownTest);

            // Get expected options
            List<String> expectedOptions = TestData.getExpectedOptions();
            dropdownTest.info("Expected options fetched: " + expectedOptions);

            // Validate dropdown options
            dropdownPage.assertDropdownOptions(expectedOptions);

            dropdownTest.pass("Dropdown options validated successfully.");
        } catch (Exception e) {
            dropdownTest.fail("Dropdown test failed: " + e.getMessage()).fail(e);
            throw e;
        }
    }

    @Test
    void testAnotherFunctionality() {
        ExtentTest test = extent.createTest("Verify Another Functionality", "This test validates additional functionality.");
        try {
            test.info("Starting another functionality test.");
            System.out.println("Another test case running after login...");
            test.pass("Another functionality verified successfully.");
        } catch (Exception e) {
            test.fail("Another functionality test failed: " + e.getMessage()).fail(e);
            throw e;
        }
    }
}

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

    @Test
    void testDropdownOptions() {
        // Create a parent test for this test case
        ExtentTest parent = extent.createTest("Verify Dropdown Options", "This test validates dropdown options.");

        // Create a child test for dropdown functionality
        ExtentTest dropdownTest = parent.createNode("Dropdown Validation");

        try {
            dropdownTest.info("Navigating to the dropdown page.");
            DropdownPage dropdownPage = new DropdownPage(page, dropdownTest);

            // Get expected options
            List<String> expectedOptions = TestData.getExpectedOptions();
            dropdownTest.info("Expected options fetched: " + expectedOptions);

            // Validate dropdown options
            dropdownPage.assertDropdownOptions(expectedOptions);

            dropdownTest.pass("Dropdown options validated successfully.");
        } catch (Exception e) {
            dropdownTest.fail("Dropdown test failed: " + e.getMessage());
            throw e; // Rethrow exception for proper test failure handling
        }
    }

    @Test
    void testAnotherFunctionality() {
        // Create a parent test for another functionality
        ExtentTest parent = extent.createTest("Verify Another Functionality", "This test validates additional functionality.");

        // Log test details
        parent.info("Starting another functionality test.");
        try {
            // Perform other test steps
            System.out.println("Another test case running after login...");
            parent.pass("Another functionality verified successfully.");
        } catch (Exception e) {
            parent.fail("Another functionality test failed: " + e.getMessage());
            throw e; // Ensure the test fails in case of exceptions
        }
    }
    }

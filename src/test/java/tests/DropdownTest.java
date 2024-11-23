package tests;

import PlaywrightSessions.TestData;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropdownTest extends BaseTest {

    @Test
    void testDropdownOptions() {
        DropdownPage dropdownPage = new DropdownPage(page);

        // Test dropdown options after login
        List<String> actualOptions = dropdownPage.getAllDropdownOptions();
        List<String> expectedOptions = TestData.getExpectedOptions();

        assertTrue(actualOptions.containsAll(expectedOptions), "All expected dropdown values should be present.");
    }

    @Test
    void testAnotherFunctionality() {
        // Perform other test steps here
        System.out.println("Another test case running after login...");
    }
}

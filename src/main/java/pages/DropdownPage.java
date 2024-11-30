package pages;

import base.BasePage;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class DropdownPage extends BasePage {
    private final String dropdownSelector = "//div[@class = 'ant-select-item-option-content']";
    private final String activeOptionSelector = "//div[@class = 'ant-select-item ant-select-item-option ant-select-item-option-active']";
    private final String siteSelectorButton = "(//a[@href='/site'])[1]";
    private final String siteDropdown = "(//input[@class  = 'ant-select-selection-search-input'])[1]";

    private final ExtentTest test; // ExtentTest for logging

    public DropdownPage(Page page, ExtentTest test) {
        super(page);
        this.test = test;
    }

    public List<String> getAllDropdownOptions() {
        test.info("Opening dropdown menu...");
        page.click(siteSelectorButton);
        page.click(siteDropdown);
        List<String> currentOptions = new ArrayList<>();
        String firstOption = null;

        test.info("Fetching dropdown options...");
        for (int i = 0; ; i++) {
            try {
                // Fetch the currently active dropdown value
                String currentOption = page.locator(activeOptionSelector).textContent();

                if (firstOption == null) {
                    firstOption = currentOption; // Store the first option
                } else if (currentOption.equals(firstOption)) {
                    break; // Exit loop if we cycle back to the first option
                }

                if (!currentOptions.contains(currentOption)) {
                    currentOptions.add(currentOption);
                    test.info("Found option: " + currentOption);
                }

                // Move to the next option
                page.keyboard().press("ArrowDown");
                page.locator(activeOptionSelector).waitFor();
            } catch (Exception e) {
                test.warning("Error while fetching dropdown option: " + e.getMessage());
                break;
            }
        }

        test.info("Total dropdown options fetched: " + currentOptions.size());
        return currentOptions;
    }

    public void assertDropdownOptions(List<String> expectedOptions) {
        test.info("Starting dropdown options validation...");
        List<String> actualOptions = getAllDropdownOptions();

        // Log all dropdown options
        test.info("Actual dropdown options: " + actualOptions);

        // Assert and report missing options
        if (!actualOptions.containsAll(expectedOptions)) {
            List<String> missingOptions = new ArrayList<>(expectedOptions);
            missingOptions.removeAll(actualOptions);
            test.fail("Some expected dropdown values are missing: " + missingOptions);
            throw new AssertionError("Missing dropdown options: " + missingOptions);
        }

        test.pass("Dropdown options validation passed successfully!");
    }
}

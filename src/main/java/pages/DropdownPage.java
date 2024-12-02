package pages;

import base.BasePage;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Paths;
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

    private void attachScreenshot(String stepName) {
        try {
            // Create the screenshots directory if it doesn't exist
            String screenshotDir = "reports/screenshots/";
            Files.createDirectories(Paths.get(screenshotDir));

            // Define screenshot file name and path
            String screenshotPath = screenshotDir + stepName.replaceAll("\\s+", "_") + ".png";

            // Capture screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));

            // Attach screenshot to Extent Report
            test.addScreenCaptureFromPath(screenshotPath, stepName);
        } catch (Exception e) {
            test.warning("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public List<String> getAllDropdownOptions() {
        test.info("Opening dropdown menu...");
        attachScreenshot("Opening Dropdown Menu");
        page.click(siteSelectorButton);
        page.click(siteDropdown);

        List<String> currentOptions = new ArrayList<>();
        String firstOption = null;

        test.info("Fetching dropdown options...");
        for (int i = 0; ; i++) {
            try {
                String currentOption = page.locator(activeOptionSelector).textContent();
                if (firstOption == null) {
                    firstOption = currentOption;
                } else if (currentOption.equals(firstOption)) {
                    break;
                }

                if (!currentOptions.contains(currentOption)) {
                    currentOptions.add(currentOption);
                    test.info("Found option: " + currentOption);
                    attachScreenshot("Found Option " + currentOption);
                }

                page.keyboard().press("ArrowDown");
                page.locator(activeOptionSelector).waitFor();
            } catch (Exception e) {
                test.warning("Error while fetching dropdown option: " + e.getMessage());
                break;
            }
        }

        test.info("Total dropdown options fetched: " + currentOptions.size());
        attachScreenshot("Total Dropdown Options");
        return currentOptions;
    }

    public void assertDropdownOptions(List<String> expectedOptions) {
        test.info("Starting dropdown options validation...");
        attachScreenshot("Before Dropdown Validation");

        List<String> actualOptions = getAllDropdownOptions();
        test.info("Actual dropdown options: " + actualOptions);

        if (!actualOptions.containsAll(expectedOptions)) {
            List<String> missingOptions = new ArrayList<>(expectedOptions);
            missingOptions.removeAll(actualOptions);
            test.fail("Some expected dropdown values are missing: " + missingOptions);
            attachScreenshot("Validation Failed");
            throw new AssertionError("Missing dropdown options: " + missingOptions);
        }

        test.pass("Dropdown options validation passed successfully!");
        attachScreenshot("Validation Passed");
    }
}

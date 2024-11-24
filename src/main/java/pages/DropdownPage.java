package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class DropdownPage extends BasePage {
    private final String dropdownSelector = "//div[@class = 'ant-select-item-option-content']";
    private final String activeOptionSelector = "//div[@class = 'ant-select-item ant-select-item-option ant-select-item-option-active']";
    private final String siteSelectorButton = "(//a[@href='/site'])[1]";
    private final String siteDropdown = "(//input[@class  = 'ant-select-selection-search-input'])[1]";


    public DropdownPage(Page page) {
        super(page);
    }

    public List<String> getAllDropdownOptions() {


        page.click(siteSelectorButton);
        page.click(siteDropdown);
        List<String> currentOptions = new ArrayList<>();
        String firstOption = null;

        for (int i = 0; ; i++) {
            // Fetch the currently active dropdown value
            String currentOption = page.locator(activeOptionSelector).textContent();

            if (firstOption == null) {
                firstOption = currentOption; // Store the first option
            } else if (currentOption.equals(firstOption)) {
                break; // Exit loop if we cycle back to the first option
            }

            if (!currentOptions.contains(currentOption)) {
                currentOptions.add(currentOption);

                System.out.println(currentOption);
            }

            // Move to the next option
            page.keyboard().press("ArrowDown");
            page.locator(activeOptionSelector).waitFor();
        }

        System.out.println("Total dropdown options: " + currentOptions.size());
        return currentOptions;
    }


    public void assertDropdownOptions(List<String> expectedOptions) {
        List<String> actualOptions = getAllDropdownOptions();

        // Print all dropdown options
        System.out.println("Dropdown Options:");
        for (String option : actualOptions) {
            System.out.println(option);
        }

        // Assert and report missing options
        if (!actualOptions.containsAll(expectedOptions)) {
            List<String> missingOptions = new ArrayList<>(expectedOptions);
            System.out.println(missingOptions);
            missingOptions.removeAll(actualOptions);
            throw new AssertionError("Some expected dropdown values are missing: " + missingOptions);
        }
    }
}

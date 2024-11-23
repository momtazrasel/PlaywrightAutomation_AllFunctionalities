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
        List<String> options = new ArrayList<>();
        String firstOption = null;

        for (int i = 0; ; i++) {
            String currentOption = page.locator(activeOptionSelector).textContent();

            if (firstOption == null) {
                firstOption = currentOption;
            } else if (currentOption.equals(firstOption)) {
                break;
            }

            if (!options.contains(currentOption)) {
                options.add(currentOption);
            }

            page.keyboard().press("ArrowDown");
        }

        return options;
    }
}

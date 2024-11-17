package PlaywrightSessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DropdownText {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://stage.portal.denowatts.com/signin");
            page.waitForTimeout(5000);
            page.getByLabel("Email").click();
            page.getByLabel("Email").fill("apps@niftyitsolution.com");
            page.getByLabel("Password").click();
            page.getByLabel("Password").fill("f@]+yN!ogbCFE\"pi33");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("logo").setExact(true)).click(new Locator.ClickOptions()
                    .setButton(MouseButton.RIGHT));
            assertThat(page.getByRole(AriaRole.BANNER)).containsText("Main Menu|");
            page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("logo").setExact(true)).click();
            assertThat(page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("logo").setExact(true))).isVisible();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Site").setExact(true)).click();
            page.getByRole(AriaRole.COMBOBOX).click();
            page.waitForTimeout(5000);

            page.waitForSelector("//div[@class = 'ant-select-item-option-content']");
            Set<String> dropdownValues = new HashSet<>();
            while (true) {
                String currentOption = page.locator("//div[@class = 'ant-select-item ant-select-item-option ant-select-item-option-active']").first().textContent();
                System.out.println(currentOption);
                page.keyboard().press("ArrowDown");
                page.waitForTimeout(2000);
                System.out.println(currentOption);


                String highlightedOption = page.locator("(//div[contains(text(),'00 Calibration Gateway')])[1]").textContent();
                if (highlightedOption != null && dropdownValues.add(highlightedOption)) {
                    System.out.println(highlightedOption);
                }
                for(int i=0; i<=100; i++){
                    page.keyboard().press("ArrowDown");
                    System.out.println(i);
                }

                page.waitForTimeout(200);
                String lastOption = page.locator("(//div[@class='ant-select-item-option-content'])[last()]").textContent();
                if (dropdownValues.contains(lastOption)) {
                    break; // Exit the loop once the last option is reached
                }

                // Get the current number of elements
                int currentOptionsCount = page.locator("//div[@class = 'ant-select-item-option-content']").count();
                System.out.println("Number of elements: "+ currentOptionsCount);

                // Scroll to the last visible option
                page.locator("div.dropdown-option:last-child").scrollIntoViewIfNeeded();

                // Wait for a short period to allow new options to load
                page.waitForTimeout(500);

                // Check if more options have been loaded
                int updatedOptionsCount = page.locator("div.dropdown-option").count();
                if (updatedOptionsCount == currentOptionsCount) {
                    break; // Exit the loop if no new options are loaded
                }
            }

            // Get all dropdown values
//            List<String> dropdownValues = page.locator("div.dropdown-option").allTextContents();

            // Print all values
            for (String value : dropdownValues) {
                System.out.println(value);
            }

            // Close the browser
            browser.close();
        }
    }
}

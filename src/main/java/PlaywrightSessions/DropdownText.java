package PlaywrightSessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;

import java.util.ArrayList;
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
//            Set<String> dropdownValues = new HashSet<>();
//            String [] currentOption;
            List<String> expectedOptions = TestData.getExpectedOptions();
            List<String> currentOptions = new ArrayList<>();
            String firstOption = null;
//            while (true) {
//                for (int i = 0; i <= 10; i++) {
//                    String currentOption = page.locator("//div[@class = 'ant-select-item ant-select-item-option ant-select-item-option-active']").textContent();
//                    currentOptions.add(currentOption);
//                    System.out.println(currentOption);
//                    page.waitForTimeout(2000);
//                    page.keyboard().press("ArrowDown");
//                    page.waitForTimeout(2000);
////                }
//            }

            for (int i = 0;; i++) { // Infinite for-loop with dynamic break condition
                // Fetch the currently active dropdown value
                String currentOption = page.locator("//div[@class = 'ant-select-item ant-select-item-option ant-select-item-option-active']").textContent();

                // Store the first option and print it
                if (firstOption == null) {
                    firstOption = currentOption; // Store the first option
                } else if (currentOption.equals(firstOption)) {
                    // Break the loop if we cycle back to the first option
                    break;
                }

                // Add the current option to the list if not already present
                if (!currentOptions.contains(currentOption)) {
                    currentOptions.add(currentOption);
//                    System.out.println(currentOption); // Print the option
                }

                // Scroll to the next option
                page.keyboard().press("ArrowDown");
                page.waitForTimeout(100); // Wait for the dropdown UI to update
            }

            System.out.println("Total dropdown options: " + currentOptions.size());
            System.out.println("All dropdown options: " + currentOptions);

            if (currentOptions.containsAll(expectedOptions)) {
                System.out.println("All expected dropdown values are available.");
            } else {
                // Identify missing options
                List<String> missingOptions = new ArrayList<>(expectedOptions);
                missingOptions.removeAll(currentOptions);
                System.out.println("Some expected dropdown values are missing: " + missingOptions);
            }

        }
        }
    }


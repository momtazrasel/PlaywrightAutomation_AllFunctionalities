package PlaywrightSessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DropdownText {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://stage.portal.denowatts.com/signin");
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
        }
    }
}
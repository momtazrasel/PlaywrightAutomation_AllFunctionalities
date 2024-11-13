package PlaywrightSessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class TextElement {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://demoqa.com/");
            page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Elements$"))).nth(1).click();
            page.locator("li").filter(new Locator.FilterOptions().setHasText("Text Box")).click();
            page.getByPlaceholder("Full Name").click();
            page.getByPlaceholder("Full Name").fill("momtaz");
            page.getByPlaceholder("name@example.com").click();
            page.getByPlaceholder("name@example.com").fill("momtaz@gmail.com");
            page.getByPlaceholder("Current Address").click();
            page.getByPlaceholder("Current Address").fill("Aftabnagar, Dhaka, Bangladesh");
            page.locator("#permanentAddress").click();
            page.locator("#permanentAddress").fill("Do");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        }
    }
}

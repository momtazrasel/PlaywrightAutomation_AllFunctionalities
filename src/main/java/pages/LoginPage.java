package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String emailInput = "//input[@id='email']";
    private final String passwordInput = "//input[@id='password']";
    private final String loginButton = "//button[normalize-space()='Log in']";

    public LoginPage(Page page){
        super(page);
    }

    public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);
        page.waitForTimeout(5000);
    }

}

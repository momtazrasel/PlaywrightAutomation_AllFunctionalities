package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String emailInput = "input[label='Email']";
    private final String passwordInput = "input[label='Password']";
    private final String loginButton = "button:has-text('Login')";

    public LoginPage(Page page){
        super(page);
    }

    public void login(String email, String password) {
        page.fill(emailInput, email);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

}

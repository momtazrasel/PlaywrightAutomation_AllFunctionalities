package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    void testLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://stage.portal.denowatts.com/signin");
        loginPage.login("apps@niftyitsolution.com", "f@]+yN!ogbCFE\"pi33");
    }
}

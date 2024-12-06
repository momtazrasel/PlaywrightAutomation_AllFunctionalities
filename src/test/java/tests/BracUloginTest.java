package tests;

import base.BracUBaseLogin;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class BracUloginTest extends BracUBaseLogin {
    @Test
    void testLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://stage.portal.denowatts.com/signin");
        loginPage.login("apps@niftyitsolution.com", "f@]+yN!ogbCFE\"pi33");
}
}

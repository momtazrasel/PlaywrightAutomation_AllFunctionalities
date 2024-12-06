package tests;

import base.BracUBaseLogin;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class BracUloginTest extends BracUBaseLogin {
    @Test
    void testLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateTo("https://bracuerp-qa.apsissolutions.com/signin");
        loginPage.login("admin", "123456");
}
}

package tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest2 extends BaseTest {

    @Test
    public void test() {

        test.info("Navigating to the Login Page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        LoginPage loginpage = new LoginPage(page);
        HomePage homepage = new HomePage(page);

        test.info("Adding Username");
        loginpage.addUsername("Admin");

        test.info("Enter Password");
        loginpage.addPassword("admin123");

        test.info("Clicking Login Button");
        loginpage.clickLoginButton();

        test.info("Checking Homepage");
        homepage.ClickTimeLink();

        test.info("All test completed");
    }

    @Test
    public void failedTest() {

        test.info("Navigating to Login Page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        LoginPage loginPage = new LoginPage(page);

        loginPage.addUsername("Admin");
        loginPage.addPassword("WrongPassword");
        loginPage.clickLoginButton();

        Assert.fail("Intentional Failure");
    }

    @Test
    public void skippedTest() {

        throw new SkipException("Intentional Skip");
    }
}
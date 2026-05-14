package com.victoria.herokuapp;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify login with valid credentials and display of SecurePage")
    public void testValidLogin() {
        SecurePage securePage = performValidLogin();
        Assert.assertTrue(securePage.isSecurePageDisplayed(), "SecurePage nu este afisata");
        // Intentional fail for screenshot
        Assert.fail("Intentional failure to verify screenshot");
    }

    @Test
    @Description("Check the success message after valid login")
    public void testSecurePageMessage() {
        SecurePage securePage = performValidLogin();
        Assert.assertTrue(securePage.getSuccessMessage().contains("You logged into a secure area!"));
    }

    @Test
    @Description("Check the Secure Page URL after a valid login")
    public void testSecurePageURL() {
        SecurePage securePage = performValidLogin();
        Assert.assertTrue(securePage.isURLSecure(), "Userul nu este pe SecurePage");
    }

    @Test
    @Description("Check the visible elements on SecurePage")
    public void testSecurePageElements() {
        SecurePage securePage = performValidLogin();
        Assert.assertTrue(securePage.getSuccessMessage().contains("You logged into a secure area!"));
        Assert.assertTrue(securePage.isLogoutButtonDisplayed(), "Butonul Logout nu este vizibil");
    }

    @Test
    @Description("Check the Logout functionality")
    public void testLogoutFunctionality() {
        SecurePage securePage = performValidLogin();
        securePage.logoutClick();
        Assert.assertTrue(loginPage.getLogoutMessage().contains("You logged out"));
    }

    @Test
    @Description("Verify access to SecurePage without login")
    public void testSecurePageAccessWithoutLogin() {
        goToSecurePageDirectly();
        Assert.assertTrue(loginPage.getErrorMessage().contains("You must login to view the secure area!"));
    }
    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = com.victoria.herokuapp.data.LoginDataProvider.class
    )
    @Description("Check invalid login using DataProvider.")
    public void testInvalidLoginDataProvider(String username, String password, String expectedMessage) {
        loginPage.loginInvalid(username, password);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedMessage));

    }
    @Step("Perform valid login with correct username and password")
    private SecurePage performValidLogin() {
        return loginPage.loginValid("tomsmith", "SuperSecretPassword!");
    }

    @Step("Directly access the SecurePage URL without logging in")
    private void goToSecurePageDirectly() {
        driver.get("https://the-internet.herokuapp.com/secure");
    }
}
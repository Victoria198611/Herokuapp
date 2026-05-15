package com.victoria.herokuapp;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector("div.flash.error");
    private By headerLogin = By.xpath("//h2[text()='Login Page']");
    private By logoutMessage = By.cssSelector("div.flash.success");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Login Page")
    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    @Step("Enter password: {password}")
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    @Step("Click Login button")
    public void clickLogin() {
        click(submitButton);
    }

    @Step("Perform valid login with username: {username} and password: {password}")
    public SecurePage loginValid(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        wait.until(ExpectedConditions.urlContains("/secure"));
        return new SecurePage(driver);
    }

    @Step("Perform invalid login with username: {username} and password: {password}")
    public LoginPage loginInvalid(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return this;
    }

    // Check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            return waitForVisible(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Get error message text
    public String getErrorMessage() {
        try {
            return waitForVisible(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Check if Login Page header is displayed
    public boolean isLoginPageDisplayed() {
        try {
            return waitForVisible(headerLogin).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Get logout message
    public String getLogoutMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        return flash.getText().trim();
    }


// Get logout Successful
public boolean isLogoutSuccessful(){
    return getLogoutMessage().contains("You logged out");
}
}
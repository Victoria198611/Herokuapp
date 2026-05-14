package com.victoria.herokuapp;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {

    // Locators
    private By successMessage = By.id("flash");
    private By logoutButton = By.cssSelector("a[href='/logout']");

    // Constructor
    public SecurePage(WebDriver driver) {
        super(driver);
    }

    // Check if success message is displayed
    public boolean isSuccessMessageDisplayed() {
        try {
            return waitForVisibleSafe(successMessage) != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Get success message text")
    public String getSuccessMessage() {
        try {
            var element = waitForVisibleSafe(successMessage);
            return element != null ? element.getText() : "";
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Click Logout button")
    public void logoutClick() {
        click(logoutButton);
    }

    @Step("Verify that current URL is SecurePage")
    public boolean isURLSecure() {
        return driver.getCurrentUrl().contains("/secure");
    }

    // Check if SecurePage is displayed
    public boolean isSecurePageDisplayed() {
        return isSuccessMessageDisplayed() && isURLSecure();
    }

    // Check if logout button is displayed
    public boolean isLogoutButtonDisplayed() {
        return !driver.findElements(logoutButton).isEmpty();
    }
}
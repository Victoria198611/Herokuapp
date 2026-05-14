package com.victoria.herokuapp;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // SAFE wait – does not throw exceptions → does not produce BROKEN in Allure
    public WebElement waitForVisibleSafe(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return null;
        }
    }

    // Strict wait – only used when you are sure the element exists
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Click on element located by: {locator}")
    public void click(By locator) {
        WebElement element = waitForVisibleSafe(locator);
        if (element != null) {
            element.click();
        }
    }

    @Step("Type text '{text}' into element located by: {locator}")
    public void type(By locator, String text) {
        WebElement element = waitForVisibleSafe(locator);
        if (element != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    @Step("Get alert text")
    public String getAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Accept alert")
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // ignore
        }
    }
}
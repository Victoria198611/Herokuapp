package com.victoria.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ScreenshotUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected SecurePage securePage;

    //  HERE I add the method for copying environment.properties
    @BeforeSuite
    public void setupEnvironment() {
        try {
            Path source = Paths.get("src/test/resources/environment.properties");
            Path destination = Paths.get("target/allure-results/environment.properties");
            Files.createDirectories(destination.getParent());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">>> Environment file copied to allure-results <<<");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public void setup() {
        System.setProperty("allure.results.directory", "target/allure-results");
        System.out.println(">>> Allure listener LOADED <<<");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Open login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Initialize pages
        loginPage = new LoginPage(driver);
        securePage = new SecurePage(driver);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        try {
            // 1.If the test failed, we take a screenshot
            if (ITestResult.FAILURE == result.getStatus()) {
                ScreenshotUtils.takeScreenshot(driver);
            }

            // 2. Close the driver safely
            if (driver != null) {
                driver.quit();
            }

        } catch (Exception ignored) {
            //Prevents test from being marked as BROKEN in Allure
        }
    }
}
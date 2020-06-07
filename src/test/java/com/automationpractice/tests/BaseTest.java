package com.automationpractice.tests;

import com.automationpractice.utilities.PropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public WebDriver driver;

    @BeforeSuite
    protected void beforeSuite() {
    }

    @BeforeTest
    void driverInit() {
        String webDriverPath = PropertyHelper.getInstance().getChromeWebDriverPath();
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        driver = new ChromeDriver();
    }

    @AfterSuite
    void afterSuit() {
        driver.close();
    }

}

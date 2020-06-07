package com.automationpractice.pages;

import com.automationpractice.utilities.PropertyHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    private static final int TIMEOUT = PropertyHelper.getInstance().getTimeout();
    private static final int SLEEP_TIME = 5000;
    public WebDriver driver;
    public String siteUrl = PropertyHelper.getInstance().getSiteUrl();
    public int timeout = PropertyHelper.getInstance().getTimeout();
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    public void openSite() {
        driver.get(siteUrl);
    }

    public boolean waitForJSToLoad() {

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            Thread.sleep(SLEEP_TIME);
            wait.until(jsLoad);
        } catch (Throwable t) {
            Assert.fail("Page load timeout");
        }

        return wait.until(jsLoad);
    }
}

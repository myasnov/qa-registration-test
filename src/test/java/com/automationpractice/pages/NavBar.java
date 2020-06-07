package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class NavBar extends BasePage {

    @FindBy(css = "nav .login")
    private WebElement signInLink;

    public NavBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void signIn() {
        signInLink.click();
    }

    public void validateUserName(String expectedUserName) {

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav .account span")));
        WebElement userName = driver.findElement(By.cssSelector("nav .account span"));
        Assert.assertEquals(userName.getText(), expectedUserName, "User name doesn't match");
    }

}

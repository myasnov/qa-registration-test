package com.automationpractice.tests;

import com.automationpractice.models.User;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MainPage;
import com.automationpractice.pages.RegistrationPage;
import com.automationpractice.utilities.EmailGenerator;
import com.automationpractice.utilities.UserDataUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class InvalidUserDataTestHandling extends BaseTest {

    private static final String validEmail = EmailGenerator.generateRandomEmail();

    @Test(dataProvider = "userData")
    public void openFormTest(User user) {
        MainPage homePage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        homePage.openSite();
        homePage.signInClick();
        loginPage.createAnAccountEmail(validEmail);
        registrationPage.waitForJSToLoad();
        registrationPage.verifyPageElementsDisplayed();
        registrationPage.fillUserData(user);
        registrationPage.sumbitForm();
        registrationPage.errorVerify();
    }

    @DataProvider(name = "userData")
    public Object[][] testDataProvider() throws IOException {
        UserDataUtil userDataUtil = new UserDataUtil();
        User user = userDataUtil.getInvalidUserData();
        return new Object[][]{{user}};
    }
}
package com.automationpractice.tests;

import com.automationpractice.models.User;
import com.automationpractice.pages.LoginPage;
import com.automationpractice.pages.MainPage;
import com.automationpractice.pages.RegistrationPage;
import com.automationpractice.utilities.EmailGenerator;
import com.automationpractice.utilities.UserDataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class InvalidUserDataTestHandling extends BaseTest {

    private static final Logger logger = LogManager.getLogger(UserDataUtil.class);

    private static final String validEmail = EmailGenerator.generateRandomEmail();

    @Test(dataProvider = "userData")
    public void openFormTest(User user) {
        MainPage homePage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        logger.info("1. Open home page");
        homePage.openSite();
        logger.info("2. Click sign in button");
        homePage.signInClick();
        logger.info("3. Waiting for load email field. Enter email");
        loginPage.createAnAccountEmail(validEmail);
        logger.info("4. Waiting for loading registration form");
        registrationPage.waitForPageLoad();
        logger.info("5. Waiting for reg form elements");
        registrationPage.verifyPageElementsDisplayed();
        logger.info("6. Fill user data");
        registrationPage.fillUserData(user);
        logger.info("7. Submit form");
        registrationPage.sumbitForm();
        logger.info("8. Waiting for errors");
        registrationPage.errorVerify();
    }

    @DataProvider(name = "userData")
    public Object[][] testDataProvider() throws IOException {
        UserDataUtil userDataUtil = new UserDataUtil();
        logger.info("Getting test data");
        User user = userDataUtil.getInvalidUserData();
        return new Object[][]{{user}};
    }
}

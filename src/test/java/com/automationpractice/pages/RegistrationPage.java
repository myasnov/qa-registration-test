package com.automationpractice.pages;

import com.automationpractice.models.Address;
import com.automationpractice.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistrationPage extends BasePage {

    private static final ArrayList<String> errors = new ArrayList<String>() {
        {

            add("lastname is invalid.");
            add("firstname is invalid.");
            add("passwd is invalid.");
            add("address1 is invalid.");
            add("address2 is invalid.");
            add("phone is invalid.");
            add("phone_mobile is invalid.");
            add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");

        }
    };
    @FindBy(className = "alert-danger")
    private WebElement errorDanger;
    @FindBy(id = "id_gender1")
    private WebElement radioGender1;
    @FindBy(id = "id_gender2")
    private WebElement radioGender2;
    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement lastName;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "passwd")
    private WebElement password;
    @FindBy(id = "days")
    private WebElement birthDay;
    @FindBy(id = "months")
    private WebElement birthMonth;
    @FindBy(id = "years")
    private WebElement birthYear;
    @FindBy(id = "newsletter")
    private WebElement newsSignUp;
    @FindBy(id = "optin")
    private WebElement offers;
    @FindBy(id = "firstname")
    private WebElement yourAddrFirstName;
    @FindBy(id = "lastname")
    private WebElement yourAddrLastName;
    @FindBy(id = "address1")
    private WebElement yourAddress1;
    @FindBy(id = "address2")
    private WebElement yourAddress2;
    @FindBy(id = "city")
    private WebElement yourAddrCity;
    @FindBy(id = "id_state")
    private WebElement yourAddrState;
    @FindBy(id = "postcode")
    private WebElement yourAddrPostcode;
    @FindBy(id = "other")
    private WebElement yourAddrOther;
    @FindBy(id = "phone")
    private WebElement yourAddrHomePhone;
    @FindBy(id = "phone_mobile")
    private WebElement yourAddrMobilePhone;
    @FindBy(id = "alias")
    private WebElement yourAddrAlias;
    @FindBy(id = "submitAccount")
    private WebElement submit;
    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static boolean equals(List<String> a, List<String> b) {
        return a.size() == b.size() && a.containsAll(b);
    }

    public void errorVerify() {
        wait.until(ExpectedConditions.visibilityOf(errorDanger));
        if (!errorDanger.isDisplayed()) Assert.fail("Alert Errors do not displayed");
        List<WebElement> errs = driver.findElements(By.cssSelector(".alert-danger li"));
        ArrayList<String> actualErrors = new ArrayList<String>();
        for (WebElement err : errs) {
            actualErrors.add(err.getText());
        }

        Assert.assertTrue(equals(errors, actualErrors), "All expected errors do not displayed");
    }

    public void verifyPageElementsDisplayed() {

        Assert.assertTrue(radioGender1.isEnabled(), "Radiobutton Mr is not displayed");
        Assert.assertTrue(radioGender2.isEnabled(), "Radiobutton Mrs is not displayed");
        Assert.assertTrue(firstName.isDisplayed(), "Input First Name is not displayed");
        Assert.assertTrue(lastName.isDisplayed(), "Input Last Name is not displayed");
        Assert.assertTrue(email.isDisplayed(), "Input Email is not displayed");
        Assert.assertTrue(password.isDisplayed(), "Input Password is not displayed");
        Assert.assertTrue(birthDay.isEnabled(), "Select Day of Birth is not displayed");
        Assert.assertTrue(birthMonth.isEnabled(), "Select Month of Birth is not displayed");
        Assert.assertTrue(birthYear.isEnabled(), "Select Year of Birth is not displayed");
        Assert.assertTrue(newsSignUp.isEnabled(), "Checkbox News Letter is not displayed");
        Assert.assertTrue(offers.isEnabled(), "Checkbox Receive special offers is not displayed");
        Assert.assertTrue(yourAddrFirstName.isDisplayed(), "Input First Name is not displayed (Address)");
        Assert.assertTrue(yourAddrLastName.isDisplayed(), "Input Last Name is not displayed (Address)");
        Assert.assertTrue(yourAddress1.isDisplayed(), "Input Address is not displayed (Address)");
        Assert.assertTrue(yourAddress2.isDisplayed(), "Input Address (Line 2) is not displayed (Address)");
        Assert.assertTrue(yourAddrCity.isDisplayed(), "Input City is not displayed (Address)");
        Assert.assertTrue(yourAddrPostcode.isDisplayed(), "Input Postcode is not displayed (Address)");
        Assert.assertTrue(yourAddrState.isEnabled(), "Select State is not displayed (Address)");
        Assert.assertTrue(yourAddrOther.isDisplayed(), "Textarea Additional Information is not displayed (Address)");
        Assert.assertTrue(yourAddrHomePhone.isDisplayed(), "Input Home Phone is not displayed (Address)");
        Assert.assertTrue(yourAddrMobilePhone.isDisplayed(), "Input Mobile Phone is not displayed (Address)");
        Assert.assertTrue(yourAddrAlias.isDisplayed(), "Input Assign an Address Alias is not displayed (Address)");
        Assert.assertTrue(submit.isDisplayed(), "Submit button is not displayed");

    }

    public void fillUserData(User user) {
        if (user.getGender() == user.getGender().FEMALE) {
            radioGender2.click();
        } else {
            radioGender1.click();
        }
        firstName.sendKeys(user.getFirstname());
        lastName.sendKeys(user.getLastname());
        password.sendKeys(user.getPassword());

        Calendar callendar = Calendar.getInstance();
        callendar.setTime(user.getDateOfBirth());

        (new Select(birthDay)).selectByValue(Integer.toString(callendar.get(Calendar.DAY_OF_MONTH)));
        (new Select(birthMonth)).selectByValue(Integer.toString(callendar.get(Calendar.MONTH) + 1));
        (new Select(birthYear)).selectByValue(Integer.toString(callendar.get(Calendar.YEAR)));

        newsSignUp.click();
        offers.click();

        yourAddrFirstName.sendKeys(user.getFirstname());
        yourAddrLastName.sendKeys(user.getLastname());
        Address address = user.getAddresses();
        yourAddress1.sendKeys(address.getAddress1());
        yourAddress2.sendKeys(address.getAddress2());
        yourAddrCity.sendKeys(address.getCity());
        (new Select(yourAddrState)).selectByVisibleText(address.getState());
        yourAddrPostcode.sendKeys(address.getPostcode());
        yourAddrOther.sendKeys(address.getAddinfo());
        yourAddrHomePhone.sendKeys(user.getHomephone());
        yourAddrMobilePhone.sendKeys(user.getMobilephone());
        yourAddrAlias.clear();
        yourAddrAlias.sendKeys(address.getAssignaddress());
    }

    public void sumbitForm() {
        submit.click();
    }

    public void verifyErrorsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorDanger));
    }

}
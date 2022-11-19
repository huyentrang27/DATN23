package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends Utility {
    /**
     * Element
     */
    @FindBy (xpath = "//a[@href='../']/descendant::span")
    private WebElement homeTab;
    @FindBy (xpath = "//a[@href='#']/descendant::span")
    private WebElement FAQTab;
    @FindBy (xpath = "//a[@href='/Page/Contact.cshtml']n")
    private WebElement contactTab;
    @FindBy (xpath = "//a[@href='TrainTimeListPage.cshtml']")
    private WebElement trainTimeTableTab;
    @FindBy (xpath = "//a[@href='/Page/TrainPriceListPage.cshtml']/descendant::span")
    private WebElement ticketPriceTab;
    @FindBy (xpath = "//a[@href='/Page/BookTicketPage.cshtml']")
    private WebElement bookTicketTab;
    @FindBy (xpath = "//a[@href='/Account/Register.cshtml']")
    private WebElement registerTab;
    @FindBy (xpath = "//a[@href='/Account/Login.cshtml']")
    private WebElement loginPageTab;
    @FindBy (xpath = "//a[@href='/Page/ManageTicket.cshtml']")
    private WebElement myTicketTab;
    @FindBy (xpath = "//a[@href='/Account/ChangePassword.cshtml']")
    private WebElement changePasswordTab;
    @FindBy (xpath = "//a[@href='/Account/Logout']")
    private WebElement logOutTab;

    /**
     * Methods
     */
    public void clickOnTimeTableTab () {
        WebDriverUtils.waitForControlBeClickable(trainTimeTableTab);
        trainTimeTableTab.click();
    }

    public void clickOnLoginTab () {
        WebDriverUtils.waitForControlBeClickable(loginPageTab);
        loginPageTab.click();
    }

    public void clickOnBookTicketTab () {
        WebDriverUtils.waitForControlBeClickable(bookTicketTab);
        bookTicketTab.click();
    }

    public void clickOnContactTab () {
        WebDriverUtils.waitForControlBeClickable(contactTab);
        contactTab.click();
    }

    public void clickOnRegisterTab () {
        WebDriverUtils.waitForControlBeClickable(registerTab);
        registerTab.click();
    }

    public void clickOnChangePasswordTab () {
        WebDriverUtils.waitForControlBeClickable(changePasswordTab);
        changePasswordTab.click();
    }

    public void clickOnMyTicketTab () {
        WebDriverUtils.waitForControlBeClickable(myTicketTab);
        myTicketTab.click();
    }

    public boolean doesLogOutTabDisplay () {
        return WebDriverUtils.doesControlExist(logOutTab);
    }
}

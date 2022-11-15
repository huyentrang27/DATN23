package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement header;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement txt_email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement txt_password;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement btn_login;
    @FindBy(xpath = "//p[@class='message error LoginForm']")
    private WebElement txt_errorMessage;

    /**
     * Methods
     */
    public void inputEmail(String email) {
        WebDriverUtils.waitForControl(txt_email);
        txt_email.sendKeys(email);
    }

    public void inputPassword(String password) {
        WebDriverUtils.waitForControl(txt_password);
        txt_password.sendKeys(password);
    }

    public void clickLoginButton() {
        WebDriverUtils.waitForControlBeClickable(btn_login);
        btn_login.click();
    }

    public void verifyErrorMessageAppears (ExtentTest logStep) {
        try{
            log4j.info("verifyErrorMessageAppears - Starts");
            TestReporter.logInfo(logStep, "Verify Error Message appears ...");

            Assertion.verifyActualAndExpected(logStep, true, WebDriverUtils.doesControlExist(txt_errorMessage));
            log4j.info("verifyErrorMessageAppears method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyErrorMessageAppears method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyErrorMessageAppears - ERROR", e);
        }
    }

    public void login (ExtentTest logStep, String email, String password) {
        try{
            log4j.info("login - Starts");
            TestReporter.logInfo(logStep, "Login to Safe Railway appears ...");

            inputEmail(email);
            inputPassword(password);
            clickLoginButton();

            log4j.info("login method - Ends");
        }catch (Exception e)
        {
            log4j.error("login method - ERROR: ", e);
            TestReporter.logException(logStep, "login - ERROR", e);
        }

    }
    public void verifyLoginPageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyLoginPageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Login page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, txt_email, "Email field");
            Assertion.checkControlExist(logTest, txt_password, "Password field");
            Assertion.checkControlExist(logTest, btn_login, "Register button");

            log4j.info("verifyLoginPageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyLoginPageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Login page - ERROR", e);
        }
    }
}

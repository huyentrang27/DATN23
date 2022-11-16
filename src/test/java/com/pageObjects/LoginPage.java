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
    private WebElement txt_Email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement txt_Password;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement btn_Login;
    @FindBy(xpath = "//div[@id='content']//a[@href='Register.cshtml']")
    private WebElement link_Register;
    @FindBy(xpath = "//div[@id='content']//a[@href='/Account/ForgotPassword.cshtml']")
    private WebElement link_ForgotPassword;
    @FindBy(xpath = "//p[@class='message error LoginForm']")
    private WebElement txt_LoginErrorMessage;
    @FindBy(xpath = "//label[@for='username'][@class='validation-error']")
    private WebElement txt_EmailErrorMessage;
    @FindBy(xpath = "//label[@for='password'][@class='validation-error']")
    private WebElement txt_PasswordErrorMessage;

    /**
     * Methods
     */
    public void inputEmail(String email) {
        WebDriverUtils.waitForControl(txt_Email);
        txt_Email.sendKeys(email);
    }

    public void inputPassword(String password) {
        WebDriverUtils.waitForControl(txt_Password);
        txt_Password.sendKeys(password);
    }

    public void clickLoginButton() {
        WebDriverUtils.waitForControlBeClickable(btn_Login);
        WebDriverUtils.scrollTillTheEnd();
        btn_Login.click();
    }

    public void clickRegisterLink() {
        WebDriverUtils.waitForControlBeClickable(link_Register);
        link_Register.click();
    }

    public void clickForgotPasswordLink() {
        WebDriverUtils.waitForControlBeClickable(link_ForgotPassword);
        link_ForgotPassword.click();
    }

    public String getLoginErrorMessage () {
        return txt_LoginErrorMessage.getText();
    }

    public String getEmailErrorMessage () {
        return txt_EmailErrorMessage.getText();
    }

    public String getPasswordErrorMessage () {
        WebDriverUtils.doesControlExist(txt_PasswordErrorMessage);
        WebDriverUtils.scrollTillTheEnd();
        return txt_PasswordErrorMessage.getText();
    }

    public void verifyErrorMessageAppears (ExtentTest logStep) {
        try{
            log4j.info("verifyErrorMessageAppears - Starts");
            TestReporter.logInfo(logStep, "Verify Error Message appears ...");

            Assertion.verifyActualAndExpected(logStep, true, WebDriverUtils.doesControlExist(txt_LoginErrorMessage));
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
            TestReporter.logInfo(logStep, "Login to Safe Railway ...");

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

    public void loginWithoutEmail (ExtentTest logTest, String password) {
        try{
            log4j.info("loginWithoutEmail method - Starts");
            TestReporter.logInfo(logTest, "loginWithoutEmail ...");

            inputPassword(password);
            clickLoginButton();

            log4j.info("loginWithoutEmail method - Ends");
        }catch (Exception e)
        {
            log4j.error("loginWithoutEmail method - ERROR: ", e);
            TestReporter.logException(logTest, "loginWithoutEmail method - ERROR", e);
        }
    }

    public void loginWithoutPassword (ExtentTest logTest, String emailAddress) {
        try{
            log4j.info("loginWithoutPassword method - Starts");
            TestReporter.logInfo(logTest, "loginWithoutPassword ...");

            inputEmail(emailAddress);
            clickLoginButton();

            log4j.info("loginWithoutPassword method - Ends");
        }catch (Exception e)
        {
            log4j.error("loginWithoutPassword method - ERROR: ", e);
            TestReporter.logException(logTest, "loginWithoutPassword method - ERROR", e);
        }
    }
    public void verifyLoginPageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyLoginPageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Login page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, txt_Email, "Email field");
            Assertion.checkControlExist(logTest, txt_Password, "Password field");
            Assertion.checkControlExist(logTest, btn_Login, "Login button");
            Assertion.checkControlExist(logTest, link_Register, "Register link");
            Assertion.checkControlExist(logTest, link_ForgotPassword, "Forgot password link");

            log4j.info("verifyLoginPageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyLoginPageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Login page - ERROR", e);
        }
    }
}

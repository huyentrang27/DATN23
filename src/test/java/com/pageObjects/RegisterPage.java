package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    /**
     * Locators
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement header;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement txt_Email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement txt_Password;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement txt_ConfirmPassword;
    @FindBy(xpath = "//input[@id='pid']")
    private WebElement txt_PID;
    @FindBy(xpath = "//input[@value='Register']")
    private WebElement btn_Register;
    @FindBy(xpath = "//div[@id='content']/descendant::p")
    private WebElement txt_RegisterSuccessfullyMessage;
    @FindBy(xpath = "//p[@class='message error']")
    private WebElement txt_RegisterErrorMessage;
    @FindBy(xpath = "//label[@for='email'][@class='validation-error']")
    private WebElement txt_EmailErrorMessage;
    @FindBy(xpath = "//label[@for='password'][@class='validation-error']")
    private WebElement txt_PasswordErrorMessage;
    @FindBy(xpath = "//label[@for='confirmPassword'][@class='validation-error']")
    private WebElement txt_ConfirmPasswordErrorMessage;
    @FindBy(xpath = "//label[@for='pid'][@class='validation-error']")
    private WebElement txt_PIDErrorMessage;
    @FindBy(xpath = "//div[@id='content']//a[contains(., 'login')]")
    private WebElement link_Login;

    /**
     * Methods
     */
    public void inputEmail (String email) {
        WebDriverUtils.waitForControl(txt_Email);
        txt_Email.sendKeys(email);
    }

    public void inputPassword (String password) {
        WebDriverUtils.waitForControl(txt_Password);
        txt_Password.sendKeys(password);
    }

    public void inputConfirmPassword (String confirmPassword) {
        WebDriverUtils.waitForControl(txt_ConfirmPassword);
        txt_ConfirmPassword.sendKeys(confirmPassword);
    }

    public void inputPID (String pid) {
        WebDriverUtils.waitForControl(txt_PID);
        txt_PID.sendKeys(pid);
    }

    public void clickRegisterButton () {
        WebDriverUtils.waitForControlBeClickable(btn_Register);
        btn_Register.click();
    }

    public void clickLoginLink () {
        WebDriverUtils.waitForControlBeClickable(link_Login);
        link_Login.click();
    }

    public String getRegisterSuccessfullyMessage () {
        return txt_RegisterSuccessfullyMessage.getText();
    }

    public String getRegisterErrorMessage () {
        return txt_RegisterErrorMessage.getText();
    }

    public String getEmailErrorMessage () {
        return txt_EmailErrorMessage.getText();
    }

    public String getPasswordErrorMessage () {
        return txt_PasswordErrorMessage.getText();
    }

    public String getConfirmPasswordErrorMessage () {
        return txt_ConfirmPasswordErrorMessage.getText();
    }

    public String getPIDErrorMessage () {
        return txt_PIDErrorMessage.getText();
    }

    public void register (ExtentTest logTest, String email, String password, String confirmPass, String PID) {
        try{
            log4j.info("Register method - Starts");
            TestReporter.logInfo(logTest, "Register an account appears ...");

            inputEmail(email);
            inputPassword(password);
            inputConfirmPassword(confirmPass);
            inputPID(PID);
            clickRegisterButton();

            log4j.info("Register method - Ends");
        }catch (Exception e)
        {
            log4j.error("Register method - ERROR: ", e);
            TestReporter.logException(logTest, "Register method - ERROR", e);
        }
    }

    public void register (ExtentTest logTest, String email, String password, String PID) {
        try{
            log4j.info("Register method - Starts");
            TestReporter.logInfo(logTest, "Register an account appears ...");

            inputEmail(email);
            inputPassword(password);
            inputConfirmPassword(password);
            inputPID(PID);
            clickRegisterButton();

            log4j.info("Register method - Ends");
        }catch (Exception e)
        {
            log4j.error("Register method - ERROR: ", e);
            TestReporter.logException(logTest, "Register method - ERROR", e);
        }
    }

    public void registerWithoutEmail (ExtentTest logTest, String password, String PID) {
        try{
            log4j.info("Register method - Starts");
            TestReporter.logInfo(logTest, "Register an account appears ...");

            inputPassword(password);
            inputConfirmPassword(password);
            inputPID(PID);
            clickRegisterButton();

            log4j.info("Register method - Ends");
        }catch (Exception e)
        {
            log4j.error("Register method - ERROR: ", e);
            TestReporter.logException(logTest, "Register method - ERROR", e);
        }
    }

    public void registerWithoutPassword (ExtentTest logTest, String emailAddress, String PID) {
        try{
            log4j.info("Register method - Starts");
            TestReporter.logInfo(logTest, "Register an account appears ...");

            inputEmail(emailAddress);
            inputPID(PID);
            clickRegisterButton();

            log4j.info("Register method - Ends");
        }catch (Exception e)
        {
            log4j.error("Register method - ERROR: ", e);
            TestReporter.logException(logTest, "Register method - ERROR", e);
        }
    }

    public void registerWithoutPID (ExtentTest logTest, String emailAddress, String password) {
        try{
            log4j.info("Register method - Starts");
            TestReporter.logInfo(logTest, "Register an account appears ...");

            inputEmail(emailAddress);
            inputPassword(password);
            inputConfirmPassword(password);
            clickRegisterButton();

            log4j.info("Register method - Ends");
        }catch (Exception e)
        {
            log4j.error("Register method - ERROR: ", e);
            TestReporter.logException(logTest, "Register method - ERROR", e);
        }
    }

    public void verifyRegisterPageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyRegisterPageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Register page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, txt_Email, "Email field");
            Assertion.checkControlExist(logTest, txt_Password, "Password field");
            Assertion.checkControlExist(logTest, txt_ConfirmPassword, "Confirm Password field");
            Assertion.checkControlExist(logTest, txt_PID, "PID / Passport Number filed");
            Assertion.checkControlExist(logTest, btn_Register, "Register button");
            Assertion.checkControlExist(logTest, link_Login, "Login link");

            log4j.info("verifyRegisterPageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyRegisterPageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Register page - ERROR", e);
        }
    }
}

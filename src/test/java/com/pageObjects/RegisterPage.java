package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Utility {
    /**
     * Locators
     */
    @FindBy(xpath = "//input[@id='email']")
    private WebElement txt_email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement txt_password;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement txt_confirmPassword;
    @FindBy(xpath = "//input[@id='pid']")
    private WebElement txt_pid;
    @FindBy(xpath = "//input[@value='Register']")
    private WebElement btn_register;
    @FindBy(xpath = "//div[@id='content']/descendant::p")
    private WebElement txt_registerSuccessfullyMessage;
    @FindBy(xpath = "//p[@class='message error']")
    private WebElement txt_registerErrorMessage;
    @FindBy(xpath = "//label[@for='password'][@class='validation-error']")
    private WebElement txt_invalidPasswordMessage;
    @FindBy(xpath = "//label[@for='pid'][@class='validation-error']")
    private WebElement txt_invalidPIDMessage;

    /**
     * Methods
     */
    public void inputEmail (String email) {
        WebDriverUtils.waitForControl(txt_email);
        txt_email.sendKeys(email);
    }

    public void inputPassword (String password) {
        WebDriverUtils.waitForControl(txt_password);
        txt_password.sendKeys(password);
    }

    public void inputConfirmPassword (String confirmPassword) {
        WebDriverUtils.waitForControl(txt_confirmPassword);
        txt_confirmPassword.sendKeys(confirmPassword);
    }

    public void inputPID (String pid) {
        WebDriverUtils.waitForControl(txt_pid);
        txt_pid.sendKeys(pid);
    }

    public void clickRegisterButton () {
        WebDriverUtils.waitForControlBeClickable(btn_register);
        btn_register.click();
    }

    public String getRegisterSuccessfullyMessage () {
        return txt_registerSuccessfullyMessage.getText();
    }

    public String getRegisterErrorMessage () {
        return txt_registerErrorMessage.getText();
    }

    public String getInvalidPasswordMessage () {
        return txt_invalidPasswordMessage.getText();
    }

    public String getInvalidIDMessage () {
        return txt_invalidPIDMessage.getText();
    }

    public void register (ExtentTest logStep, String email, String password, String confirmPass, String PID) {
        try{
            log4j.info("register - Starts");
            TestReporter.logInfo(logStep, "Register an account appears ...");

            inputEmail(email);
            inputPassword(password);
            inputConfirmPassword(confirmPass);
            inputPID(PID);
            clickRegisterButton();

            log4j.info("register method - Ends");
        }catch (Exception e)
        {
            log4j.error("register method - ERROR: ", e);
            TestReporter.logException(logStep, "register - ERROR", e);
        }
    }
}

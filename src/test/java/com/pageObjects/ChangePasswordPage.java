package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//input[@id='currentPassword']")
    private WebElement txt_currentPassword;
    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement txt_newPassword;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement txt_confirmPassword;
    @FindBy(xpath = "//input[@value='Change Password']")
    private WebElement btn_changePassword;
    @FindBy(xpath = "//p[@class='message error']")
    private WebElement txt_messageError;

    /**
     * Methods
     */
    public void typeCurrentPasswordField (String currentPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_currentPassword);
        txt_currentPassword.sendKeys(currentPassword);
    }

    public void typeNewPasswordField (String newPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_newPassword);
        txt_newPassword.sendKeys(newPassword);
    }

    public void typeConfirmPasswordField (String confirmPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_confirmPassword);
        txt_confirmPassword.sendKeys(confirmPassword);
    }

    public void clickChangePasswordButton () {
        WebDriverUtils.waitForControlBeClickable(btn_changePassword);
        btn_changePassword.click();
    }

    public void clickChangePasswordButton (ExtentTest logStep) {
        try{
            log4j.info("clickChangePasswordButton - Starts");
            TestReporter.logInfo(logStep, "Click Change Password button ...");

            clickChangePasswordButton ();
            log4j.info("clickChangePasswordButton method - Ends");
        }catch (Exception e)
        {
            log4j.error("clickChangePasswordButton method - ERROR: ", e);
            TestReporter.logException(logStep, "clickChangePasswordButton - ERROR", e);
        }
    }

    public void verifyErrorMessageAppears (ExtentTest logStep, String errorMessage) {
        try{
            log4j.info("verifyErrorMessageAppears - Starts");
            TestReporter.logInfo(logStep, "Verify Error Message appears ...");

            Assertion.verifyActualAndExpected(logStep, txt_messageError.getText().toString(), errorMessage);
            log4j.info("verifyErrorMessageAppears method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyErrorMessageAppears method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyErrorMessageAppears - ERROR", e);
        }
    }

    public void changePassword (ExtentTest logStep, String currentPass, String newPass, String confirmNewPass) {
        try{
            log4j.info("changePassword - Starts");
            TestReporter.logInfo(logStep, "Change password appears ...");

            typeCurrentPasswordField(currentPass);
            typeNewPasswordField(newPass);
            typeConfirmPasswordField(confirmNewPass);
            clickChangePasswordButton();

            log4j.info("changePassword method - Ends");
        }catch (Exception e)
        {
            log4j.error("changePassword method - ERROR: ", e);
            TestReporter.logException(logStep, "changePassword - ERROR", e);
        }
    }
}

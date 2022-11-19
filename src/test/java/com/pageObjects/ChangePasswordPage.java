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
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement header;
    @FindBy(xpath = "//input[@id='currentPassword']")
    private WebElement txt_CurrentPassword;
    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement txt_NewPassword;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement txt_ConfirmPassword;
    @FindBy(xpath = "//input[@value='Change Password']")
    private WebElement btn_ChangePassword;
    @FindBy(xpath = "//p[@class='message error']")
    private WebElement txt_messageError;
    @FindBy(xpath = "//p[@class='message success']")
    private WebElement txt_successMessage;
    @FindBy(xpath = "//label[@for='currentPassword'][@class='validation-error']")
    private WebElement txt_CurrentPassErrorMessage;
    @FindBy(xpath = "//label[@for='newPassword'][@class='validation-error']")
    private WebElement txt_newPassErrorMessage;
    @FindBy(xpath = "//label[@for='confirmPassword'][@class='validation-error']")
    private WebElement txt_confirmPassErrorMessage;

    /**
     * Methods
     */
    public void typeCurrentPasswordField (String currentPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_CurrentPassword);
        txt_CurrentPassword.sendKeys(currentPassword);
    }

    public void typeNewPasswordField (String newPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_NewPassword);
        txt_NewPassword.sendKeys(newPassword);
    }

    public void typeConfirmPasswordField (String confirmPassword) {
        WebDriverUtils.waitForControlBeClickable(txt_ConfirmPassword);
        txt_ConfirmPassword.sendKeys(confirmPassword);
    }

    public void clickChangePasswordButton () {
        WebDriverUtils.scrollTillElementVisible(btn_ChangePassword);
        WebDriverUtils.waitForControlBeClickable(btn_ChangePassword);
        btn_ChangePassword.click();
    }

    public String getChangePasswordSuccessMessage () {
        WebDriverUtils.waitForControlBeClickable(txt_successMessage);
        return txt_successMessage.getText();
    }

    public String getChangePasswordErrorMessage () {
        WebDriverUtils.waitForControlBeClickable(txt_messageError);
        return txt_messageError.getText();
    }

    public String getCurrentPasswordErrorMessage () {
        WebDriverUtils.waitForControlBeClickable(txt_CurrentPassErrorMessage);
        return txt_CurrentPassErrorMessage.getText();
    }

    public String getNewPasswordErrorMessage () {
        WebDriverUtils.waitForControlBeClickable(txt_newPassErrorMessage);
        return txt_newPassErrorMessage.getText();
    }

    public String getConfirmPasswordErrorMessage () {
        WebDriverUtils.waitForControlBeClickable(txt_confirmPassErrorMessage);
        return txt_confirmPassErrorMessage.getText();
    }

    public void clickChangePasswordButton (ExtentTest logStep) {
        try{
            log4j.info("clickChangePasswordButton - Starts");
            TestReporter.logInfo(logStep, "Click Change Password button ...");

            clickChangePasswordButton();
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

            if(!currentPass.equals(""))
            {
                typeCurrentPasswordField(currentPass);
            }
            if (!newPass.equals(""))
            {
                typeNewPasswordField(newPass);
            }
            if (!confirmNewPass.equals(""))
            {
                typeConfirmPasswordField(confirmNewPass);
            }
            clickChangePasswordButton();

            log4j.info("changePassword method - Ends");
        }catch (Exception e)
        {
            log4j.error("changePassword method - ERROR: ", e);
            TestReporter.logException(logStep, "changePassword - ERROR", e);
        }
    }

    public void verifyChangePasswordPageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyLoginPageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Login page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, txt_NewPassword, "New password field");
            Assertion.checkControlExist(logTest, txt_CurrentPassword, "Current password field");
            Assertion.checkControlExist(logTest, txt_ConfirmPassword, "Confirm password field");
            Assertion.checkControlExist(logTest, btn_ChangePassword, "Change password button");

            log4j.info("verifyLoginPageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyLoginPageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Login page - ERROR", e);
        }
    }
}

package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage{
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//fieldset/legend")
    private WebElement header;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement txt_Email;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btn_SendInstructions;

    /**
     * Methods
     */
    public void verifyForgotPasswordPageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyForgotPasswordPageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Forgot password page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, txt_Email, "Email field");
            Assertion.checkControlExist(logTest, btn_SendInstructions, "Send Instructions button");
            log4j.info("verifyForgotPasswordPageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyForgotPasswordPageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Forgot password page - ERROR", e);
        }
    }
}

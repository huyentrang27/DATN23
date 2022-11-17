package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_RES_013_Unable_to_register_new_account_when_password_and_confirm_password_field_are_not_match extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Unable to register new account when password and confirm password field are not match")
    public void TC_RES_013(Hashtable<String, String> data) {
        String password = DataFaker.generateTimeStampString("yymmddssmmhh");
        String confirmPassword = DataFaker.generateTimeStampString("hhmmssddmmyy");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that error message displays when user register by using invalid email");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, EMAIL_ADDRESS, password, confirmPassword, PID);
        Assertion.verifyActualAndExpected(logStep, registerPage.getRegisterErrorMessage(), data.get("RegisterErrMessage"));
        Assertion.verifyActualAndExpected(logStep, registerPage.getConfirmPasswordErrorMessage(), data.get("ConfirmPasswordErrMessage"));

    }
}

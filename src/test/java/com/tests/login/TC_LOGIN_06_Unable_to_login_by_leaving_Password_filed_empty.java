package com.tests.login;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.LoginPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.*;

public class TC_LOGIN_06_Unable_to_login_by_leaving_Password_filed_empty extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to login by leaving Password field empty")
    public void TC_LOGIN_06(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Register new account and go to login page");
        CommonMethods.registerAndGoToLoginPage(logStep, emailAddress, PASSWORD, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Login without password");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.loginWithoutPassword(logStep, emailAddress);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and login unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, loginPage.getLoginErrorMessage(), data.get("LoginErrMessage"));
        Assertion.verifyActualAndExpected(logStep, loginPage.getPasswordErrorMessage(), data.get("PasswordErrMessage"));
    }
}

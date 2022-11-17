package com.tests.login;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.LoginPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_LOGIN_08_Verify_that_password_is_case_sensitive extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Password is case sensitive")
    public void TC_LOGIN_08(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);
        String password1 = data.get("Password1");
        String password2 = data.get("Password2");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Register new account using a password containing all uppercase characters then go to the login page");
        CommonMethods.registerAndGoToLoginPage(logStep, emailAddress, password1, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Login by using a that password but containing all lowercase characters");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, emailAddress, password2);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and login unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, loginPage.getLoginErrorMessage(), data.get("LoginErrMessage"));
    }
}

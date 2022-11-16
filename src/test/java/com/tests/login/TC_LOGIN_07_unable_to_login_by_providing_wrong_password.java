package com.tests.login;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.LoginPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;
import static com.common.Constant.PID;

public class TC_LOGIN_07_unable_to_login_by_providing_wrong_password extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to login by providing wrong password")
    public void TC_LOGIN_07(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);
        String password1 = data.get("Password1");
        String password2 = data.get("Password2");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Register new account and go to login page");
        CommonMethods.registerAndGoToLoginPage(logStep, emailAddress, password1, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Login with wrong password");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, emailAddress, password2);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and login unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, loginPage.getLoginErrorMessage(), data.get("LoginErrMessage"));
    }
}

package com.tests.login;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.LoginPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static com.common.Constant.*;
import static com.common.Constant.RAILWAY_HOMEPAGE_URL;

public class TC_LOGIN_03_Able_to_login_with_valid_info extends TestBase {

    @Test(description = "Able to login with valid account")
    public void TC_LOGIN_03() {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Register new account and go to login page");
        CommonMethods.registerAndGoToLoginPage(logStep, emailAddress, PASSWORD, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Login with registered account");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, emailAddress, PASSWORD);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that login successfully and home page display");
        String currentURL = Utility.getDriver().getCurrentUrl();
        Assertion.verifyActualAndExpected(logStep, currentURL, RAILWAY_HOMEPAGE_URL);
    }
}

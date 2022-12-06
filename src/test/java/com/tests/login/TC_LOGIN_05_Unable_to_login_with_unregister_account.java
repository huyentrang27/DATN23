package com.tests.login;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.*;

public class TC_LOGIN_05_Unable_to_login_with_unregister_account extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Unable to login with unregistered account ")
    public void TC_LOGIN_05(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Login page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnLoginTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Login with unregistered account");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, emailAddress, PASSWORD);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and login unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, loginPage.getLoginErrorMessage(), data.get("LoginErrMessage"));
    }
}

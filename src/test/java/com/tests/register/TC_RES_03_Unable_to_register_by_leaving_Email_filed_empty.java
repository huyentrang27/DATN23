package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_RES_03_Unable_to_register_by_leaving_Email_filed_empty extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to register new account by leaving Email field empty")
    public void TC_RES_03(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Register new account");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.registerWithoutEmail(logStep, PASSWORD, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and register unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, registerPage.getRegisterErrorMessage(), data.get("RegisterErrMessage"));
        Assertion.verifyActualAndExpected(logStep, registerPage.getEmailErrorMessage(), data.get("EmailErrMessage"));
    }
}

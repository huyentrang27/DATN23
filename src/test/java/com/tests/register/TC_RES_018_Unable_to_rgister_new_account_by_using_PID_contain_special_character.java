package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_RES_018_Unable_to_rgister_new_account_by_using_PID_contain_special_character extends TestBase {
    @Test(dataProvider = "getDataForTest", description = "Unable to register account by using PID contain special character")
    public void TC_RES_016(Hashtable<String, String> data) {
        String PID = data.get("PID");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify that error message displays when user register by using invalid email");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, EMAIL_ADDRESS, PASSWORD, PID);
        Assertion.verifyActualAndExpected(logStep, registerPage.getRegisterErrorMessage(), data.get("RegisterErrMessage"));
        Assertion.verifyActualAndExpected(logStep, registerPage.getPIDErrorMessage(), data.get("PIDErrMessage"));

    }
}
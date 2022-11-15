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

public class TC_RES_015_Unable_to_register_by_leaving_PID_Passport_number_filed_empty extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to register new account by leaving PID / Passport number field empty")
    public void TC_RES_015(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Register new account");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.registerWithoutPID(logStep, EMAIL_ADDRESS, PASSWORD);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify error message displays and register unsuccessfully");
        Assertion.verifyActualAndExpected(logStep, registerPage.getRegisterErrorMessage(), data.get("RegisterErrMessage"));
        Assertion.verifyActualAndExpected(logStep, registerPage.getPIDErrorMessage(), data.get("PIDErrMessage"));
    }
}

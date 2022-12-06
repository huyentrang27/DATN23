package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.*;

public class TC_RES_06_Unable_to_register_new_account_by_using_a_registered_account_before extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to register new account by using a registered account before")
    public void TC_RES_06(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Register new account");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, emailAddress, PASSWORD, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that user is unable to register new account by using a registered account before");
        registerPage.clickOnRegisterTab();
        registerPage.register(logStep, emailAddress, PASSWORD, PID);
        Assertion.verifyActualAndExpected(logStep, data.get("RegisterErrMessage"), registerPage.getRegisterErrorMessage());
    }
}

package com.tests;

import com.common.TestBase;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC01 extends TestBase {
    @Test(dataProvider = "getDataForTest", description = "User is able to register new account")
    public void TC01(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Register new account");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, emailAddress, PASSWORD, CONFIRM_PASSWORD, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify register successfully message display");
        Assertion.verifyActualAndExpected(logStep, data.get("message"), registerPage.getRegisterSuccessfullyMessage());

    }
}

package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_RES_02_Able_to_register_an_account_with_valid_info extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is able to register new account")
    public void TC_RES_02(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(data.get("Email"));
        String password = data.get("Password");
        String PID = data.get("PID");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Register new account");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, emailAddress, password, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify register successfully message display");
        Assertion.verifyActualAndExpected(logStep, data.get("Message"), registerPage.getRegisterSuccessfullyMessage());

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Login with registered account");
        registerPage.clickOnLoginTab();
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, emailAddress, password);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify login successfully and Home page displays");
        String currentURL = Utility.getDriver().getCurrentUrl();
        Assertion.verifyActualAndExpected(logStep, currentURL, RAILWAY_HOMEPAGE_URL);
    }
}

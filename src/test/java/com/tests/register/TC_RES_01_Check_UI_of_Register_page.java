package com.tests.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static com.common.GlobalVariables.RAILWAY_URL;

public class TC_RES_01_Check_UI_of_Register_page extends TestBase {

    @Test(description = "Check UI of Register page")
    public void TC_RES_01() {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Register page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify Register page display");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.verifyRegisterPageDisplayProperly(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that login link in Register page direct user to Login page");
        registerPage.clickLoginLink();
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.verifyLoginPageDisplayProperly(logStep);
    }
}

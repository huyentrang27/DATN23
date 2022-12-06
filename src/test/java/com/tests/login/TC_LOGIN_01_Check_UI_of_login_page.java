package com.tests.login;

import com.common.TestBase;
import com.pageObjects.ForgotPasswordPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static com.common.GlobalVariables.RAILWAY_URL;

public class TC_LOGIN_01_Check_UI_of_login_page extends TestBase {

    @Test(description = "Check UI of Login page")
    public void TC_LOGIN_01() {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Login page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnLoginTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify Login page display properly");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.verifyLoginPageDisplayProperly(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that 'Registration Page' link in Login page direct user to Register page");
        loginPage.clickRegisterLink();
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.verifyRegisterPageDisplayProperly(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Verify that 'Forgot Password page' link in Login page direct user to Forgot password page");
        registerPage.clickOnLoginTab();
        loginPage.clickForgotPasswordLink();
        ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(Utility.getDriver(), ForgotPasswordPage.class);
        forgotPasswordPage.verifyForgotPasswordPageDisplayProperly(logStep);
    }
}

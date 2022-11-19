package com.tests.changepassword;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.ChangePasswordPage;
import com.pageObjects.HomePage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.*;

public class TC_CP_06_08_Unable_to_change_password_by_leaving_New_password_or_Confirm_password_field_empty extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "User is unable to Change Password by leaving new password or confirm password field empty")
    public void TC_CP_04(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);
        String password = data.get("Password");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep, emailAddress, password, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Change password page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnChangePasswordTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that Change password page display properly");
        ChangePasswordPage changePasswordPage = PageFactory.initElements(Utility.getDriver(), ChangePasswordPage.class);
        changePasswordPage.changePassword(logStep, password, "", "");
        Assertion.verifyActualAndExpected(logStep, changePasswordPage.getChangePasswordErrorMessage(), data.get("ErrorMessage"));
        Assertion.verifyActualAndExpected(logStep, changePasswordPage.getNewPasswordErrorMessage(), data.get("NewPasswordErrorMessage"));
    }
}

package com.tests.changepassword;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.ChangePasswordPage;
import com.pageObjects.HomePage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.*;

public class TC_CP_09_Unable_to_change_password_when_new_password_and_confirm_password_field_are_not_match extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Unable to change password when new password and confirm password field are not match")
    public void TC_CP_04(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);
        String password = data.get("Password");
        String newPassword = data.get("NewPassword");
        String confirmPassword = data.get("ConfirmPassword");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep, emailAddress, password, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Change password page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnChangePasswordTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that Change password page display properly");
        ChangePasswordPage changePasswordPage = PageFactory.initElements(Utility.getDriver(), ChangePasswordPage.class);
        changePasswordPage.changePassword(logStep, password, newPassword, confirmPassword);
        Assertion.verifyActualAndExpected(logStep, changePasswordPage.getChangePasswordErrorMessage(), data.get("ErrorMessage"));
        Assertion.verifyActualAndExpected(logStep, changePasswordPage.getConfirmPasswordErrorMessage(), data.get("ConfirmPasswordErrorMessage"));
    }
}

package com.tests.changepassword;

import com.common.CommonMethods;
import com.common.GlobalVariables;
import com.common.TestBase;
import com.pageObjects.ChangePasswordPage;
import com.pageObjects.HomePage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.PID;
import static com.common.GlobalVariables.RAILWAY_URL;

public class TC_CP_03_User_is_able_to_change_password_by_providing_valid_information extends TestBase {
    @Test(dataProvider = "getDataForTest", description = "User is able to change password by providing valid information")
    public void TC_CP_03(Hashtable<String, String> data) {
        String emailAddress = DataFaker.generateRandomEmail(GlobalVariables.EMAIL_ADDRESS);
        String password = data.get("Password");
        String newPassword = data.get("NewPassword");;

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep, emailAddress, password, PID);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Change password page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnChangePasswordTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that Change password page display properly");
        ChangePasswordPage changePasswordPage = PageFactory.initElements(Utility.getDriver(), ChangePasswordPage.class);
        changePasswordPage.changePassword(logStep, password, newPassword, newPassword);
        Assertion.verifyActualAndExpected(logStep, changePasswordPage.getChangePasswordSuccessMessage(), data.get("SuccessMessage"));
    }
}

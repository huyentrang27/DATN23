package com.tests.changepassword;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.BookTicketPage;
import com.pageObjects.ChangePasswordPage;
import com.pageObjects.HomePage;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static com.common.Constant.RAILWAY_URL;

public class TC_CP_01_Check_UI_of_Change_password_page extends TestBase {

    @Test(description = "Check UI of Change password page")
    public void TC_CP_01() {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Change password page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnChangePasswordTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that Change password page display properly");
        ChangePasswordPage changePasswordPage = PageFactory.initElements(Utility.getDriver(), ChangePasswordPage.class);
        changePasswordPage.verifyChangePasswordPageDisplayProperly(logStep);
    }
}

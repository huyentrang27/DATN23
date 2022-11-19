package com.tests.timetable;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.RegisterPage;
import com.pageObjects.TimeTablePage;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static com.common.Constant.RAILWAY_URL;

public class TC_TIME_01_Check_UI_of_Timetable_page extends TestBase {

    @Test(description = "Check UI of Timetable page")
    public void TC_TIME_01() {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Timetable page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify Timetable page display properly");
        TimeTablePage timeTablePage = PageFactory.initElements(Utility.getDriver(), TimeTablePage.class);
        timeTablePage.verifyTimetablePageDisplayProperly(logStep);
    }
}

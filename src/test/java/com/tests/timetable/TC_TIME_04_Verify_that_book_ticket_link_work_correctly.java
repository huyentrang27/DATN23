package com.tests.timetable;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.*;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.RAILWAY_URL;


public class TC_TIME_04_Verify_that_book_ticket_link_work_correctly extends TestBase {
    @Test(dataProvider = "getDataForTest", description = "Verify book ticket link works correctly")
    public void TC_TIME_04(Hashtable<String, String> data) {
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Timetable page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTimeTableTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Click book ticket link of row has Depart Station is: " + departStation + " and Arrive Station is: " + arriveStation);
        TimeTablePage timeTablePage = PageFactory.initElements(Utility.getDriver(), TimeTablePage.class);
        timeTablePage.clickOnBookTicketLink(logStep, departStation, arriveStation);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Verify that Book Ticket page display and " + departStation + " is selected as Depart Station and " + arriveStation + " is selected as Arrive Station");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.checkBookTicketPageDisplayed(logStep);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getSelectedDepartStation(), departStation);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getSelectedArriveStation(), arriveStation);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify that user can book ticket with selected Depart Station and Arrive Station");
        String departDate = bookTicketPage.getSelectedDepartDate();
        String seatType = bookTicketPage.getFirstSeatTypeOption();
        String ticketAmount = bookTicketPage.getSelectedTicketAmount();
        bookTicketPage.clickBookTicketButton(logStep);
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        bookTicketSuccessfullyPage.verifyBookTicketSuccessfullyPageDisplay(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);
    }
}

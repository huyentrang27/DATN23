package com.tests.myTickets;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.BookTicketPage;
import com.pageObjects.BookTicketSuccessfullyPage;
import com.pageObjects.HomePage;
import com.pageObjects.MyTicketPage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static com.common.Constant.RAILWAY_URL;

public class TC_MT_011_Error_message_displays_when_filtering_with_criteria_not_match_with_any_information_of_ticket extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Error message displays when filtering with criteria not match with any information ofticket")
    public void TC_MT_011(Hashtable<String, String> data) {
        int i;
        List<String> departStation = Arrays.asList(data.get("DepartStation").split("-"));
        List<String> seatType = Arrays.asList(data.get("SeatType").split("-"));
        String ticketAmount = "1";
        String departDateFilter = data.get("DepartDateFilter");
        String departStationFilter = data.get("DepartStationFilter");
        String arriveStationFilter = data.get("ArriveStationFilter");
        String statusFilter = data.get("StatusFilter");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Book tickets 6 times");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        for (i=0; i<6; i++)
        {
            bookTicketPage.bookTicket(logStep, CommonMethods.getSomeDaysAfter(i+4), departStation.get(i), "", seatType.get(i), ticketAmount);
            bookTicketSuccessfullyPage.clickOnBookTicketTab();

        }

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Go to My Ticket page");
        bookTicketPage.clickOnMyTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Filter ticket");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        myTicketPage.filterTicket(logStep, departStationFilter, arriveStationFilter, departDateFilter, statusFilter);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify error message display");
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getErrorMessage(), data.get("ErrorMessage"));
    }
}

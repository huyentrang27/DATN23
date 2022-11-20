package com.tests.myTickets;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.BookTicketPage;
import com.pageObjects.BookTicketSuccessfullyPage;
import com.pageObjects.HomePage;
import com.pageObjects.MyTicketPage;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.RAILWAY_URL;

public class TC_MT_01_02_Check_UI_of_My_Ticket_page extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Check UI of My Ticket page")
    public void TC_MT_01_02(Hashtable<String, String> data) {
        String departDate = CommonMethods.getSomeDaysAfter(4);
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");
        String seatType = data.get("SeatType");
        String ticketAmount = "1";

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Book tickets");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.bookTicket(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Go to My Ticket page");
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        bookTicketSuccessfullyPage.clickOnMyTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify My Ticket page displays properly");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        myTicketPage.verifyMyTicketPageDisplay(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);
    }
}

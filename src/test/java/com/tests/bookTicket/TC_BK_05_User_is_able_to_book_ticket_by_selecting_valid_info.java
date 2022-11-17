package com.tests.bookTicket;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.BookTicketPage;
import com.pageObjects.BookTicketSuccessfullyPage;
import com.pageObjects.HomePage;
import com.utility.DataFaker;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.RAILWAY_URL;

public class TC_BK_05_User_is_able_to_book_ticket_by_selecting_valid_info extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify that user can book ticket successfully")
    public void TC_BK_05(Hashtable<String, String> data) {
        String departDate = CommonMethods.getSomeDaysAfter(5);
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");
        String seatType = data.get("SeatType");
        String ticketAmount = data.get("TicketAmount");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Book ticket");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.bookTicket(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Verify Book Ticket Successfully page display with right info");
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        bookTicketSuccessfullyPage.verifyBookTicketSuccessfullyPageDisplay(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);
    }
}

package com.tests.bookTicket;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.BookTicketPage;
import com.pageObjects.BookTicketSuccessfullyPage;
import com.pageObjects.HomePage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.Constant.RAILWAY_URL;

public class TC_BK_06_07_User_can_book_up_to_10_tickets extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify that user can book up to 10 tickets")
    public void TC_BK_06_07(Hashtable<String, String> data) {
        String departDate = CommonMethods.getSomeDaysAfter(5);
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");
        String seatType = data.get("SeatType");
        String ticketAmount1 = data.get("TicketAmount1");
        String ticketAmount2 = data.get("TicketAmount2");
        int ticketAmountRemaining = 10 - Integer.parseInt(ticketAmount1);

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Book" + ticketAmount1 +" ticket");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.bookTicket(logStep, departDate, departStation, arriveStation, seatType, ticketAmount1);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Book more than" + ticketAmount2 +" ticket");
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        bookTicketSuccessfullyPage.clickOnBookTicketTab();
        bookTicketPage.bookTicket(logStep, departDate, departStation, arriveStation, seatType, String.valueOf(ticketAmount2));

        int totalAmount = Integer.parseInt(ticketAmount1) + Integer.parseInt(ticketAmount2);
        if(totalAmount == 10)
        {
            logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify user can book ticket successfully");
            Assertion.verifyActualAndExpected(logStep, bookTicketSuccessfullyPage.doesBookTicketSuccessfullyHeaderExist(logStep), true);
        }
        else
        {
            logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify user can not complete book ticket and error message displays");
            Assertion.verifyActualAndExpected(logStep, bookTicketPage.getBookTicketErrorMessage(), data.get("BookTicketErrorMessage"));
            Assertion.verifyActualAndExpected(logStep, bookTicketPage.getAmountErrorMessage(), String.format(data.get("AmountErrorMessage"), ticketAmount1, String.valueOf(ticketAmountRemaining)));
        }
    }
}

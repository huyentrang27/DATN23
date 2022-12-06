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

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static com.common.GlobalVariables.RAILWAY_URL;

public class TC_MT_03_Filter_only_appears_when_ticket_table_has_more_than_6_rows extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Filter only appear when ticket table has more than 6 rows")
    public void TC_MT_03(Hashtable<String, String> data) {
        int i;
        List<String> departStation = Arrays.asList(data.get("DepartStation").split("-"));
        List<String> seatType = Arrays.asList(data.get("SeatType").split("-"));
        String ticketAmount = "1";

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Book tickets 5 times");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        for (i=0; i<5; i++)
        {
            bookTicketPage.bookTicket(logStep, CommonMethods.getSomeDaysAfter(i+4), departStation.get(i), "", seatType.get(i), ticketAmount);
            bookTicketSuccessfullyPage.clickOnBookTicketTab();

        }

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Go to My Ticket page");
        bookTicketPage.clickOnMyTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify that filter doesn't appear");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        Assertion.verifyActualAndExpected(logStep, myTicketPage.doesApplyFilterButtonExist(), false);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Book more 1 ticket");
        myTicketPage.clickOnBookTicketTab();
        bookTicketPage.bookTicket(logStep, CommonMethods.getSomeDaysAfter(i+4), departStation.get(i), "", seatType.get(i), ticketAmount);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Go to My ticket page and verify that filter already appears");
        bookTicketSuccessfullyPage.clickOnMyTicketTab();
        Assertion.verifyActualAndExpected(logStep, myTicketPage.doesApplyFilterButtonExist(), true);
    }
}

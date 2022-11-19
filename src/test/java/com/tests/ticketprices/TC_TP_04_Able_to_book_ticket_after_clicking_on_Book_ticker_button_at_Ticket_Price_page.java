package com.tests.ticketprices;

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

import static com.common.Constant.RAILWAY_URL;

public class TC_TP_04_Able_to_book_ticket_after_clicking_on_Book_ticker_button_at_Ticket_Price_page extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify book ticket button works correctly")
    public void TC_TP_04(Hashtable<String, String> data) {
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");
        String seatType = data.get("SeatType");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Ticket price page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTicketPriceTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Click check price button of row has Depart Station is: " + departStation + " and Arrive Station is: " + arriveStation);
        TicketPricePage ticketPricePage = PageFactory.initElements(Utility.getDriver(), TicketPricePage.class);
        ticketPricePage.checkPrice(logStep, departStation, arriveStation);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Click book ticket button of train has Seat Type is: " + seatType);
        ticketPricePage.clickOnBookTicketButton(logStep, seatType);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify that Book Ticket page display and " + departStation + " is selected as Depart Station and " + arriveStation + " is selected as Arrive Station");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.checkBookTicketPageDisplayed(logStep);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getSelectedDepartStation(), departStation);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getSelectedArriveStation(), arriveStation);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getFirstSeatTypeOption(), seatType);

        logStep = TestReporter.logStepInfo(logMethod, "Step #7: Verify that user can book ticket with selected Depart Station, Arrive Station and Seat type");
        String departDate = bookTicketPage.getSelectedDepartDate();
        String ticketAmount = bookTicketPage.getSelectedTicketAmount();
        bookTicketPage.clickBookTicketButton(logStep);
        BookTicketSuccessfullyPage bookTicketSuccessfullyPage = PageFactory.initElements(Utility.getDriver(), BookTicketSuccessfullyPage.class);
        bookTicketSuccessfullyPage.verifyBookTicketSuccessfullyPageDisplay(logStep, departDate, departStation, arriveStation, seatType, ticketAmount);
    }
}

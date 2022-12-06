package com.tests.ticketprices;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.TicketPricePage;
import com.pageObjects.TimeTablePage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.common.GlobalVariables.RAILWAY_URL;

public class TC_TP_02_Verify_that_Check_price_link_works_correctly extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Verify Check price link work correctly")
    public void TC_TP_03(Hashtable<String, String> data) {
        String departStation = data.get("DepartStation");
        String arriveStation = data.get("ArriveStation");

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Ticket price page");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTicketPriceTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Click check price link of row has Depart Station is: " + departStation + " and Arrive Station is: " + arriveStation);
        TicketPricePage ticketPricePage = PageFactory.initElements(Utility.getDriver(), TicketPricePage.class);
        ticketPricePage.checkPrice(logStep, departStation, arriveStation);

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify Ticket price page display with Depart Station is: " + departStation + " and Arrive Station is: " + arriveStation);
        Assertion.verifyActualAndExpected(logMethod, ticketPricePage.getTicketInformationHeader(), String.format(data.get("ExpectedInfoHeader"), departStation, arriveStation));
    }
}

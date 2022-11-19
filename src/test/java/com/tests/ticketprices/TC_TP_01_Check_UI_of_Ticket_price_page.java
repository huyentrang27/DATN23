package com.tests.ticketprices;

import com.common.TestBase;
import com.pageObjects.*;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static com.common.Constant.RAILWAY_URL;

public class TC_TP_01_Check_UI_of_Ticket_price_page extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Check UI of Ticket price page")
    public void TC_TP_01(Hashtable<String, String> data) {
        List<String> departStationList = Arrays.asList(data.get("DepartStationList").split("-"));

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Click on Ticket price tab");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTicketPriceTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Verify Train ticket pricing list display");
        TicketPricePage ticketPricePage = PageFactory.initElements(Utility.getDriver(), TicketPricePage.class);
        ticketPricePage.verifyTrainTicketPricingListDisplayProperly(logStep, departStationList);
    }
}

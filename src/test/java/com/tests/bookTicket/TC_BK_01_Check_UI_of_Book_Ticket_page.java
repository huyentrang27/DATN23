package com.tests.bookTicket;

import com.common.CommonMethods;
import com.common.TestBase;
import com.pageObjects.*;
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

public class TC_BK_01_Check_UI_of_Book_Ticket_page extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Check UI of Book Ticket page")
    public void TC_BK_01_03_04(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Step #1: Navigate to Railway");
        WebDriverUtils.navigateToTestSite(logStep, RAILWAY_URL);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Login to Railway");
        CommonMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #2: Go to Timetable page and get Arrive station for each Depart station");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnTimeTableTab();
        TimeTablePage timeTablePage = PageFactory.initElements(Utility.getDriver(), TimeTablePage.class);
        List<String> expectedArriveStationForDaNang = timeTablePage.getArriveStationValue("Đà Nẵng");
        List<String> expectedArriveStationForSaiGon = timeTablePage.getArriveStationValue("Sài Gòn");
        List<String> expectedArriveStationForPhanThiet = timeTablePage.getArriveStationValue("Phan Thiết");
        List<String> expectedArriveStationForNhaTrang = timeTablePage.getArriveStationValue("Nha Trang");
        List<String> expectedArriveStationForHue = timeTablePage.getArriveStationValue("Huế");
        List<String> expectedArriveStationForQuangNgai = timeTablePage.getArriveStationValue("Quảng Ngãi");

        logStep = TestReporter.logStepInfo(logMethod, "Step #3: Go to Book Ticket page");
        timeTablePage.clickOnBookTicketTab();

        logStep = TestReporter.logStepInfo(logMethod, "Step #4: Verify that Book Ticket page display properly");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        bookTicketPage.checkBookTicketPageDisplayed(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #5: Verify that Depart date drop down displays 3-30 days ahead");
        bookTicketPage.verifyDepartDateDisplay3To30daysAhead(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #6: Verify that value of Arrive At drop down display corresponding to Depart From drop down");
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Huế"), expectedArriveStationForHue);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Đà Nẵng"), expectedArriveStationForDaNang);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Sài Gòn"), expectedArriveStationForSaiGon);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Nha Trang"), expectedArriveStationForNhaTrang);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Phan Thiết"), expectedArriveStationForPhanThiet);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getArriveStationValue("Quảng Ngãi"), expectedArriveStationForQuangNgai);

        logStep = TestReporter.logStepInfo(logMethod, "Step #7: Verify that Ticket Amount drop down only displays 1-10");
        bookTicketPage.verifyTicketAmountDisplay1To10(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Step #8: Verify that all Seat types display under ST drop down");
        List<String> seatTypeList;
        String seatType = data.get("SeatTypes");
        seatTypeList = Arrays.asList(seatType.split("-"));
        bookTicketPage.verifyAllSeatTypesDisplay(logStep, seatTypeList);
    }
}

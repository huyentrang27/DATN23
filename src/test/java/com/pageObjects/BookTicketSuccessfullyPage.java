package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Hashtable;

public class BookTicketSuccessfullyPage extends BasePage{
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement bookTicketSuccessfullyPageHeader;
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1]")
    private WebElement departStation;
    @FindBy(xpath = "//tr/td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1]")
    private WebElement arriveStation;
    @FindBy(xpath = "//tr/td[count(//th[text()='Seat Type']/preceding-sibling::th) + 1]")
    private WebElement seatType;
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Date']/preceding-sibling::th) + 1]")
    private WebElement departDate;
    @FindBy(xpath = "//tr/td[count(//th[text()='Amount']/preceding-sibling::th) + 1]")
    private WebElement ticketAmount;

    /**
     * Methods
     */
    public String getBookTicketSuccessfullyText () {
        return bookTicketSuccessfullyPageHeader.getText();
    }

    public Hashtable<String, String> ticketInformation () {
        Hashtable<String, String> ticketInfo = new Hashtable<String, String>();
        ticketInfo.put("DepartDate", departDate.getText());
        ticketInfo.put("DepartStation", departStation.getText());
        ticketInfo.put("ArriveStation", arriveStation.getText());
        ticketInfo.put("SeatType", seatType.getText());
        ticketInfo.put("TicketAmount", ticketAmount.getText());
        return ticketInfo;
    }

    public boolean doesBookTicketSuccessfullyHeaderExist(ExtentTest logStep)
    {
        return WebDriverUtils.doesControlExist(bookTicketSuccessfullyPageHeader);
    }

    public void verifyBookTicketSuccessfullyPageDisplay(ExtentTest logStep, String departDate, String departStation, String arriveStation, String seatType, String ticketAmount)
    {
        try {
            log4j.info("verifyBookTicketSuccessfullyPageDisplay method - Starts");
            TestReporter.logInfo(logStep, "verifyBookTicketSuccessfullyPageDisplay ...");

            Assertion.checkControlExist(logStep, bookTicketSuccessfullyPageHeader, "Book Ticket Successfully page header");
            Assertion.verifyActualAndExpected(logStep, ticketInformation().get("DepartDate"), departDate);
            Assertion.verifyActualAndExpected(logStep, ticketInformation().get("DepartStation"), departStation);
            Assertion.verifyActualAndExpected(logStep, ticketInformation().get("ArriveStation"), arriveStation);
            Assertion.verifyActualAndExpected(logStep, ticketInformation().get("SeatType"), seatType);
            Assertion.verifyActualAndExpected(logStep, ticketInformation().get("TicketAmount"), ticketAmount);

            log4j.info("verifyBookTicketSuccessfullyPageDisplay method - Ends");
        } catch (Exception e) {
            log4j.error("verifyBookTicketSuccessfullyPageDisplay method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyBookTicketSuccessfullyPageDisplay - ERROR", e);
        }
    }
}

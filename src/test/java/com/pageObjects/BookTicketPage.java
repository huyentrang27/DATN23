package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage extends Utility {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement bookTicketPageHeader;
    @FindBy(xpath = "//select[@name='Date']")
    private WebElement departDateField;
    @FindBy(xpath = "//select[@name='DepartStation']")
    private WebElement departFromField;
    @FindBy(xpath = "//select[@name='ArriveStation']")
    private WebElement arriveAtField;
    @FindBy(xpath = "//select[@name='SeatType']")
    private WebElement seatTypeField;
    @FindBy(xpath = "//select[@name='TicketAmount']")
    private WebElement ticketAmountField;
    @FindBy(xpath = "//input[@value='Book ticket']")
    private WebElement btn_bookTicket;
    @FindBy(xpath = "//h1[@align='center'][text()='Ticket Booked Successfully!']")
    private WebElement txt_bookTicketSuccessfully;
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1]")
    private WebElement txt_infoDepartStationTicket;
    @FindBy(xpath = "//tr/td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1]")
    private WebElement txt_infoArriveAtTicket;
    @FindBy(xpath = "//tr/td[count(//th[text()='Seat Type']/preceding-sibling::th) + 1]")
    private WebElement txt_infoSeatTypeTicket;
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Date']/preceding-sibling::th) + 1]")
    private WebElement txt_infoDepartDateTicket;
    @FindBy(xpath = "//tr/td[count(//th[text()='Amount']/preceding-sibling::th) + 1]")
    private WebElement txt_infoAmountTicket;

    /**
     * Methods
     */
    public void selectValueForDepartDateField (String departDate) {
        Select valueOfDepartDateField = new Select(departDateField);
        WebDriverUtils.waitForControl(departDateField);
        valueOfDepartDateField.selectByVisibleText(departDate);
    }

    public void selectValueForDepartFromField (String departFrom) {
        Select valueOfDepartFromField = new Select(departFromField);
        WebDriverUtils.waitForControl(departFromField);
        valueOfDepartFromField.selectByVisibleText(departFrom);
    }

    public void selectValueForArriveAtField (String arriveAt) {
        Select valueOfArriveAtField = new Select(arriveAtField);
        WebDriverUtils.waitForControl(arriveAtField);
        valueOfArriveAtField.selectByVisibleText(arriveAt);
    }

    public void selectValueForSeatTypeField (String seatType) {
        Select valueOfSeatTypeField = new Select(seatTypeField);
        WebDriverUtils.waitForControl(seatTypeField);
        valueOfSeatTypeField.selectByVisibleText(seatType);
    }

    public void selectValueForTicketAmountField (String ticletAmount) {
        Select valueOfTicketAmountField = new Select(ticketAmountField);
        WebDriverUtils.waitForControl(ticketAmountField);
        valueOfTicketAmountField.selectByVisibleText(ticletAmount);
    }

    public void clickBookTicketButton () {
        WebDriverUtils.waitForControlBeClickable(btn_bookTicket);
        btn_bookTicket.click();
    }

    public void clickBookTicketButton(ExtentTest logStep){
        try{
            log4j.info("clickNextButton - Starts");
            TestReporter.logInfo(logStep, "Click Next button ...");

            clickBookTicketButton();
            log4j.info("clickNextButton method - Ends");
        }catch (Exception e)
        {
            log4j.error("clickNextButton method - ERROR: ", e);
            TestReporter.logException(logStep, "clickNextButton - ERROR", e);
        }
    }

    public String getBookTicketSuccessfullyText () {
        return txt_bookTicketSuccessfully.getText();
    }

    public String[] getInfoTicket () {
        String departStation = txt_infoDepartStationTicket.getText();
        String arriveAt = txt_infoArriveAtTicket.getText();
        String seatType = txt_infoSeatTypeTicket.getText();
        String departDate = txt_infoDepartDateTicket.getText();
        String amount = txt_infoAmountTicket.getText();
        String[] infoTicket = {departStation, arriveAt, seatType, departDate, amount};
        return infoTicket;
    }

    public void checkBookTicketPageDisplayed(ExtentTest logStep) {
        try {
            log4j.info("checkBookTicketPageDisplayed method - Starts");
            TestReporter.logInfo(logStep, "checkBookTicketPageDisplayed ...");

            Assertion.checkControlExist(logStep, bookTicketPageHeader, "Book Ticket page header");

            log4j.info("checkBookTicketPageDisplayed method - Ends");
        } catch (Exception e) {
            log4j.error("checkBookTicketPageDisplayed method - ERROR: ", e);
            TestReporter.logException(logStep, "checkBookTicketPageDisplayed - ERROR", e);
        }
    }

    public void bookTicket (ExtentTest logStep, String departDate, String departFrom, String arriveAt, String seatType, String amount) {
        try {
            log4j.info("bookTicket method - Starts");
            TestReporter.logInfo(logStep, "bookTicket ...");

            selectValueForDepartDateField(departDate);
            selectValueForDepartFromField(departFrom);
            selectValueForArriveAtField(arriveAt);
            selectValueForSeatTypeField(seatType);
            selectValueForTicketAmountField(amount);
            clickBookTicketButton();

            log4j.info("bookTicket method - Ends");
        } catch (Exception e) {
            log4j.error("bookTicket method - ERROR: ", e);
            TestReporter.logException(logStep, "bookTicket - ERROR", e);
        }
    }
}

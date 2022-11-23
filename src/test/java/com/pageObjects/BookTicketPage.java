package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.common.CommonMethods;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookTicketPage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement bookTicketPageHeader;
    @FindBy(xpath = "//select[@name='Date']")
    private WebElement cbx_departDateField;
    @FindBy(xpath = "//select[@name='Date']/option[1]")
    private WebElement departDateFirstOption;
    @FindBy(xpath = "//select[@name='Date']/option[last()]")
    private WebElement departDateLastOption;
    @FindBy(xpath = "//select[@name='DepartStation']")
    private WebElement cbx_departFromField;
    @FindBy(xpath = "//select[@name='DepartStation']/option[1]")
    private WebElement departStationFirstOption;
    @FindBy(xpath = "//select[@name='DepartStation']/option[@selected='selected']")
    private WebElement option_SelectedDepartStation;
    @FindBy(xpath = "//select[@name='ArriveStation']")
    private WebElement cbx_arriveAtField;
    @FindBy(xpath = "//select[@name='ArriveStation']/option[1]")
    private WebElement arriveStationFirstOption;
    @FindBy(xpath = "//select[@name='ArriveStation']/option[@selected='selected']")
    private WebElement option_SelectedArriveStation;
    @FindBy(xpath = "//select[@name='SeatType']")
    private WebElement cbx_seatTypeField;
    @FindBy(xpath = "//select[@name='SeatType']/option[1]")
    private WebElement option_FirstSeatType;
    @FindBy(xpath = "//select[@name='SeatType']/option[@selected='selected']")
    private WebElement option_SelectedSeatType;
    @FindBy(xpath = "//select[@name='TicketAmount']")
    private WebElement cbx_ticketAmountField;
    @FindBy(xpath = "//select[@name='TicketAmount']/option[1]")
    private WebElement ticketAmountFirstOption;
    @FindBy(xpath = "//select[@name='TicketAmount']/option[last()]")
    private WebElement ticketAmountLastOption;
    @FindBy(xpath = "//input[@value='Book ticket']")
    private WebElement btn_bookTicket;
    @FindBy(xpath = "//p[@class ='message error']")
    private WebElement txt_BookTicketErrorMessage;
    @FindBy(xpath = "//label[@class='validation-error']")
    private WebElement txt_AmountErrorMessage;
    /**
     * Dynamic xpath
     */
    String xpath_ArriveStationValue = "//select[@name='ArriveStation']/option";
    String seatTypeOptions = "//select[@name='SeatType']/option[%s]";

    /**
     * Methods
     */
    public void selectValueForDepartDateField (String departDate) {
        Select valueOfDepartDateField = new Select(cbx_departDateField);
        WebDriverUtils.waitForControl(cbx_departDateField);
        valueOfDepartDateField.selectByVisibleText(departDate);
    }

    public void selectValueForDepartFromField (String departStation) {
        Select valueOfDepartFromField = new Select(cbx_departFromField);
        WebDriverUtils.waitForControl(cbx_departFromField);
        valueOfDepartFromField.selectByVisibleText(departStation);
    }

    public void selectValueForArriveAtField (String arriveStation) {
        Select valueOfArriveAtField = new Select(cbx_arriveAtField);
        WebDriverUtils.waitForControl(cbx_arriveAtField);
        valueOfArriveAtField.selectByVisibleText(arriveStation);
    }

    public void selectValueForSeatTypeField (String seatType) {
        Select valueOfSeatTypeField = new Select(cbx_seatTypeField);
        WebDriverUtils.waitForControl(cbx_seatTypeField);
        valueOfSeatTypeField.selectByVisibleText(seatType);
    }

    public void selectValueForTicketAmountField (String ticketAmount) {
        Select valueOfTicketAmountField = new Select(cbx_ticketAmountField);
        WebDriverUtils.waitForControl(cbx_ticketAmountField);
        valueOfTicketAmountField.selectByVisibleText(ticketAmount);
    }

    public void clickBookTicketButton () {
        WebDriverUtils.scrollTillElementVisible(btn_bookTicket);
        WebDriverUtils.waitForControlBeClickable(btn_bookTicket);
        btn_bookTicket.click();
    }

    public String getBookTicketErrorMessage () {
        WebDriverUtils.waitForControl(txt_BookTicketErrorMessage);
        return txt_BookTicketErrorMessage.getText();
    }

    public String getAmountErrorMessage () {
        WebDriverUtils.waitForControl(txt_AmountErrorMessage);
        return txt_AmountErrorMessage.getText();
    }

    public String getSelectedDepartDate () {
        WebDriverUtils.waitForControl(departDateFirstOption);
        return departDateFirstOption.getText();
    }

    public String getSelectedDepartStation () {
        WebDriverUtils.waitForControl(option_SelectedDepartStation);
        return option_SelectedDepartStation.getText();
    }

    public String getFirstDepartStationOption () {
        WebDriverUtils.waitForControl(departStationFirstOption);
        return departStationFirstOption.getText();
    }

    public String getSelectedArriveStation () {
        WebDriverUtils.waitForControl(option_SelectedArriveStation);
        return option_SelectedArriveStation.getText();
    }

    public String getFirstArriveStationOption () {
        WebDriverUtils.waitForControl(arriveStationFirstOption);
        return arriveStationFirstOption.getText();
    }

    public String getFirstSeatTypeOption () {
        WebDriverUtils.waitForControl(option_FirstSeatType);
        return option_FirstSeatType.getText();
    }

    public String getSelectedSeatType () {
        WebDriverUtils.waitForControl(option_SelectedSeatType);
        return option_SelectedSeatType.getText();
    }

    public String getSelectedTicketAmount () {
        WebDriverUtils.waitForControl(ticketAmountFirstOption);
        return ticketAmountFirstOption.getText();
    }

    public List<String> getArriveStationValue(String departStation)
    {
        selectValueForDepartFromField(departStation);
        sleep(2);
        List<String> arriveStationValue = new ArrayList<>();
        for(WebElement element : Utility.getDriver().findElements(By.xpath(String.format(xpath_ArriveStationValue, departStation))))
            arriveStationValue.add(element.getText());
        return arriveStationValue;
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

    public void checkBookTicketPageDisplayed(ExtentTest logStep) {
        try {
            log4j.info("checkBookTicketPageDisplayed method - Starts");
            TestReporter.logInfo(logStep, "checkBookTicketPageDisplayed ...");

            Assertion.checkControlExist(logStep, bookTicketPageHeader, "Book Ticket page header");
            Assertion.checkControlExist(logStep, cbx_departDateField, "Depart date filed");
            Assertion.checkControlExist(logStep, cbx_departFromField, "Depart station field");
            Assertion.checkControlExist(logStep, cbx_arriveAtField, "Arrive station field");
            Assertion.checkControlExist(logStep, cbx_seatTypeField, "Seat type field");
            Assertion.checkControlExist(logStep, cbx_ticketAmountField, "Ticket amount filed");
            Assertion.checkControlExist(logStep, btn_bookTicket, "Book Ticket button");

            log4j.info("checkBookTicketPageDisplayed method - Ends");
        } catch (Exception e) {
            log4j.error("checkBookTicketPageDisplayed method - ERROR: ", e);
            TestReporter.logException(logStep, "checkBookTicketPageDisplayed - ERROR", e);
        }
    }

    public void verifyDepartDateDisplay3To30daysAhead(ExtentTest logStep) {
        try {
            log4j.info("verifyDepartDateDisplay3To30daysAhead method - Starts");
            TestReporter.logInfo(logStep, "verifyDepartDateDisplay3To30daysAhead ...");

            String firstDate = departDateFirstOption.getText();
            String lastDate = departDateLastOption.getText();

            Assertion.verifyActualAndExpected(logStep, firstDate, CommonMethods.get3DaysAfter());
            Assertion.verifyActualAndExpected(logStep, lastDate, CommonMethods.get30DaysAfter());

            log4j.info("verifyDepartDateDisplay3To30daysAhead method - Ends");
        } catch (Exception e) {
            log4j.error("verifyDepartDateDisplay3To30daysAhead method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyDepartDateDisplay3To30daysAhead - ERROR", e);
        }
    }

    public void verifyAllSeatTypesDisplay(ExtentTest logStep, List<String> seatTypeList) {
        try {
            log4j.info("verifyAllSeatTypesDisplay method - Starts");
            TestReporter.logInfo(logStep, "verifyAllSeatTypesDisplay ...");

            for (int i=0; i<seatTypeList.size(); i++)
            {
                Assertion.verifyActualAndExpected(logStep, Utility.getDriver().findElement(By.xpath(String.format(seatTypeOptions, i+1))).getText(), seatTypeList.get(i));
            }

            log4j.info("verifyAllSeatTypesDisplay method - Ends");
        } catch (Exception e) {
            log4j.error("verifyAllSeatTypesDisplay method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyAllSeatTypesDisplay - ERROR", e);
        }
    }

    public void verifyTicketAmountDisplay1To10(ExtentTest logStep) {
        try {
            log4j.info("verifyTicketAmountDisplay1To10 method - Starts");
            TestReporter.logInfo(logStep, "verifyTicketAmountDisplay1To10 ...");

            String firstTicketAmountOption = ticketAmountFirstOption.getText();
            String lastTicketAmountOption = ticketAmountLastOption.getText();

            Assertion.verifyActualAndExpected(logStep, firstTicketAmountOption, "1");
            Assertion.verifyActualAndExpected(logStep, lastTicketAmountOption,"10");

            log4j.info("verifyTicketAmountDisplay1To10 method - Ends");
        } catch (Exception e) {
            log4j.error("verifyTicketAmountDisplay1To10 method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyTicketAmountDisplay1To10 - ERROR", e);
        }
    }

    public void bookTicket (ExtentTest logStep, String departDate, String departStation, String arriveStation, String seatType, String amount) {
        try {
            log4j.info("bookTicket method - Starts");
            TestReporter.logInfo(logStep, "bookTicket ...");
            WebDriverUtils.waitForPageLoaded();

            selectValueForDepartDateField(departDate);
            selectValueForDepartFromField(departStation);
            sleep(5);
            if(!arriveStation.equals(""))
            {
                selectValueForArriveAtField(arriveStation);
            }
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

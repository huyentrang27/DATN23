package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyTicketPage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement header;
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
    @FindBy(xpath = "//select[@name='FilterDpStation']")
    private WebElement cbx_departFrom;
    @FindBy(xpath = "//select[@name='FilterArStation']")
    private WebElement cbx_ArriveAt;
    @FindBy(xpath = "//input[@name='FilterDpDate']")
    private WebElement tbx_DepartDate;
    @FindBy(xpath = "//select[@name='FilterStatus']")
    private WebElement cbx_Status;
    @FindBy(xpath = "//div//input[@type='submit']")
    private WebElement button_ApplyFilter;
    @FindBy(xpath = "//div[@class = 'error message']")
    private WebElement filterErrorMessage;
    String xpath_CancelButton = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1][text()='%s']/..//td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1][text()='%s']/../td[count(//th[text()='Operation']/preceding-sibling::th) + 1]/descendant::input";
    String xpath_FilterResult = "//table[@class = 'MyTable']//td[count(//table[@class='MyTable']//th[text()='%s']/preceding-sibling::th)+1]";

    /**
     * Methods
     */
    public void inputDepartDate (String departDate) {
        WebDriverUtils.waitForControl(tbx_DepartDate);
        tbx_DepartDate.sendKeys(departDate);
    }

    public void selectDepartStation (String departStation) {
        Select valueOfDepartFromField = new Select(cbx_departFrom);
        WebDriverUtils.waitForControl(cbx_ArriveAt);
        valueOfDepartFromField.selectByVisibleText(departStation);
    }

    public void selectArriveStation (String arriveStation) {
        Select valueOfArriveAtField = new Select(cbx_ArriveAt);
        WebDriverUtils.waitForControl(cbx_ArriveAt);
        valueOfArriveAtField.selectByVisibleText(arriveStation);
    }

    public void selectStatus (String status) {
        Select valueOfSeatTypeField = new Select(cbx_Status);
        WebDriverUtils.waitForControl(cbx_Status);
        valueOfSeatTypeField.selectByVisibleText(status);
    }

    public void clickCancelButton(String departStation, String arriveStation) {
        WebElement cancelButton = WebDriverUtils.getDriver().findElement(By.xpath(String.format(xpath_CancelButton, departStation, arriveStation)));
        WebDriverUtils.scrollTillElementVisible(cancelButton);
        WebDriverUtils.waitForControlBeClickable(cancelButton);
        cancelButton.click();
    }

    public void clickApplyFilterButton() {
        WebDriverUtils.scrollTillElementVisible(button_ApplyFilter);
        WebDriverUtils.waitForControlBeClickable(button_ApplyFilter);
        button_ApplyFilter.click();
    }

    public List<String> getFilterResult(String criteria) {
        List<WebElement> results = WebDriverUtils.getDriver().findElements(By.xpath(String.format(xpath_FilterResult, criteria)));
        List<String> filterResults = new ArrayList<>();
        for(WebElement result: results)
        {
            filterResults.add(result.getText());
        }
        return filterResults;
    }

    public String getErrorMessage () {
        return filterErrorMessage.getText();
    }

    public Hashtable<String, String> ticketInformation() {
        Hashtable<String, String> ticketInfo = new Hashtable<String, String>();
        ticketInfo.put("DepartDate", departDate.getText());
        ticketInfo.put("DepartStation", departStation.getText());
        ticketInfo.put("ArriveStation", arriveStation.getText());
        ticketInfo.put("SeatType", seatType.getText());
        ticketInfo.put("TicketAmount", ticketAmount.getText());
        return ticketInfo;
    }

    public void acceptAlert() {
        Alert alert = Utility.getDriver().switchTo().alert();
        alert.accept();
    }

    public void cancelTicket(ExtentTest logStep, String departStation, String arriveStation) {
        try {
            log4j.info("cancelTicket method - Starts");
            TestReporter.logInfo(logStep, "Cancel ticket ...");

            clickCancelButton(departStation, arriveStation);
            acceptAlert();
            log4j.info("cancelTicket method - Ends");
        } catch (Exception e) {
            log4j.error("cancelTicket method - ERROR: ", e);
            TestReporter.logException(logStep, "cancelTicket - ERROR", e);
        }
    }

    public void filterTicket(ExtentTest logStep, String departStation, String arriveStation, String departDate, String status) {
        try {
            log4j.info("filterTicket method - Starts");
            TestReporter.logInfo(logStep, "Filter ticket ...");
            WebDriverUtils.waitForPageLoaded();

            if(!departDate.equals(""))
            {
                inputDepartDate(departDate);
            }
            if(!departStation.equals(""))
            {
                selectDepartStation(departStation);
            }
            if(!arriveStation.equals(""))
            {
                selectArriveStation(arriveStation);
            }
            if(!status.equals(""))
            {
                selectStatus(status);
            }

            clickApplyFilterButton();
            log4j.info("filterTicket method - Ends");
        } catch (Exception e) {
            log4j.error("filterTicket method - ERROR: ", e);
            TestReporter.logException(logStep, "filterTicket - ERROR", e);
        }
    }

    public void verifyFilterResultDisplayProperly(ExtentTest logStep, String departDate, String departStation, String arriveStation, String status)
    {
        try {
            log4j.info("verifyFilterResultDisplayProperly method - Starts");
            TestReporter.logInfo(logStep, "verifyFilterResultDisplayProperly ...");

            if(!departDate.equals(""))
                for(String result: getFilterResult("Depart Date"))
                    Assertion.verifyActualAndExpected(logStep, result, departDate);

            if(!departStation.equals(""))
                for(String result: getFilterResult("Depart Station"))
                    Assertion.verifyActualAndExpected(logStep, result, departStation);

            if(!arriveStation.equals(""))
                for(String result: getFilterResult("Arrive Station"))
                    Assertion.verifyActualAndExpected(logStep, result, arriveStation);

            if(!status.equals(""))
                for(String result: getFilterResult("Status"))
                    Assertion.verifyActualAndExpected(logStep, result, status);

            log4j.info("verifyFilterResultDisplayProperly method - Ends");
        } catch (Exception e) {
            log4j.error("verifyFilterResultDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logStep, "verifyFilterResultDisplayProperly - ERROR", e);
        }
    }

    public void verifyMyTicketPageDisplay(ExtentTest logStep, String departDate, String departStation, String arriveStation, String seatType, String ticketAmount) {
        try {
            log4j.info("verifyBookTicketSuccessfullyPageDisplay method - Starts");
            TestReporter.logInfo(logStep, "verifyBookTicketSuccessfullyPageDisplay ...");

            Assertion.checkControlExist(logStep, header, "Manage Tickets page");
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

    public boolean doesApplyFilterButtonExist() {
        return WebDriverUtils.doesControlExist(button_ApplyFilter);
    }

    public boolean doesCancelButtonExist(String departStation, String arriveStation) {
        WebElement cancelButton = WebDriverUtils.getDriver().findElement(By.xpath(String.format(xpath_CancelButton, departStation, arriveStation)));
        return WebDriverUtils.doesControlExist(cancelButton);
    }
}

package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class TimeTablePage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement header;

    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'No.')]")
    private WebElement column_No;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Depart Station')]")
    private WebElement column_DepartStation;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Arrive Station')]")
    private WebElement column_ArriveStation;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Depart Time')]")
    private WebElement column_DepartTime;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Arrive Time')]")
    private WebElement column_ArriveTime;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Check Price')]")
    private WebElement column_CheckPrice;
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th[contains(text(), 'Book ticket')]")
    private WebElement column_BookTicket;
    private String link_xpath = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1][text()='%s']/..//td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1][text()='%s']/../td[count(//th[text()='%s']/preceding-sibling::th) + 1]/descendant::a";

    private String xpath_ArriveStationValue = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1][text()='%s']/..//td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1]";

    /**
     * Methods
     */
    public void clickOnTheLink (String departStation, String arriveStation,String linkName) {
        WebElement link = WebDriverUtils.getDriver().findElement(By.xpath(String.format(link_xpath, departStation, arriveStation, linkName)));
        WebDriverUtils.scrollTillElementVisible(link);
        WebDriverUtils.waitForControlBeClickable(link);
        link.click();
    }

    public void checkPrice(ExtentTest logStep, String departStation, String arriveStation)
    {
        try{
            log4j.info("checkPrice method - Starts");
            TestReporter.logInfo(logStep, "Check Price ...");

            clickOnTheLink(departStation, arriveStation, "Check Price");
            log4j.info("checkPrice method - Ends");
        }catch (Exception e)
        {
            log4j.error("checkPrice method - ERROR: ", e);
            TestReporter.logException(logStep, "checkPrice - ERROR", e);
        }
    }

    public void clickOnBookTicketLink(ExtentTest logStep, String departStation, String arriveStation)
    {
        try{
            log4j.info("clickOnBookTicket method - Starts");
            TestReporter.logInfo(logStep, "Click On Book ticket link ...");

            clickOnTheLink(departStation, arriveStation, "Book ticket");
            log4j.info("clickOnBookTicket method - Ends");
        }catch (Exception e)
        {
            log4j.error("clickOnBookTicket method - ERROR: ", e);
            TestReporter.logException(logStep, "clickOnBookTicket - ERROR", e);
        }
    }

    public List<String> getArriveStationValue(String departStation)
    {
        List<String> arriveStationValue = new ArrayList<>();
        for(WebElement element : Utility.getDriver().findElements(By.xpath(String.format(xpath_ArriveStationValue, departStation))))
            arriveStationValue.add(element.getText());
        return arriveStationValue;
    }

    public void verifyTimetablePageDisplayProperly (ExtentTest logTest) {
        try{
            log4j.info("verifyTimetablePageDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Timetable page ...");

            WebDriverUtils.waitForPageLoaded();
            Assertion.checkControlExist(logTest, header, "Page header");
            Assertion.checkControlExist(logTest, column_No, "No column");
            Assertion.checkControlExist(logTest, column_DepartStation, "Depart Station column");
            Assertion.checkControlExist(logTest, column_ArriveStation, "Arrive station column");
            Assertion.checkControlExist(logTest, column_DepartTime, "Depart Time column");
            Assertion.checkControlExist(logTest, column_ArriveTime, "Arrive Time column");
            Assertion.checkControlExist(logTest, column_CheckPrice, "Check Price column");
            Assertion.checkControlExist(logTest, column_BookTicket, "Book Ticket column");


            log4j.info("verifyTimetablePageDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyTimetablePageDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify TImetable page - ERROR", e);
        }
    }
}

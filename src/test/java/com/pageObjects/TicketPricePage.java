package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TicketPricePage extends BasePage{
    /**
     * Elements
     */
    @FindBy(xpath = "//table//tr[@class = 'TableSmallHeader']/th")
    private WebElement header_TicketInformation;

    String subTrainsHeader = "//table//tr[contains(., 'Trains depart from %s')]";
    String button_CheckPrice = "//li[text() = '%s to %s']/ancestor::tr/descendant::a";
    String button_BookTicket = "//td[text() = '%s']/ancestor::tr/descendant::a";

    /**
     * Methods
     */
    public String getTicketInformationHeader () {
        return header_TicketInformation.getText();
    }

    public void verifyTrainTicketPricingListDisplayProperly(ExtentTest logTest, List<String> departStationList)
    {
        try{
            log4j.info("verifyTrainTicketPricingListDisplayProperly method - Starts");
            TestReporter.logInfo(logTest, "Verify Train ticket pricing list ...");

            WebDriverUtils.waitForPageLoaded();
            for(String departStation:departStationList)
            {
                Assertion.checkControlExist(logTest, Utility.getDriver().findElement(By.xpath(String.format(subTrainsHeader, departStation))), "Trains depart from "+departStation);
            }

            log4j.info("verifyTrainTicketPricingListDisplayProperly method - Ends");
        }catch (Exception e)
        {
            log4j.error("verifyTrainTicketPricingListDisplayProperly method - ERROR: ", e);
            TestReporter.logException(logTest, "Verify Train ticket pricing list - ERROR", e);
        }
    }

    public void checkPrice(ExtentTest logStep, String departStation, String arriveStation)
    {
        try{
            log4j.info("checkPrice method - Starts");
            TestReporter.logInfo(logStep, "Check Price ...");

            WebElement checkPriceButton = WebDriverUtils.getDriver().findElement(By.xpath(String.format(button_CheckPrice, departStation, arriveStation)));
            WebDriverUtils.scrollTillElementVisible(checkPriceButton);
            WebDriverUtils.waitForControlBeClickable(checkPriceButton);
            checkPriceButton.click();

            log4j.info("checkPrice method - Ends");
        }catch (Exception e)
        {
            log4j.error("checkPrice method - ERROR: ", e);
            TestReporter.logException(logStep, "checkPrice - ERROR", e);
        }
    }

    public void clickOnBookTicketButton(ExtentTest logStep, String seatType)
    {
        try{
            log4j.info("checkPrice method - Starts");
            TestReporter.logInfo(logStep, "Check Price ...");

            WebElement bookTicketButton = WebDriverUtils.getDriver().findElement(By.xpath(String.format(button_BookTicket, seatType)));
            WebDriverUtils.scrollTillElementVisible(bookTicketButton);
            WebDriverUtils.waitForControlBeClickable(bookTicketButton);
            bookTicketButton.click();

            log4j.info("checkPrice method - Ends");
        }catch (Exception e)
        {
            log4j.error("checkPrice method - ERROR: ", e);
            TestReporter.logException(logStep, "checkPrice - ERROR", e);
        }
    }
}

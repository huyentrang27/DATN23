package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TimeTablePage extends Utility {
    /**
     * Elements
     */
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1][text()='Sài Gòn']\" +\n" +
            "            \"/..//td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1][text()='Phan Thiết']\" +\n" +
            "            \"/../td[count(//th[text()='Check Price']/preceding-sibling::th) + 1]/descendant::a")
    private WebElement link_checkPrice;

    /**
     * Methods
     */
    public void clickCheckPriceButton () {
        WebDriverUtils.waitForControlBeClickable(link_checkPrice);
        link_checkPrice.click();
    }

    public void checkPrice(ExtentTest logStep)
    {
        try{
            log4j.info("checkPrice method - Starts");
            TestReporter.logInfo(logStep, "Check Price ...");

            clickCheckPriceButton();
            log4j.info("checkPrice method - Ends");
        }catch (Exception e)
        {
            log4j.error("checkPrice method - ERROR: ", e);
            TestReporter.logException(logStep, "checkPrice - ERROR", e);
        }
    }
}

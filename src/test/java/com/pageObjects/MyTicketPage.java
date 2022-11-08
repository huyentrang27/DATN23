package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyTicketPage extends Utility {
    /**
     * Elements
     */
    @FindBy(xpath = "//tr/td[count(//th[text()='Depart Station']/preceding-sibling::th) + 1][text()='Đà Nẵng']\" +\n" +
            "            \"/..//td[count(//th[text()='Arrive Station']/preceding-sibling::th) + 1][text()='Nha Trang']\" +\n" +
            "            \"/../td[count(//th[text()='Operation']/preceding-sibling::th) + 1]/descendant::input")
    private WebElement btn_cancel;

    /**
     * Methods
     */
    public void clickCancelButton(ExtentTest logStep)
    {
        WebDriverUtils.waitForControlBeClickable(btn_cancel);
        btn_cancel.click();
    }
    public void cancelTicket(ExtentTest logStep) {
        try{
            log4j.info("cancelTicket method - Starts");
            TestReporter.logInfo(logStep, "Cancel ticket ...");

            clickCancelButton(logStep);
            log4j.info("cancelTicket method - Ends");
        }
        catch (Exception e)
        {
            log4j.error("cancelTicket method - ERROR: ", e);
            TestReporter.logException(logStep, "cancelTicket - ERROR", e);
        }
    }
}

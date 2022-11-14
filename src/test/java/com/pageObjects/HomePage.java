package com.pageObjects;

import com.utility.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//div[@class='account']/descendant::strong")
    private WebElement welcomeGuestElement;

    /**
     * Methods
     */
    public String getWelcomeElement () {
        return welcomeGuestElement.getText();
    }
}

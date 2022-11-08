package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TicketPricePage {
    /**
     * Elements
     */
    @FindBy(xpath = "//table[@class='MyTable MedTable']//th[text()='Ticket price from Sài Gòn to Phan Thiết']")
    private WebElement header_ticketPricePage;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='HS']/preceding-sibling::td) + 1][text()='90000']")
    private WebElement hardSeat;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='SS']/preceding-sibling::td) + 1][text()='115000']")
    private WebElement softSeat;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='SSC']/preceding-sibling::td) + 1][text()='140000']")
    private WebElement softSeatWithAirConditioner;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='HB']/preceding-sibling::td) + 1][text()='190000']")
    private WebElement hardBed;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='SB']/preceding-sibling::td) + 1][text()='240000']")
    private WebElement softBed;
    @FindBy(xpath = "//table[@class='MyTable MedTable']//td[count(//td[text()='SBC']/preceding-sibling::td) + 1][text()='290000']")
    private WebElement softBedWithAirConditioner	;

    /**
     * Methods
     */
    public String getTextTitleOfTicketPrice () {
        return header_ticketPricePage.getText();
    }

    public String[] getTicketPrice () {
        String txt_hardSeat = hardSeat.getText();
        String txt_softSeat = softSeat.getText();
        String txt_softSeatWithAirConditioner = softSeatWithAirConditioner.getText();
        String txt_hardBed = hardBed.getText();
        String txt_softBed = softBed.getText();
        String txt_softBedWithAirConditioner = softBedWithAirConditioner.getText();
        String[] ticketPrice = {txt_hardSeat, txt_softSeat, txt_softSeatWithAirConditioner, txt_hardBed, txt_softBed, txt_softBedWithAirConditioner};
        return ticketPrice;
    }
}

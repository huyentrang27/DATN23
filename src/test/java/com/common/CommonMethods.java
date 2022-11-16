package com.common;

import com.aventstack.extentreports.ExtentTest;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPage;
import com.utility.DataFaker;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;

import static com.common.Constant.*;

public class CommonMethods {
    public static void registerAndGoToLoginPage(ExtentTest logStep, String emailAddress, String password, String PID){
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickOnRegisterTab();

        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, emailAddress, password, PID);
        registerPage.clickOnLoginTab();
    }
}

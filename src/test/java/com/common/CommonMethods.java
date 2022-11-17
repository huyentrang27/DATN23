package com.common;

import com.aventstack.extentreports.ExtentTest;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.RegisterPage;
import com.utility.*;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.common.Constant.*;
import static com.utility.Utility.log4j;

public class CommonMethods {
    public static void registerAndGoToLoginPage(ExtentTest logStep, String emailAddress, String password, String PID){
        try{
            log4j.info("registerAndGoToLoginPage - Starts");
            TestReporter.logInfo(logStep, "registerAndGoToLoginPage ...");

            HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
            homePage.clickOnRegisterTab();

            RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
            registerPage.register(logStep, emailAddress, password, PID);
            registerPage.clickOnLoginTab();
            log4j.info("registerAndGoToLoginPage method - Ends");
        }catch (Exception e)
        {
            log4j.error("registerAndGoToLoginPage method - ERROR: ", e);
            TestReporter.logException(logStep, "registerAndGoToLoginPage - ERROR", e);
        }
    }

    public static void login(ExtentTest logStep){
        try{
            String emailAddress = DataFaker.generateRandomEmail(EMAIL_ADDRESS);
            log4j.info("login - Starts");
            TestReporter.logInfo(logStep, "login ...");

            HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
            homePage.clickOnRegisterTab();

            RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
            registerPage.register(logStep, emailAddress, PASSWORD, PID);
            registerPage.clickOnLoginTab();

            LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
            loginPage.login(logStep, emailAddress, PASSWORD);

            log4j.info("login method - Ends");
        }catch (Exception e)
        {
            log4j.error("login method - ERROR: ", e);
            TestReporter.logException(logStep, "login - ERROR", e);
        }
    }

    public static String get3DaysAfter(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);

        Date currentDatePlusThree = c.getTime();
        return dateFormat.format(currentDatePlusThree);
    }

    public static String get30DaysAfter(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);

        Date currentDatePlusThirty = c.getTime();
        return dateFormat.format(currentDatePlusThirty);
    }

    public static String getSomeDaysAfter(int i){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);

        Date currentDatePlusThree = c.getTime();
        return dateFormat.format(currentDatePlusThree);
    }
}

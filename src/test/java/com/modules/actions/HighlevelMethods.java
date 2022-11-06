//package com.modules.actions;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.pageObjects.HomePage;
//import com.pageObjects.login_register.LoginPage;
//import com.utility.Utility;
//import org.openqa.selenium.support.PageFactory;
//
//public class HighlevelMethods {
//
//    public static void login(ExtentTest logStep){
//        String userName = System.getenv("userName");
//        String password = System.getenv("password");
//
//        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
//        homePage.open(logStep);
//        homePage.clickLoginLabel(logStep);
//        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
//        loginPage.login(logStep, userName, password);
//    }
//
//}

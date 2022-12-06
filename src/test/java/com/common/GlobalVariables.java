package com.common;

import com.utility.PropertiesUtils;

public class GlobalVariables {

    //project path
    public static String PROJECT_PATH = System.getProperty("user.dir");
    public static String JSON_FILE_PATH = PROJECT_PATH + "/src/test/java/";
    public static String OUTPUT_PATH =  PROJECT_PATH + "/resources/output/";

    //Run parameters
    public static String THREAD_COUNT = PropertiesUtils.getPropValue("threadCount");
    public static String BROWSER = PropertiesUtils.getPropValue("browserName");

    //Defaul wait time
    public static final int WAIT_TIME_60 = 60;

    //Report data
    public static int TOTAL_TESTCASES = 0;
    public static int TOTAL_EXECUTED = 0;
    public static int TOTAL_PASSED = 0;
    public static int TOTAL_FAILED = 0;
    public static int TOTAL_SKIPPED = 0;

    //URL path
    public static final String RAILWAY_URL = "http://www.raillog.somee.com";
    public static final String RAILWAY_HOMEPAGE_URL = "http://www.raillog.somee.com/Page/HomePage.cshtml";
    //data test
    public static String EMAIL_ADDRESS = "tnguyen@gmail.com";
    public static String PASSWORD = "Test@123456";
    public static String PID = "123456789";
}

package com.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFaker {

    public static String generateRandomEmail(String email){
        if(email.contains("@")){
            String[] parts = email.split("@");
            return parts[0] + "+" + generatetimeStampString("HHmmssSSS")+ "@" + parts[1];
        }
        else {
            throw new IllegalArgumentException("The string email does't contain @");
        }
    }

    public static String generatetimeStampString(String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return df.format(now);
    }

    public static String generateUserName(){
        return "test_" + generatetimeStampString("MMddHHmmssSSS");
    }

}

package com.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFaker {

    public static String generateRandomEmail(String email){
        if(email.contains("@")){
            String[] parts = email.split("@");
            return parts[0] + "+" + generateTimeStampString("HHmmssSSS")+ "@" + parts[1];
        }
        else {
            throw new IllegalArgumentException("The string email doesn't contain @");
        }
    }

    public static String generateTimeStampString(String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return df.format(now);
    }

}

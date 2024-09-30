package com.project.Banking.Application.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_CREATED = "002";

    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already exists";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account created successfully";

    public static String generateAccountNumber(){
        /*
         * year + randomSixDigits
         */

        String year = String.valueOf(Year.now());
        int min = 100000;
        int max = 999999;

        /*generate a random number between min and max;*/
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        String randomNumber = String.valueOf(random);

        return year + randomNumber;
    }
}

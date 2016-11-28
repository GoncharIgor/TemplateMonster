package com.templatemonster.demo.util;

import org.apache.commons.lang3.RandomStringUtils;

public class MailGenerator {

    public static String generateNewEmail() {
        return generateAlphaNumericString(15) + "@com.com";
    }

    private static String generateAlphaNumericString(int textLength){
        return RandomStringUtils.randomAlphanumeric(textLength);
    }
}

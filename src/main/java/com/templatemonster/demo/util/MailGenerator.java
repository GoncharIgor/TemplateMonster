package com.templatemonster.demo.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class MailGenerator {

    public static String generateNewEmail() {
        return generateAlphaNumericString(15) + "@com.com";
    }

    private static String generateAlphaNumericString(int textLength){
        return RandomStringUtils.randomAlphanumeric(textLength);
    }
}

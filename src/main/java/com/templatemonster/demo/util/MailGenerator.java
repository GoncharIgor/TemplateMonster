package com.templatemonster.demo.util;

import java.util.Random;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class MailGenerator {

    public static String generateNewEmail() {
        return generateRandomText(15) + "@com.com";
    }

    private static String generateRandomText(int textLength) {
        String characters;
        StringBuilder sb = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();
        Random random = new Random();

        for (char ch = 'a'; ch < 'z'; ch++) {
            sb.append(ch);
        }
        for (char ch = '1'; ch < '9'; ch++) {
            sb.append(ch);
        }
        characters = sb.toString();
        char[] charsArray = new char[textLength];

        for (int i = 0; i < textLength; i++) {
            charsArray[i] = characters.charAt(random.nextInt(characters.length()));
        }
        String result = new String(charsArray);
        return result;
    }
}

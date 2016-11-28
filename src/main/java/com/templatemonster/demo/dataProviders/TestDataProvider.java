package com.templatemonster.demo.dataProviders;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "templates_examples")
    public static Object[][] getTemplateExamples() {
        return new Object[][]{
                {"53590"},
                {"53088"}
        };
    }

    @DataProvider(name = "invalid_user_emails")
    public static Object[][] getInvalidUserEmails() {
        return new Object[][]{
                {""},
                {"aaaaaa"},
                {"aaaaa@"},
                {"aaaaa@com"},
                {"aaaaa@.com"},
                {"@.com"},
                {"@com"},
                {"!@#$%^&"},
                {"aaaaa@com.!#$%"},
                {"22222@222"},
        };
    }
}

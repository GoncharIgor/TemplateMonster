package com.templatemonster.demo.dataProviders;

import com.templatemonster.demo.util.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.*;

public class TestDataProvider {
    public static final String TM_001_LOGIN_POSITIVE_TEST = "tm_001_login_positive_test";
    public static final String TM_002_LOGIN_NEGATIVE_TEST = "tm_002_login_negative_test";
    public static final String TM_010_INVALID_EMAIL_CHECKOUT_TEST = "tm_010_invalid_email_checkout_test";
    public static final String TM_018_CHANGE_LOCALIZATION_TEST = "tm_018_change_localization_test";

    @DataProvider(name = "templates_examples")
    public static Object[][] getTemplateExamples() {

        return new Object[][]{
                {"53590"},
                {"53088"}
        };
    }

    @DataProvider(name = TM_001_LOGIN_POSITIVE_TEST)
    private static Object[][] tm_001_login_positive_test() {
        ExcelUtils excelUtils = new ExcelUtils("./src/test/resources/testData/general.xls");
        List<String> tm_001_testData = excelUtils.getDataFromOneRowInExcelFile(1);

        return new Object[][]{{tm_001_testData}};
    }

    @DataProvider(name = TM_002_LOGIN_NEGATIVE_TEST)
    private static Object[][] tm_002_login_negative_test() {
        ExcelUtils excelUtils = new ExcelUtils("./src/test/resources/testData/general.xls");
        List<String> tm_002_testData = excelUtils.getDataFromOneRowInExcelFile(2);

        return new Object[][]{{tm_002_testData}};
    }

    @DataProvider(name = TM_010_INVALID_EMAIL_CHECKOUT_TEST)
    private static Object[][] getInvalidUserEmails() {
        List<String> invalidEmails = new ArrayList<>(Arrays.asList(
                "aaaaaa",
                "aaaaa@",
                "aaaaa@com",
                "aaaaa@.com",
                "@.com",
                "@com",
                "!@#$%^&",
                "aaaaa@com.!#$%",
                "22222@222"));

        return new Object[][]{{invalidEmails}};
    }

    @DataProvider(name = TM_018_CHANGE_LOCALIZATION_TEST)
    private static Object[][] tm_018_change_localization_test() {
        List<String> localizationsList = new ArrayList<>(Arrays.asList("US", "ES", "RU", "DE", "PL", "IT", "TR", "FR", "BR", "NL", "CN", "CZ", "UA", "HU"));
        Map<String, String> lozalizationMap = new HashMap<>();

        return new Object[][]{{localizationsList}};
    }
}

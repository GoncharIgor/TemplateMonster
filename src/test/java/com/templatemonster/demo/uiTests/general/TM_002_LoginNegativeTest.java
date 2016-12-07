package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.dataProviders.TestDataProvider;
import com.templatemonster.demo.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Тестовый сценарий:
 * 1. Открыть форму Sign in в хедере
 * 2. Ввести невалидный имейл
 * 3. Нажать Enter/кнопку Sign in
 * <p>
 * Ожидаемый результат:
 * 1. Пользователь не авторизован
 * 2. Появляется сообщение об ошибке
 */

public class TM_002_LoginNegativeTest extends TemplateMonsterBaseTest {
    private LoginPage loginPage;

    @Test(groups = {"userLoginTests"}, dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_002_LOGIN_NEGATIVE_TEST)
    public void userCannotLoginWithInvalidEmail(List<String> testData) {
        loginPage = openLoginPage()
                .fillLoginFormWithAnyCredentials(testData.get(2), testData.get(1))
                .clickSubmitButton()
                .isLoginErrorLabelDisplayed(true);

        assertEquals(loginPage.getValueOfCookie("wac"), "cookie not found", "'wac' cookie was created");
    }


    /**
     * Тестовый сценарий:
     * 1. Открыть форму Sign in в хедере
     * 2. Ввести неправильный пароль
     * 3. Нажать Enter/кнопку Sign in
     * <p>
     * Ожидаемый результат:
     * 1. Пользователь не авторизован
     * 2. Появляется сообщение об ошибке
     */

    @Test(groups = {"userLoginTests"}, dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_002_LOGIN_NEGATIVE_TEST)
    public void userCannotLoginWithInvalidPassword(List<String> testData) {
        loginPage = openLoginPage()
                .fillLoginFormWithAnyCredentials(testData.get(0), testData.get(3))
                .clickSubmitButton()
                .isLoginErrorLabelDisplayed(true);

        assertEquals(loginPage.getValueOfCookie("wac"), "cookie not found", "'wac' cookie was created");
    }

    /**
     * Тестовый сценарий:
     * 1. Открыть форму Sign in в хедере
     * 2. Ввести неправильный имейл и пароль
     * 3. Нажать Enter/кнопку Sign in
     * <p>
     * Ожидаемый результат:
     * 1. Пользователь не авторизован
     * 2. Появляется сообщение об ошибке
     */

    @Test(groups = {"userLoginTests"}, dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_002_LOGIN_NEGATIVE_TEST)
    public void userCannotLoginWithInvalidEmailAndPassword(List<String> testData) {
        loginPage = openLoginPage()
                .fillLoginFormWithAnyCredentials(testData.get(2), testData.get(3))
                .clickSubmitButton()
                .isLoginErrorLabelDisplayed(true);

        assertEquals(loginPage.getValueOfCookie("wac"), "cookie not found", "'wac' cookie was created");
    }
}

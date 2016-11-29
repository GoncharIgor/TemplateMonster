package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.LoginPage;
import org.testng.annotations.Test;

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

    @Test(groups = {"userLoginTests"})
    public void userCannotLoginWithInvalidEmail() {
        //Test data
        String invalidUserLogin = propertyManager.getProperty("invalidUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Test steps
        loginPage = openHomePage()
                .navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(invalidUserLogin, validUserPassword)
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

    @Test(groups = {"userLoginTests"})
    public void userCannotLoginWithInvalidPassword() {
        //Test data
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String invalidUserPassword = propertyManager.getProperty("invalidUserPassword");

        //Test steps
        loginPage = openHomePage()
                .navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(validUserLogin, invalidUserPassword)
                .clickSubmitButton()
                .isLoginErrorLabelDisplayed(true);

        assertEquals(loginPage.getValueOfCookie("wac"), "cookie not found", "'wac' cookie was created");
    }
}

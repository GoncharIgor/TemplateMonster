package com.templatemonster.demo;

import com.templatemonster.demo.pages.HomePage;
import com.templatemonster.demo.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Тестовый сценарий:
 * 1. Открыть форму Sign in в хедере
 * 2. Заполнить поля формы данными существующего пользователя
 * 3. Нажать Enter/кнопку Sign in
 * <p>
 * Ожидаемый результат:
 * 1. Пользователь успешно авторизован
 * 2. Установлена кука wac (значение =1)
 */


public class TM_001_loginTest extends BaseTest {
    private HomePage homePage;

    @Test
    public void tm_001_loginTest() {
        //Test data
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Test steps
        homePage = openHomePage()
                .navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(validUserLogin, validUserPassword)
                .submitLoginForm();
        assertTrue(homePage.isUserLoggedIn(), "User is not logged into the system");
        assertEquals(homePage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
    }
}

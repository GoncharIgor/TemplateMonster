package com.templatemonster.demo;

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
    private LoginPage loginPage;

    @Test(groups = {"checkintest", "functionalTests"})
    public void tm_001_loginTest() {
        //Test data
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Test steps
        driver.manage().deleteAllCookies();
        homePage = basePage.navigateToHomePage();
        loginPage = homePage.navigateToLoginPage();
        assertTrue(loginPage.isLoginPageOpened(), "Login page was not opened");
        loginPage = loginPage.fillLoginFormWithAnyCredentials(validUserLogin, validUserPassword);
        homePage = loginPage.submitLoginForm();
        assertTrue(basePage.isUserLoggedIn(), "User is not logged into the system");
        assertEquals(basePage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
    }
}

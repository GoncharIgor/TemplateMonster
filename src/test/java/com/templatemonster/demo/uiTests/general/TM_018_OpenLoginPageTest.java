package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Тестовый сценарий:
 * 1. Открыть форму Sign in в хедере
 * <p>
 * Ожидаемый результат:
 * 1. Открывается страница с логин формой
 */
public class TM_018_OpenLoginPageTest extends TemplateMonsterBaseTest {
    private LoginPage loginPage;

    @Test
    public void opensLoginPageByClickingAccountButton() {
        loginPage = openHomePage()
                .navigateToLoginPage();

        assertTrue(loginPage.isLoginPageOpened(), "Login page was not opened");
    }

    @Test
    public void openLoginPageAndCheckItsInitialStatus() {
        loginPage = openLoginPage()
                .checkInitialStateOfLoginPage();
    }

}

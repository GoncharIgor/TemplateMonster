package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Test case:
 * 1. Open 'Sign in' form header
 * <p>
 * Expected result:
 * 1. Page with login form is opened
 */
public class TM_004_OpenLoginPageTest extends TemplateMonsterBaseTest {
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

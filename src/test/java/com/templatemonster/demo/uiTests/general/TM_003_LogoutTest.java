package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Test case:
 * Предусловие: юзер залогинен в систему
 * 1. Юзер жмет на аватар аккаунта > Sign out
 * <p>
 * Expected result:
 * 1. Пользователь вылогинен из системы
 */

public class TM_003_LogoutTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test(groups = {"userLoginTests"})
    public void userCanLogoutFromSystemAfterLogin() {
        //Test data
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Test steps
        homePage = loginToSystem(validUserLogin, validUserPassword);
        assertTrue(homePage.isUserLoggedIn(), "User is not logged into the system");

        homePage.logoutFromSystem();
        assertFalse(homePage.isUserLoggedIn(), "User is logged into the system");
    }
}

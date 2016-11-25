package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.LoginPage;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by i.gonchar on 25.11.2016.
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

package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.UserRegistrationPage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by i.gonchar on 28.11.2016.
 */
public class TM_020_UserRegistrationTest extends TemplateMonsterBaseTest {
    private UserRegistrationPage userRegistrationPage;

    @Test(groups = {"userLoginTests"})
    public void openUserRegistrationPageByLink() {
        userRegistrationPage = openHomePage()
                .navigateToLoginPage()
                .navigateToUserRegistrationPage();

        assertTrue(userRegistrationPage.isUserRegistrationPageOpened(), "User registration page was not opened");

    }

    @Test(groups = {"userLoginTests"})
    public void canNotRegisterWithAlreadyExistingEmail() {
        //Test data
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");
        By emailErrorLocator = By.id("emailErrors");

        //Test steps
        userRegistrationPage = openUserRegistrationPage();
        assertTrue(userRegistrationPage.doesElementHasExpectedText(emailErrorLocator, ""));

        userRegistrationPage.fillUserRegistrationFormWithAnyCredentials(validUserLogin, validUserPassword);
        assertTrue(userRegistrationPage.doesElementHasExpectedText(emailErrorLocator, "Account with such e-mail address already exists."));

    }
}

package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.UserRegistrationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by i.gonchar on 28.11.2016.
 */
public class TM_019_UserRegistrationTest extends TemplateMonsterBaseTest {
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
        userRegistrationPage = openHomePage()
                .navigateToLoginPage()
                .navigateToUserRegistrationPage();

    }
}

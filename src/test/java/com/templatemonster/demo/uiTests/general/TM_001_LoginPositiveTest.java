package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.dataProviders.TestDataProvider;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test case:
 * 1. Open 'Sign in' form header
 * 2. Fill 'Sign in' form with existing user data
 * 3. Click Enter/[Sign in] button
 * <p>
 * Expected result:
 * 1. User is successfully authorized
 * 2. Cookie 'wac' is set with value =1
 */

public class TM_001_LoginPositiveTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test(groups = {"userLoginTests"}, dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_001_LOGIN_POSITIVE_TEST)
    public void userCanLoginWithValidEmailAndPassword(List<String> testData) {
        homePage = openHomePage()
                .navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(testData.get(0), testData.get(1))
                .submitLoginForm();

        assertTrue(homePage.isUserLoggedIn(), "User is not logged into the system");
        assertEquals(homePage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
    }
}

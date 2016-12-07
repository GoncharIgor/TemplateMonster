package com.templatemonster.demo.uiTests;


import static org.testng.Assert.assertTrue;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.dataProviders.TestDataProvider;
import com.templatemonster.demo.pages.CheckoutPage;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test case:
 * 1. Добавить в корзину платный шаблон и перейти на чекаут
 * 2. Enter invalid email (min. 10 variants)
 * <p>
 * Expected result:
 * Появляется сообщение о ошибке “Please specify a valid email”
 */
public class TM_010_InvalidEmailCheckoutTest extends TemplateMonsterBaseTest {
    private CheckoutPage checkoutPage;

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_010_INVALID_EMAIL_CHECKOUT_TEST)
    public void incorrectEmailsAreNorValidatedOnCheckoutPage(List<String> invalidEmails) {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");

        //Test steps
        checkoutPage = openHomePage()
                .searchForTemplate(templateId)
                .addOpenedTemplateToCartAndCheckout();
        assertTrue(checkoutPage.addInvalidEmailsToCheckout(invalidEmails), "Not all emails were correctly validated");
    }
}

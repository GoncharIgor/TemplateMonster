package com.templatemonster.demo.uiTests;


import static org.testng.Assert.assertTrue;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.CheckoutPage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * 1. Добавить в корзину платный шаблон и перейти на чекаут
 * 2. Ввести невалидный email (мин. 10 вариантов)
 * <p>
 * Ожидаемый результат:
 * Появляется сообщение о ошибке “Please specify a valid email”
 */
public class TM_010_InvalidEmailCheckoutTest extends TemplateMonsterBaseTest {
    private CheckoutPage checkoutPage;

    @Test
    public void tm_010_InvalidEmailCheckoutTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String[] invalidEmails = new String[10];
        for (int i = 0; i < 10; i++) {
            String invalidEmail = propertyManager.getProperty("invalidUserEmail" + i);
            invalidEmails[i] = invalidEmail;
        }

        //Test steps
        checkoutPage = openHomePage()
                .searchForTemplate(templateId)
                .addOpenedTemplateToCartAndCheckout();
        assertTrue(checkoutPage.addInvalidEmailsToCheckout(invalidEmails), "Not all emails were correctly validated");
    }
}

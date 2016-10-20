package com.templatemonster.demo;


import com.templatemonster.demo.dataProviders.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * 1. Добавить в корзину платный шаблон и перейти на чекаут
 * 2. Ввести невалидный email (мин. 10 вариантов)
 * <p>
 * Ожидаемый результат:
 * Появляется сообщение о ошибке “Please specify a valid email”
 */
public class TM_010_InvalidEmailCheckoutTest extends BaseTest {

    @Test
    public void tm_010_InvalidEmailCheckoutTest() throws InterruptedException {
        String templateId = propertyManager.getProperty("environmentalThemeID");

        String[] invalidEmails = new String[10];

        for (int i = 0; i < 10; i++) {
            String invalidEmail = propertyManager.getProperty("invalidUserEmail" + i);
            invalidEmails[i] = invalidEmail;
        }

        homePage = basePage.navigateToHomePage();
        templateSearchResultPage = homePage.searchForTemplate(templateId);
        Assert.assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        checkoutPage = templateSearchResultPage.addOpenedTemplateToCartAndCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page was not opened");
        Assert.assertTrue(checkoutPage.addInvalidEmailsToCheckout(invalidEmails), "Not all emails were correctly validated");
    }
}

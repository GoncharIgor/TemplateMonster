package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.CardPaymentPage;
import com.templatemonster.demo.pages.CheckoutPage;
import com.templatemonster.demo.util.WaitHelper;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * 1. Добавить в корзину платный шаблони перейти на чекаут
 * 2. На степе 1 чекаута авторизоваться существующим пользователем
 * 3. На степе 3 чекаута выполнить покупку через TransactPro (Pay by CARD)
 * <p>
 * Ожидаемый результат:
 * 1. Пользователь залогинен на степе 3)
 * 2. Открывается страница платёжки
 */
public class TM_004_BuyTemplateWithCardTest extends TemplateMonsterBaseTest {
    private CheckoutPage checkoutPage;
    private CardPaymentPage cardPaymentPage;

    @Test
    public void tm_004_BuyTemplateWithTransactProTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Test steps
        checkoutPage = openHomePage()
                .searchForTemplate(templateId)
                .addOpenedTemplateToCartAndCheckout()
                .checkoutUserAuthorization(validUserLogin, validUserPassword);
        WaitHelper.waitAdditional(5);
        assertEquals(checkoutPage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
        cardPaymentPage = checkoutPage.payCheckoutViaCard();
        assertTrue(cardPaymentPage.isCardpaymentPageOpened(), "CardPayment page was not opened");
    }
}

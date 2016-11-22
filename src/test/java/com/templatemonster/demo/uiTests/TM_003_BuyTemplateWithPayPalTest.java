package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.CheckoutPage;
import com.templatemonster.demo.pages.PayPalPage;
import com.templatemonster.demo.util.MailGenerator;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


/**
 * Тестовый сценарий:
 * 1. Добавить в корзину платный шаблон и перейти на чекаут
 * 2. Заполнить корректными данными форму Billing Details (емейл новый - регистрация нового пользователя) и нажать Enter/кнопку Continue to Payment
 * 4. На степе 3 чекаута выполнить покупку с помощью PayPal
 * <p>
 * Ожидаемый результат:
 * 1. Создаётся новый аккаунт (пользователь залогинен на степе 3)
 * 2. Открывается страница платёжки
 */
public class TM_003_BuyTemplateWithPayPalTest extends TemplateMonsterBaseTest {
    private CheckoutPage checkoutPage;
    private PayPalPage payPalPage;
    private String generatedUniqueEmail = MailGenerator.generateNewEmail();

    @Test
    public void tm_003_BuyTemplateWithPayPalTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String userName = propertyManager.getProperty("userName");
        String userSurname = propertyManager.getProperty("userSurname");
        String userPhoneNumber = propertyManager.getProperty("userPhoneNumber");
        String userZipCode = propertyManager.getProperty("userZipCode");

        //Test steps
        checkoutPage = openHomePage()
                .searchForTemplate(templateId)
                .addOpenedTemplateToCartAndCheckout();
        assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page was not opened");

        payPalPage = checkoutPage.addEmailToCheckout(generatedUniqueEmail)
                .addValidBillingInformation(userName, userSurname, userPhoneNumber, userZipCode)
                .payCheckoutViaPayPal();
        assertTrue(payPalPage.isPayPalPageOpened(), "PayPal page was not opened");
    }
}

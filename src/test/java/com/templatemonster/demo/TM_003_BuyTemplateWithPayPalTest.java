package com.templatemonster.demo;

import com.templatemonster.demo.util.MailGenerator;
import org.junit.Test;
import org.testng.Assert;


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
public class TM_003_BuyTemplateWithPayPalTest extends BaseTest {
    private String generatedUniqueEmail = MailGenerator.generateNewEmail();

    @Test
    public void tm_003_BuyTemplateWithPayPalTest() throws InterruptedException {
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String userName = propertyManager.getProperty("userName");
        String userSurname = propertyManager.getProperty("userSurname");
        String userPhoneNumber = propertyManager.getProperty("userPhoneNumber");
        String userZipCode = propertyManager.getProperty("userZipCode");

        driver.manage().deleteAllCookies();
        homePage = basePage.navigateToHomePage();
        templateSearchResultPage = homePage.searchForTemplate(templateId);
        Assert.assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        checkoutPage = templateSearchResultPage.addOpenedTemplateToCartAndCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page was not opened");
        checkoutPage = checkoutPage.addEmailToCheckout(generatedUniqueEmail);
        checkoutPage = checkoutPage.addValidBillingInformation(userName, userSurname, userPhoneNumber, userZipCode);
        payPalPage = checkoutPage.payCheckoutViaPayPal();
        Assert.assertTrue(payPalPage.isPayPalPageOpened(), "PayPal page was not opened");
    }
}

package com.templatemonster.demo;

import com.templatemonster.demo.util.WaitHelper;
import org.testng.Assert;
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
public class TM_004_BuyTemplateWithCardTest extends BaseTest {

    @Test
    public void tm_004_BuyTemplateWithTransactProTest() throws InterruptedException {
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        driver.manage().deleteAllCookies();
        homePage = basePage.navigateToHomePage();
        templateSearchResultPage = homePage.searchForTemplate(templateId);
        Assert.assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        checkoutPage = templateSearchResultPage.addOpenedTemplateToCartAndCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page was not opened");
        checkoutPage = checkoutPage.checkoutUserAuthorization(validUserLogin, validUserPassword);
        WaitHelper.waitAdditional(5);
        Assert.assertEquals(basePage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
        cardPaymentPage = checkoutPage.payCheckoutViaCard();
        Assert.assertTrue(cardPaymentPage.isCardpaymentPageOpened(), "CardPayment page was not opened");
    }
}

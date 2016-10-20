package com.templatemonster.demo;


import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

/**
 * Тестовый сценарий:
 * 1. Добавить в корзину платный шаблон и перейти на чекаут
 * 2. Ввести невалидный email (мин. 10 вариантов)
 * <p>
 * Ожидаемый результат:
 * Появляется сообщение о ошибке “Please specify a valid email”
 */
public class TM_010_InvalidEmailCheckoutTest extends BaseTest {

    List<String> invalidEmails = new ArrayList<String>() {{
        add("");
        add("aaaaaa");
        add("aaaaa@");
        add("aaaaa@com");
        add("aaaaa@.com");
    }};
    String templateId = "52293";

    @Test
    public void tm_010_InvalidEmailCheckoutTest() throws InterruptedException {
        templateSearchResultPage = onHomePage()
                .searchForTemplate(templateId);

        assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");

        checkoutPage = templateSearchResultPage.addOpenedTemplateToCartAndCheckout();

        assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page was not opened");
        assertTrue(checkoutPage.addInvalidEmailsToCheckout(invalidEmails), "Not all emails were correctly validated");
    }
}

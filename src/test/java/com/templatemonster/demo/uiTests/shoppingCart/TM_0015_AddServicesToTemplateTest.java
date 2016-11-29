package com.templatemonster.demo.uiTests.shoppingCart;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * Предусловие: корзина пустая
 * 1. Незалогиненным пользователем добавить шаблон в корзину
 * 2. Добавить 2 разных сервиса
 * <p>
 * Ожидаемый результат:
 * 1. Общая сумма меняется в зависимости от стоимости сервисов
 */
public class TM_0015_AddServicesToTemplateTest extends TemplateMonsterBaseTest {
    private ShoppingCartPage shoppingCartPage;
    private TemplateSearchResultPage templateSearchResultPage;

    @Test
    public void tm_0015_AddServicesToTemplate() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");
        int environmentalThemePrice = Integer.parseInt(propertyManager.getProperty("environmentalThemePrice"));

        //Test steps
        templateSearchResultPage = new TemplateSearchResultPage(driver);
        int servicePrice = openHomePage()
                .searchForTemplate(templateId)
                .addServiceToTemplate(2)
                .getServicePrice("2");

        shoppingCartPage = templateSearchResultPage.addTemplateToCartWithoutCheckout()
                .navigateToShoppingCart()
                .checkOrderTotalProce(environmentalThemePrice + servicePrice);

    }
}

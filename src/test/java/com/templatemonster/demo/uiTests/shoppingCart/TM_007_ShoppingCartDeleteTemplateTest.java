package com.templatemonster.demo.uiTests.shoppingCart;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * Предусловие: в корзине пользователя хранятся продукты
 * 1. Незалогиненным пользователем положить в корзину продукты
 * 2. Авторизоваться на степе 1 чекаута
 * 3. Перейти на страницу шоппинг карты и удалить некоторые продукты из корзины (нажать крестик возле выбранного продукта)
 * <p>
 * Ожидаемый результат:
 * 1. В блоке Cart Summary удаляются соответствующие продукты
 * 2. Subtotal: и Order Total: пересчитываются с вычетом стоимости удалённых продуктов
 */
public class TM_007_ShoppingCartDeleteTemplateTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;

    @Test
    public void tm_007_ShoppingCartDeleteTemplateTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");

        //Precondition
        templateSearchResultPage = openHomePage()
                .searchForTemplate(templateId)
                .addTemplateToCartWithoutCheckout();
        templateSearchResultPage.checkCartCount(1);

        //Test steps
    }
}

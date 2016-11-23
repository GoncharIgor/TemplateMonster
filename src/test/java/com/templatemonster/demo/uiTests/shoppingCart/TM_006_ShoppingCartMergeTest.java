package com.templatemonster.demo.uiTests.shoppingCart;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * Предусловие: в корзине пользователя хранятся продукты (проверка мерджа корзин)
 * 1. Незалогиненным пользователем положить в корзину продукты
 * 2. Авторизоваться через хедер (форма Sign in)
 * 3. Перейти на страницу шоппинг карты
 * <p>
 * Ожидаемый результат:
 * 1. При логине каунтер корзины показывает правильное количество продуктов (пересчитывается с учётом продуктов, хранившихсчя в корзине)
 * 2. На странице шоппинг карты в блоке Cart Summary отображаются все продукты (те, что были добавленны ранее + положенные до авторизации)
 * 3. Subtotal: и Order Total: пересчитываются с учётом цен всех продуктов в корзине
 */
public class TM_006_ShoppingCartMergeTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;
    private ShoppingCartPage shoppingCartPage;

    @Test
    public void tm_006_ShoppingCartMergeTest() throws InterruptedException {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");
        String templateId2 = propertyManager.getProperty("danceSchoolThemeID");
        String validUserLogin = propertyManager.getProperty("validUserLogin");
        String validUserPassword = propertyManager.getProperty("validUserPassword");

        //Precondition
        templateSearchResultPage = openHomePage()
                .searchForTemplate(templateId)
                .addToCartWithoutCheckout();
        templateSearchResultPage.checkCartCount(1);

        //Test steps
        templateSearchResultPage.searchForTemplate(templateId2)
                .addToCartWithoutCheckout();
        templateSearchResultPage.checkCartCount(2);

        shoppingCartPage = templateSearchResultPage.navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(validUserLogin, validUserPassword)
                .submitLoginForm()
                .openShoppingCart();
    }
}

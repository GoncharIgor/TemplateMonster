package com.templatemonster.demo.uiTests.shoppingCart;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import com.templatemonster.demo.util.WaitHelper;
import org.testng.annotations.Test;

/**
 * Test case:
 * Precondition: Chart has some products
 * 1. Незалогиненным пользователем положить в корзину продукты
 * 2. Авторизоваться через хедер (форма Sign in)
 * 3. Перейти на страницу шоппинг карты
 * <p>
 * Expected result:
 * 1. При логине каунтер корзины показывает правильное количество продуктов (пересчитывается с учётом продуктов, хранившихсчя в корзине)
 * 2. На странице шоппинг карты в блоке Cart Summary отображаются все продукты (те, что были добавленны ранее + положенные до авторизации)
 * 3. Subtotal: и Order Total: пересчитываются с учётом цен всех продуктов в корзине
 */
public class TM_006_ShoppingCartMergeTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;
    private HomePage homePage;
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
                .addTemplateToCartWithoutCheckout();
        templateSearchResultPage.checkCartCount(1);

        //Test steps
        templateSearchResultPage.searchForTemplate(templateId2)
                .addTemplateToCartWithoutCheckout();
        templateSearchResultPage.checkCartCount(2);

        homePage = templateSearchResultPage.navigateToLoginPage()
                .fillLoginFormWithAnyCredentials(validUserLogin, validUserPassword)
                .submitLoginForm();

        homePage.checkCartCount(2);

        //TODO check and debug
        //After steps
        shoppingCartPage = homePage.navigateToShoppingCart()
                .deleteAllTemplatesFromCart();

        WaitHelper.waitAdditional(5000);
    }
}

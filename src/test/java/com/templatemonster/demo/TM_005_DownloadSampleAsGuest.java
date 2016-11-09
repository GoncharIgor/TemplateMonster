package com.templatemonster.demo;

import com.templatemonster.demo.pages.HomePage;
import com.templatemonster.demo.pages.TemplateDownloadPage;
import com.templatemonster.demo.pages.TemplateSearchResultPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;


/**
 * Тестовый сценарий:
 * Предусловие: корзина пустая
 * 1. Незалогиненным пользователем перейти на страницу большого превью фри темплейт шаблона (51682)
 * 2. В блоке Download the Free Template нажать кнопку Download Template
 * 3. Оставить выбранным пункт I am a new customer и нажать Continue as a Guest
 * 4. Заполнить поля формы User Details корректными данными и нажать кнопку Download Sample
 * <p>
 * Ожидаемый результат:
 * 1. Происходит автоматическая регистрация аккаунта пользователя на указанный емейл (без биллинг деталей), при переходе на статусную страницу пользователь залогинен
 * 2. Открывается статусная страница (как для заказа в статусе Approved), в тексте указан емейл пользователя
 * 3. “Бредкрам”: галочкой отмечены пукнты 1 - Signin, 2 – User Details, 3 – Confirm, выделен жирным 4 – Delivery
 * 4. Пользователь получает письма: на подтверждение регистрации, деливери письмо на скачивание фри шаблона
 */
public class TM_005_DownloadSampleAsGuest extends BaseTest {
    private HomePage homePage;
    private TemplateDownloadPage templateDownloadPage;

    @Test
    public void tm_005_DownloadSampleAsGuest() throws InterruptedException {
        //Test data
        String templateId = propertyManager.getProperty("freeHtml5ThemeID");

        //Test steps
        homePage = openHomePage();
        assertFalse(homePage.isUserLoggedIn(), "User is logged into the system");
        templateDownloadPage = homePage.checkCartCount(0)
                .searchForTemplate(templateId)
                .hoverShareAndDownloadButton()
                .shareTemplateWithTwitter();

    }
}
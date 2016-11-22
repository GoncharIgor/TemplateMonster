package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.HomePage;
import com.templatemonster.demo.pages.TemplateDownloadPage;
import com.templatemonster.demo.util.MailGenerator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


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
public class TM_005_DownloadSampleAsGuest extends TemplateMonsterBaseTest {
    private HomePage homePage;
    private TemplateDownloadPage templateDownloadPage;
    private String generatedUniqueEmail = MailGenerator.generateNewEmail();

    @Test
    public void tm_005_DownloadSampleAsGuest() {
        //Test data
        String templateId = propertyManager.getProperty("freeHtml5ThemeID");
        String userName = propertyManager.getProperty("userName");
        String userSurname = propertyManager.getProperty("userSurname");
        String userFullName = userName + " " + userSurname;
        String userPhoneNumber = propertyManager.getProperty("userPhoneNumber");

        //Test steps
        homePage = openHomePage();
        assertFalse(homePage.isUserLoggedIn(), "User is logged into the system");
        templateDownloadPage = homePage.checkCartCount(0)
                .searchForTemplate(templateId)
                .hoverShareAndDownloadButton()
                .shareTemplateWithTwitter()
                .continueAsAGuest()
                .fillUSerDetailsForm(userFullName, generatedUniqueEmail, userPhoneNumber)
                .submitUserDetailsForm();
        assertTrue(templateDownloadPage.checkOrderStatus(generatedUniqueEmail), "Order was not accomplished");

        homePage = openHomePage();
        assertTrue(homePage.isUserLoggedIn(), "User is logged into the system");
        assertEquals(homePage.getValueOfCookie("wac"), "1", "'wac' cookie value is not correct");
    }
}

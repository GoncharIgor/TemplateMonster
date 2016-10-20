package com.templatemonster.demo;

import com.templatemonster.demo.util.WaitHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * 1. Зайти на проект (индексная страница)
 * 2. Заполнить корректными значениями поля чата Your Name и Email Address
 * 3. Нажать Enter/кнопку Start Chat
 * <p>
 * Ожидаемый результат:
 * 1. Успешное подключение к чату (открылся попап http://chat.template-help.com/chat.jsp и произошел коннект (комната Pre-sales Chat))
 */
public class TM_008_ChatTest extends BaseTest {

    @Test
    public void tm_008_ChatTest() throws InterruptedException {
        String userEmail = propertyManager.getProperty("validUserLogin");
        String userName = propertyManager.getProperty("userName");

        homePage = basePage.navigateToHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page was not opened");
        chatPage = homePage.navigateToChatPage(userName, userEmail);
        WaitHelper.waitAdditional(3);
        Assert.assertTrue(chatPage.isChatPageOpened(), "Chat pop up was not opened");
        Assert.assertTrue(chatPage.isChatPreSalesRoomOpened(), "Chat Pre sales room was not opened");
    }
}

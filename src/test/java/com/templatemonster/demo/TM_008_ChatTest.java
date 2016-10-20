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

    String userEmail = propertyManager.getProperty("validUserLogin");
    String userName = propertyManager.getProperty("userName");

    @Test
    public void tm_008_ChatTest() {
        chatPage = onHomePage().navigateToChatPage(userName, userEmail);

        Assert.assertTrue(chatPage.isChatPageOpened(), "Chat pop up was not opened");
        Assert.assertTrue(chatPage.isChatPreSalesRoomOpened(), "Chat Pre sales room was not opened");
    }
}

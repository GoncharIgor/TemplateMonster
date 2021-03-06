package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.ChatPage;
import com.templatemonster.demo.util.WaitHelper;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/**
 * Test case:
 * 1. Зайти на проект (индексная страница)
 * 2. Заполнить корректными значениями поля чата Your Name и Email Address
 * 3. Нажать Enter/кнопку Start Chat
 * <p>
 * Expected result:
 * 1. Успешное подключение к чату (открылся попап http://chat.template-help.com/chat.jsp и произошел коннект (комната Pre-sales Chat))
 */
public class TM_008_ChatTest extends TemplateMonsterBaseTest {
    private ChatPage chatPage;

    @Test
    public void clickOnChatImageOpensChatWindow() {
        //Test data
        String userEmail = propertyManager.getProperty("validUserLogin");
        String userName = propertyManager.getProperty("userName");

        //Test steps
        chatPage = openHomePage()
                .navigateToChatPage(userName, userEmail);
        WaitHelper.waitAdditional(3);

        assertTrue(chatPage.isChatPageOpened(), "Chat pop up was not opened");
        assertTrue(chatPage.isChatPreSalesRoomOpened(), "Chat Pre sales room was not opened");
    }
}

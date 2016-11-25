package com.templatemonster.demo.pages.pagesWithHeader;

import com.templatemonster.demo.pages.basePages.BasePageHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class ChatPage extends BasePageHeader {
    private By CHAT_ROOM_HEADER = By.className("header-panel");

    public ChatPage(WebDriver driver) {
        super(driver);
    }

    public boolean isChatPageOpened() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("http://chat.template-help.com/chat.jsp");
    }

    public boolean isChatPreSalesRoomOpened() {
        String actualChatRoomHeader = $(CHAT_ROOM_HEADER).getText();
        return actualChatRoomHeader.equals("Welcome to Pre-sales Chat");
    }
}

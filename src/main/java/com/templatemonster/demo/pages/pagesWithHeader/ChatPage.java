package com.templatemonster.demo.pages.pagesWithHeader;

import com.templatemonster.demo.pages.basePages.BasePageHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class ChatPage extends BasePageHeader {
    private By CHAT_ROOM_HEADER = By.className("header-panel");

    public ChatPage(WebDriver driver) {
        super(driver);
    }

    public boolean isChatPageOpened() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("http://chat.template-help.com/chaaat.jsp");
    }

    public boolean isChatPreSalesRoomOpened() {
        String actualChatRoomHeader = $(CHAT_ROOM_HEADER).getText();
        return actualChatRoomHeader.equals("Welcome to Pre-sales Chat");
    }
}

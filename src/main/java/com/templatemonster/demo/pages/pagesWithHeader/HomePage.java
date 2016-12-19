package com.templatemonster.demo.pages.pagesWithHeader;

import com.templatemonster.demo.pages.basePages.BasePageHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class HomePage extends BasePageHeader {
    private By CHAT_IMAGE_LOCATOR = By.cssSelector("img.girl");
    private By CHAT_NAME_INPUT_LOCATOR = By.id("live-chat-consultant-form-fullname");
    private By CHAT_PASSWORD_INPUT_LOCATOR = By.id("live-chat-consultant-form-email");
    private By START_CHAT_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Start Chat')]");
    private By LOCALIZATION_COUNTRY_CODE_LOCATOR = By.cssSelector(".user-menu-element.language-pick.user-menu-dropdown span");

    private static final Map<String, String> LOCALIZATION_MAP = new HashMap<>();

    static {
        LOCALIZATION_MAP.put("US", "Account");
        LOCALIZATION_MAP.put("ES", "Cuenta");
        LOCALIZATION_MAP.put("RU", "Учетная запись");
        LOCALIZATION_MAP.put("DE", "Konto");
        LOCALIZATION_MAP.put("PL", "Konto");
        LOCALIZATION_MAP.put("IT", "Account");
        LOCALIZATION_MAP.put("TR", "Hesap");
        LOCALIZATION_MAP.put("FR", "Compte");
        LOCALIZATION_MAP.put("BR", "Conta");
        LOCALIZATION_MAP.put("NL", "Uw account");
        LOCALIZATION_MAP.put("CN", "账户");
        LOCALIZATION_MAP.put("CZ", "Účet");
        LOCALIZATION_MAP.put("UA", "Аккаунт");
        LOCALIZATION_MAP.put("HU", "Fiók");
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public ChatPage navigateToChatPage(String name, String email) {
        $(CHAT_IMAGE_LOCATOR).click();
        $(CHAT_NAME_INPUT_LOCATOR).sendKeys(name);
        $(CHAT_PASSWORD_INPUT_LOCATOR).sendKeys(email);
        $(START_CHAT_BUTTON_LOCATOR).click();
        for (String currentWindow : driver.getWindowHandles()) {
            driver.switchTo().window(currentWindow);
        }
        return new ChatPage(driver);
    }

    public HomePage checkLocalizationSelected(String countryCode) {
        $(LOCALIZATION_COUNTRY_CODE_LOCATOR).shouldHave(text(countryCode));
        return this;
    }

    public HomePage changeLocalization(String countryCode) {
        $(LOCALIZATION_COUNTRY_CODE_LOCATOR).click();
        $(By.id("menu-" + countryCode + "-locale")).click();
        return this;
    }
}
package com.templatemonster.demo.pages;

import com.codeborne.selenide.ex.ElementNotFound;
import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class HomePage extends BasePage {
    private By accountHeaderLocator = By.id("header-signin-link");
    private By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private By CHAT_IMAGE_LOCATOR = By.cssSelector("img.girl");
    private By CHAT_NAME_INPUT_LOCATOR = By.id("live-chat-consultant-form-fullname");
    private By CHAT_PASSWORD_INPUT_LOCATOR = By.id("live-chat-consultant-form-email");
    private By START_CHAT_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Start Chat')]");
    private By CART_COUNT_LOCATOR = By.id("cart-count");
    private By CATEGORY_LEFT_BLOCK_HEADING_LOCATOR = By.id("sf-category-cb");


    private final HashMap<Integer, String> THEMES_TYPES = new HashMap<>();

    {
        THEMES_TYPES.put(1, "WordPress Themes");
        THEMES_TYPES.put(2, "Website Templates");
        THEMES_TYPES.put(3, "Joomla Templates");
        THEMES_TYPES.put(4, "PrestaShop Themes");
        THEMES_TYPES.put(5, "Magento Themes");
        THEMES_TYPES.put(6, "WooCommerce Themes");
        THEMES_TYPES.put(7, "Moto CMS Templates");
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginToSystem() throws InterruptedException {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.fillLoginFormWithAnyCredentials("IgorGoncharUA@gmail.com", "Test_Test").submitLoginForm();
        return this;
    }

    public LoginPage navigateToLoginPage() {
        $(accountHeaderLocator).click();
        switchToWindow(1);
        return new LoginPage(driver);
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

    public TemplateSearchResultPage searchForTemplate(String templateId) {
        driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).sendKeys(templateId, Keys.ENTER);
        return new TemplateSearchResultPage(driver);
    }

    public HomePage checkCartCount(int expectedCount) {
        if (expectedCount == 0) {
            $(CART_COUNT_LOCATOR).shouldHave(text(""));
        } else {
            $(CART_COUNT_LOCATOR).shouldHave(text(String.valueOf(expectedCount)));
        }
        return this;
    }

    public HomePage checkThemeTypes() {
        for (Map.Entry<Integer, String> entry : THEMES_TYPES.entrySet()) {
            $(By.cssSelector(".sub-menu-1.js-sub-menu-1 ul li:nth-child(" + entry.getKey() + ") a")).shouldHave(text(entry.getValue())).click();
            if (entry.getKey() < 7) {
                $(CATEGORY_LEFT_BLOCK_HEADING_LOCATOR).shouldHave(text(entry.getValue() + " Categories"));
            } else if (entry.getKey() == 7) {
                $(CATEGORY_LEFT_BLOCK_HEADING_LOCATOR).shouldHave(text("Moto CMS 3 Templates Categories "));
            }
            if (entry.getKey() == 3) { // To close location modal window
                try {
                    $(By.id("close-popover-UA-switcher")).click();
                } catch (ElementNotFound e) {
                    LOGGER.info("Location modal window was not opened");
                }

            }

        }
        return this;
    }

    public HomePage checkLocalizationIsSelected(String countryCode) {
        $(By.cssSelector(".user-menu-element.language-pick.user-menu-dropdown span")).shouldHave(text(countryCode));
        return this;
    }

    public HomePage changeLocalization(String countryCode) {
        $(By.id("menu-" + countryCode + "-locale")).click();
        return this;
    }
}
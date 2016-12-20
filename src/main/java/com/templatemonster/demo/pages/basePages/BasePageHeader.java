package com.templatemonster.demo.pages.basePages;

import com.codeborne.selenide.ex.ElementNotFound;
import com.templatemonster.demo.pages.LoginPage;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import com.templatemonster.demo.pages.pagesWithHeader.MultiThemesPage;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public abstract class BasePageHeader extends BasePage {
    private By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private By CART_COUNT_LOCATOR = By.id("cart-count");
    private By ACCOUNT_HEADER_LOCATOR = By.id("header-signin-link");
    private By SHOPPING_CART_LOCATOR = By.id("shp-cart");
    private By USER_AVATAR_LOCATOR = By.id("user-avatar");
    private By LOGOUT_LOCATOR = By.id("header-signOut-link");
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

    public BasePageHeader(WebDriver driver) {
        super(driver);
    }

    public TemplateSearchResultPage searchForTemplate(String templateId) {
        driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).sendKeys(templateId, Keys.ENTER);
        return new TemplateSearchResultPage(driver);
    }

    public void checkCartCount(int expectedCount) {
        WaitHelper.waitAdditional(2);
        if (expectedCount == 0) {
            $(CART_COUNT_LOCATOR).shouldHave(text(""));
        } else {
            $(CART_COUNT_LOCATOR).shouldHave(text(String.valueOf(expectedCount)));
        }
    }

    public int getCartCount( ) {
        if ($(CART_COUNT_LOCATOR).has(text(""))){
            return 0;
        }
        return Integer.parseInt($(CART_COUNT_LOCATOR).getText());
    }

    public LoginPage navigateToLoginPage() {
        $(ACCOUNT_HEADER_LOCATOR).click();
        switchToWindow(1);
        return new LoginPage(driver);
    }

    public ShoppingCartPage navigateToShoppingCart() {
        $(SHOPPING_CART_LOCATOR).click();
        switchToWindow(1);
        return new ShoppingCartPage(driver);
    }

    public HomePage logoutFromSystem() {
        $(USER_AVATAR_LOCATOR).click();
        $(LOGOUT_LOCATOR).click();
        return new HomePage(driver);
    }

    public MultiThemesPage checkThemeTypes() {
        for (Map.Entry<Integer, String> entry : THEMES_TYPES.entrySet()) {
            $(By.cssSelector(".sub-menu-1.js-sub-menu-1>ul>li:nth-child(" + entry.getKey() + ") a")).shouldHave(text(entry.getValue())).click();
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
        return new MultiThemesPage(driver);
    }
}

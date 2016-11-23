package com.templatemonster.demo.pages.basePages;

import com.templatemonster.demo.pages.LoginPage;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 23.11.2016.
 */
public class BasePageHeader extends BasePage {
    private By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private By CART_COUNT_LOCATOR = By.id("cart-count");
    private By ACCOUNT_HEADER_LOCATOR = By.id("header-signin-link");
    private By SHOPPING_CART_LOCATOR = By.id("shp-cart");

    public BasePageHeader(WebDriver driver) {
        super(driver);
    }

    public TemplateSearchResultPage searchForTemplate(String templateId) {
        driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).sendKeys(templateId, Keys.ENTER);
        return new TemplateSearchResultPage(driver);
    }

    public void checkCartCount(int expectedCount) {
        WaitHelper.waitAdditional(1);
        if (expectedCount == 0) {
            $(CART_COUNT_LOCATOR).shouldHave(text(""));
        } else {
            $(CART_COUNT_LOCATOR).shouldHave(text(String.valueOf(expectedCount)));
        }
    }

    public LoginPage navigateToLoginPage() {
        $(ACCOUNT_HEADER_LOCATOR).click();
        switchToWindow(1);
        return new LoginPage(driver);
    }

    public ShoppingCartPage openShoppingCart () {
        $(SHOPPING_CART_LOCATOR).click();
        switchToWindow(1);
        return new ShoppingCartPage(driver);

    }
}

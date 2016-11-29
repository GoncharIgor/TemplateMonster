package com.templatemonster.demo.pages.pagesWithHeader;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePageHeader;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage extends BasePageHeader {
    private By ORDER_TOTAL_LOCATOR = By.cssSelector(".price-block .template-price.js-total-price");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage checkOrderTotalProce(int expectedAmount) {
        WaitHelper.waitAdditional(5);
        $(ORDER_TOTAL_LOCATOR).shouldHave(Condition.text(Integer.toString(expectedAmount)));
        return this;
    }

}

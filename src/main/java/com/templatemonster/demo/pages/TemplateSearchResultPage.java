package com.templatemonster.demo.pages;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class TemplateSearchResultPage extends BasePage {

    private final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Add to Cart')]");
    private final By CHECKOUT_NOW_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Checkout Now ')]");

    public TemplateSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTemplateOpened(String templateId) {
        String expectedUrl = "http://www.templatemonster.com/wordpress-themes/" + templateId + ".html";
        String currentUrl = driver.getCurrentUrl();

        return currentUrl.equals(expectedUrl);
    }

    public CheckoutPage addOpenedTemplateToCartAndCheckout() throws InterruptedException {
        $(ADD_TO_CART_BUTTON_LOCATOR).click();
        driver.switchTo().activeElement();
        //TO ADD: CHECK IF TEMPLATE WAS ADDED TO CART
        $(CHECKOUT_NOW_BUTTON_LOCATOR).shouldBe(Condition.visible).click();
        return new CheckoutPage(driver);
    }
}

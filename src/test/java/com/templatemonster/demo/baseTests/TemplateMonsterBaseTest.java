package com.templatemonster.demo.baseTests;

import com.templatemonster.demo.pages.HomePage;
import com.templatemonster.demo.pages.ShoppingCartPage;
import com.templatemonster.demo.pages.TemplateSearchResultPage;
import com.templatemonster.demo.pages.basePages.BasePage;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 22.11.2016.
 */
public class TemplateMonsterBaseTest extends BaseTest {

    public HomePage openHomePage() {
        open("http://www.templatemonster.com/");
        return new HomePage(driver);
    }

    public TemplateSearchResultPage openWordPressTemplate(String templateId) {
        open("http://www.templatemonster.com/wordpress-templates/" + templateId + ".html");
        return new TemplateSearchResultPage(driver);
    }

    public ShoppingCartPage openShoppingCartPage() {
        open("https://secure.templatemonster.com/cart.php");
        return new ShoppingCartPage(driver);
    }

}

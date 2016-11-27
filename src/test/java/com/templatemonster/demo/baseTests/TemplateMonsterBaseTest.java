package com.templatemonster.demo.baseTests;

import com.templatemonster.demo.pages.LoginPage;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import com.templatemonster.demo.pages.pagesWithHeader.ShoppingCartPage;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 22.11.2016.
 */
public class TemplateMonsterBaseTest extends BaseTest {
    private By SIGN_IN_FORM_LOCATOR = By.id("signForm");
    private final String LOGIN_PAGE_URL = "https://wac.templatemonster.com/signin.html?";

    public HomePage openHomePage() {
        open("http://www.templatemonster.com/");
        return new HomePage(driver);
    }

    public HomePage loginToSystem(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        open(LOGIN_PAGE_URL);
        loginPage.fillLoginFormWithAnyCredentials(email, password);
        $(SIGN_IN_FORM_LOCATOR).submit();
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

    public LoginPage openLoginPage() {
        open(LOGIN_PAGE_URL);
        return new LoginPage(driver);
    }

}

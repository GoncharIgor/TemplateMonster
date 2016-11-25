package com.templatemonster.demo.pages;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class LoginPage extends BasePage {
    private By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.id("login_button");
    private By USER_LOGIN_INPUT_LOCATOR = By.id("edtLogin");
    private By USER_PASSWORD_INPUT_LOCATOR = By.id("edtPassword");
    private By LOGIN_ERROR_LABEL_LOCATOR = By.id("edtPassword");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillLoginFormWithAnyCredentials(String login, String password) {
        $(USER_LOGIN_INPUT_LOCATOR).shouldBe(Condition.visible).sendKeys(login);
        $(USER_PASSWORD_INPUT_LOCATOR).shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    public HomePage submitLoginForm() {
        clickSubmitButton();
        return new HomePage(driver);
    }

    public LoginPage clickSubmitButton() {
        $(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
        return this;
    }

    public boolean isLoginPageOpened() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("https://wac.templatemonster.com/signin.html");
    }

    public LoginPage isLoginErrorLabelDisplayed(boolean toBeDisplayed) {
        if (toBeDisplayed) {
            $(LOGIN_ERROR_LABEL_LOCATOR).shouldBe(Condition.visible);
        } else {
            $(LOGIN_ERROR_LABEL_LOCATOR).shouldNotBe(Condition.visible);
        }
        return this;
    }
}

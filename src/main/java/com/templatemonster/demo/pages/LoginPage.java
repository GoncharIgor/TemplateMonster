package com.templatemonster.demo.pages;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class LoginPage extends BasePage {
    private final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.id("login_button");
    private final By USER_LOGIN_INPUT_LOCATOR = By.id("edtLogin");
    private final By USER_PASSWORD_INPUT_LOCATOR = By.id("edtPassword");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillLoginFormWithAnyCredentials(String login, String password) {
        $(USER_LOGIN_INPUT_LOCATOR).shouldBe(Condition.visible).sendKeys(login);
        $(USER_PASSWORD_INPUT_LOCATOR).shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    public HomePage submitLoginForm() {
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
        return new HomePage(driver);
    }

    public boolean isLoginPageOpened(){
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("https://wac.templatemonster.com/signin.html");
    }
}

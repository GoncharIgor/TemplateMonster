package com.templatemonster.demo.pages;

import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;


public class UserRegistrationPage extends BasePage {
    private By USER_EMAIL_INPUT_LOCATOR = By.id("emailarea");
    private By USER_PASSWORD_INPUT_LOCATOR = By.id("pass");
    private By USER_RETYPE_PASSWORD_INPUT_LOCATOR = By.id("passwordRetype");
    private By SECURITY_ANSWER_INPUT_LOCATOR = By.name("answer");
    private By REGISTER_BUTTON_LOCATOR = By.id("register_button");

    public UserRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserRegistrationPageOpened() {
        $(USER_EMAIL_INPUT_LOCATOR).shouldBe(visible);
        return url().contains("https://wac.templatemonster.com/register.html");
    }

    public UserRegistrationPage fillUserRegistrationFormWithAnyCredentials(String login, String password) {
        $(USER_EMAIL_INPUT_LOCATOR).shouldBe(visible).sendKeys(login);
        enterAndReenterPassword(password, password);
        return this;
    }

    private void enterAndReenterPassword(String password, String rePassword) {
        $(USER_PASSWORD_INPUT_LOCATOR).shouldBe(visible).sendKeys(password);
        $(USER_RETYPE_PASSWORD_INPUT_LOCATOR).shouldBe(visible).sendKeys(rePassword);
    }

}

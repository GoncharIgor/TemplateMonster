package com.templatemonster.demo.pages;

import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

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

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginToSystem() throws InterruptedException {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.fillLoginFormWithAnyCredentials("IgorGoncharUA@gmail.com", "Test_Test").submitLoginForm();
        return this;
    }

    public LoginPage navigateToLoginPage(){
        $(accountHeaderLocator).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new LoginPage(driver);
    }

    public ChatPage navigateToChatPage(String name, String email) {
        $(CHAT_IMAGE_LOCATOR).click();
        $(CHAT_NAME_INPUT_LOCATOR).sendKeys(name);
        $(CHAT_PASSWORD_INPUT_LOCATOR).sendKeys(email);
        $(START_CHAT_BUTTON_LOCATOR).click();
        for (String currentWindow: driver.getWindowHandles()){
            driver.switchTo().window(currentWindow);
        }
        return new ChatPage(driver);
    }

    public boolean isHomePageOpened(){
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("http://www.templatemonster.com/");
    }

    public TemplateSearchResultPage searchForTemplate(String templateId){
        driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).sendKeys(templateId, Keys.ENTER);
        return new TemplateSearchResultPage(driver);
    }
}

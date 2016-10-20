package com.templatemonster.demo.pages;

import com.templatemonster.demo.pages.basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class HomePage extends BasePage {
    private final By ACCOUNT_HEADER_LOCATOR = By.id("header-signin-link");
    private final By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private final By CHAT_IMAGE_LOCATOR = By.cssSelector("img.girl");
    private final By CHAT_NAME_INPUT_LOCATOR = By.id("live-chat-consultant-form-fullname");
    private final By CHAT_PASSWORD_INPUT_LOCATOR = By.id("live-chat-consultant-form-email");
    private final By START_CHAT_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Start Chat')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginToSystem() throws InterruptedException {
        LoginPage loginPage = navigateToLoginPage();
        loginPage.fillLoginFormWithAnyCredentials("IgorGoncharUA@gmail.com", "Test_Test").submitLoginForm();
        return this;
    }

    public LoginPage navigateToLoginPage(){
        driver.findElement(ACCOUNT_HEADER_LOCATOR).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new LoginPage(driver);
    }

    public ChatPage navigateToChatPage(String name, String email) throws InterruptedException {
        driver.findElement(CHAT_IMAGE_LOCATOR).click();
        driver.findElement(CHAT_NAME_INPUT_LOCATOR).sendKeys(name);
        driver.findElement(CHAT_PASSWORD_INPUT_LOCATOR).sendKeys(email);
        driver.findElement(START_CHAT_BUTTON_LOCATOR).click();
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

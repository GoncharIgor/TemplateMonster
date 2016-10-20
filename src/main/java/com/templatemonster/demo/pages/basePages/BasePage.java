package com.templatemonster.demo.pages.basePages;

import com.codeborne.selenide.SelenideElement;
import com.templatemonster.demo.pages.HomePage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public abstract class BasePage {
    private static final Logger LOGGER = Logger.getLogger(WaitHelper.class);
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage navigateToHomePage() {
        driver.navigate().to("http://www.templatemonster.com/");
        return new HomePage(driver);
    }

    public boolean isUserLoggedIn() {
        By userAccountHeaderLocator = By.id("menu-account-block");
        return getElementUntilItIsLoaded(userAccountHeaderLocator, 15).isDisplayed();
    }


    public WebElement getElementUntilItIsLoaded(By locator, int time) {
        WebDriverWait waitForElement = new WebDriverWait(driver, time);
        return waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getValueOfCookie(String cookieName) {
        String result = "cookie not found";
        for (Cookie ck : driver.manage().getCookies()) {
            if (ck.getName().equals(cookieName)) {
                result = ck.getValue();
            }
        }
        return result;
    }
}

package com.templatemonster.demo.pages.basePages;

import com.codeborne.selenide.SelenideElement;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    protected static final Logger LOGGER = Logger.getLogger(WaitHelper.class);
    protected WebDriver driver;
    protected JavascriptExecutor javaScriptExecutor;

    private By USER_ACCOUNT_HEADER_LOCATOR = By.id("menu-account-block");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        javaScriptExecutor = (JavascriptExecutor) driver;
    }

    public boolean isUserLoggedIn() {
        WaitHelper.waitAdditional(7);
        String classAttributeValue = $(USER_ACCOUNT_HEADER_LOCATOR).getAttribute("class");
        return !classAttributeValue.contains("hidden");
    }

    public void getElementUntilItIsLoaded(By locator, int time) {
        WebDriverWait waitForElement = new WebDriverWait(driver, time);
        //return waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void areElementsNotVisibleNow(SelenideElement... selenideElements) {
        WaitHelper.setImplicitWait(driver, 0.5);
        for (SelenideElement elem : selenideElements) {
            $(elem).shouldNotBe(visible);
        }
        WaitHelper.setImplicitWaitDefault(driver);

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

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }

    public boolean isImageLoaded(By imageLocator) {
        boolean isImagePresent = (Boolean) javaScriptExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", $(imageLocator));
        return true;
    }

    public void switchToWindow(int number) {
        WaitHelper.waitAdditional(0.5);
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(number));
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(pageLoadCondition);
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Throwable e) {
            return false;
        }
    }

    public boolean doesElementHasExpectedText(By locator, String expectedTest) {
        return $(locator).has(text(expectedTest));
    }
}

package com.templatemonster.demo.pages.basePages;

import com.codeborne.selenide.SelenideElement;
import com.templatemonster.demo.util.StatusWebElem;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.templatemonster.demo.util.StatusWebElem.*;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class BasePage {
    protected static final Logger LOGGER = Logger.getLogger(WaitHelper.class);
    protected WebDriver driver;
    private By userAccountHeaderLocator = By.id("menu-account-block");
    protected JavascriptExecutor javaScriptExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        javaScriptExecutor = (JavascriptExecutor) driver;
    }

    public boolean isUserLoggedIn() {
        WaitHelper.waitAdditional(7);
        String classAttributeValue = driver.findElement(userAccountHeaderLocator).getAttribute("class");
        return !classAttributeValue.contains("hidden");
    }

    public WebElement getElementUntilItIsLoaded(By locator, int time) {
        WebDriverWait waitForElement = new WebDriverWait(driver, time);
        return waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkElementStatus(SelenideElement selenideElement, StatusWebElem expectedStatus) {
        if (expectedStatus.equals(VISIBLE)) {
            $(selenideElement).shouldBe(visible);
        } else if (expectedStatus.equals(ENABLED)) {
            $(selenideElement).shouldBe(visible, enabled);
        } else if (expectedStatus.equals(DISABLED)) {
            $(selenideElement).shouldBe(visible, disabled);
        } else if (expectedStatus.equals(NOT_VISIBLE)) {
            isElementsNotVisibleNow($(selenideElement));
        } else {
            Assert.assertTrue(false, "Incorrect expected status. Possible values: ENABLED / DISABLED / NOT_AVAILABLE");
        }
    }

    public void isElementsNotVisibleNow(SelenideElement... selenideElements) {
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
        wait.until(pageLoadCondition);
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Throwable e) {
            return false;
        }
    }
}

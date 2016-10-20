package com.templatemonster.demo.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class WaitHelper {
    private static final Logger LOGGER = Logger.getLogger(WaitHelper.class);

    public static void setImplicitWaitDefault(WebDriver driver) {
        int implicit = 15;
        driver.manage().timeouts().implicitlyWait(implicit, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public static void setImplicitWait(WebDriver driver, double seconds) {
        driver.manage().timeouts().implicitlyWait((long) seconds * 1000, TimeUnit.MILLISECONDS);
    }

    public static void waitAdditional(double seconds) {
        if (seconds <= 0) {
            return;
        }
        long milliseconds = (long) (seconds * 1000);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WebDriverException(e);
        }
    }

    public static void waitUntilElementIsLoaded(WebDriver driver, final By by, long timeOutInSeconds) {
        WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
        LOGGER.debug("Waiting for Element to be loaded by locator" + by);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}


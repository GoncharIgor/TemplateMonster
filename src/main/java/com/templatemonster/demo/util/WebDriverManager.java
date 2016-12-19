package com.templatemonster.demo.util;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by i.gonchar on 12.12.2016.
 */
public class WebDriverManager extends BaseUtils {
    private static WebDriver driver;

    public static WebDriver initializeWebDriver(String browser) {
        if (browser != null) {
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxDriverManager.getInstance().setup();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "en");
                driver = new FirefoxDriver(profile);
            } else if (browser.equalsIgnoreCase("chrome")) {
                ChromeDriverManager.getInstance().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("ie")) {
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver();
            }
            WebDriverRunner.setWebDriver(driver);
            LOGGER.info(WordUtils.capitalize(browser) + " browser was initialized");
        } else {
            LOGGER.info("Incorrect browser was passed");
        }
        return driver;
    }

    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }
}

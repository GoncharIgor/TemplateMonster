package com.templatemonster.demo.baseTests;

import com.codeborne.selenide.WebDriverRunner;
import com.templatemonster.demo.util.FileUtils;
import com.templatemonster.demo.util.PropertyManager;
import com.templatemonster.demo.util.WaitHelper;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

public class BaseTest {
    protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected static PropertyManager propertyManager;
    protected WebDriver driver;
    protected FileUtils fileUtils;

    @BeforeTest
    public void generateProperties() {
        propertyManager = new PropertyManager();
        propertyManager.generateProperty();
        fileUtils = new FileUtils();
    }

    @Parameters({"browser", "environment"})
    @BeforeMethod
    public void setUp(@Optional String browser, @Optional("prod") String environment) {
        initializeWebDriver(browser);
        driver.manage().window().setPosition(new Point(1920, 24)); //to start tests in right monitor when 2 are available
        driver.manage().window().maximize();
        WaitHelper.setImplicitWaitDefault(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                LOGGER.info("Failed to close browser. Error message:" + e.getMessage());
            }
        }
    }

    private void initializeWebDriver(String browser) {
        if (browser != null) {
            if (browser.equalsIgnoreCase("firefox")) {
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
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
    }

}

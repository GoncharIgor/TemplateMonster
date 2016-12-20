package com.templatemonster.demo.baseTests;

import com.templatemonster.demo.util.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {
    protected static final Logger LOGGER = LoggerUtil.createLogger();
    protected static PropertyManager propertyManager;
    protected WebDriver driver;
    protected FileUtils fileUtils;

    @BeforeTest
    public void generateProperties() {
        propertyManager = new PropertyManager();
        fileUtils = new FileUtils();
        propertyManager.generateProperty();
    }

    @Parameters({"browser", "environment"})
    @BeforeMethod
    public void setUp(@Optional String browser, @Optional("prod") String environment) {
        driver = WebDriverManager.initializeWebDriver(browser);
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
                LOGGER.error("Failed to close browser. Error message:" + e.getMessage());
            }
        }
    }
}

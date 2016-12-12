package com.templatemonster.demo.baseTests;

import com.templatemonster.demo.util.FileUtils;
import com.templatemonster.demo.util.PropertyManager;
import com.templatemonster.demo.util.WaitHelper;
import com.templatemonster.demo.util.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
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
                LOGGER.info("Failed to close browser. Error message:" + e.getMessage());
            }
        }
    }
}

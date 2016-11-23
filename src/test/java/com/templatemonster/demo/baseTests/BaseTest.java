package com.templatemonster.demo.baseTests;

import com.codeborne.selenide.WebDriverRunner;
import com.templatemonster.demo.util.PropertyManager;
import com.templatemonster.demo.util.WaitHelper;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.io.File;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class BaseTest {
    protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected static PropertyManager propertyManager;
    protected WebDriver driver;

    @BeforeTest
    public void generateProperties() {
        propertyManager = new PropertyManager();
        propertyManager.generateProperty();
    }

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void setUp(@Optional String browser, @Optional("prod") String environment) {
        initializeWebDriver(browser);
        driver.manage().window().setPosition(new Point(1920, 24)); //to start tests in right monitor when 2 are available
        driver.manage().window().maximize();
        WaitHelper.setImplicitWaitDefault(driver);
    }

    @AfterClass
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
               /*
                To run with Selenium 3.0
                File resourcesDirectory = new File("src/test/resources/webdriver/geckodriver.exe");
                String GECKO_DRIVER_PATH = resourcesDirectory.getAbsolutePath();
                System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);*/
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                File resourcesDirectory = new File("src/test/resources/webdriver/chromedriver_win32.exe");
                String CHROME_DRIVER_PATH = resourcesDirectory.getAbsolutePath();
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("ie")) {
                File resourcesDirectory = new File("src/test/resources/webdriver/IEDriverServer_64.exe");
                String IEXPLORER_DRIVER_PATH = resourcesDirectory.getAbsolutePath();
                System.setProperty("webdriver.ie.driver", IEXPLORER_DRIVER_PATH);
                driver = new InternetExplorerDriver();
            }
            WebDriverRunner.setWebDriver(driver);
            LOGGER.info(WordUtils.capitalize(browser) + " browser was initialized");
        } else {
            LOGGER.info("Incorrect browser was passed");
        }
    }

}

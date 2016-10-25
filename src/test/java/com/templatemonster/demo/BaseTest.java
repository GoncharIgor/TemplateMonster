package com.templatemonster.demo;

import com.templatemonster.demo.pages.*;
import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.PropertyManager;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.io.File;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class BaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected static PropertyManager propertyManager;
    protected WebDriver driver;

    protected BasePage basePage;
    protected HomePage homePage;


    @BeforeTest
    static void generateProperties() {
        propertyManager = new PropertyManager();
        propertyManager.generateProperty();
    }

    @Parameters({"browser", "environment"})
    @BeforeClass
    public void setUp(@Optional String browser, @Optional("prod") String environment) {
        initializeWebDriver(browser);
        WaitHelper.setImplicitWaitDefault(driver);
        basePage = new BasePage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.manage().deleteAllCookies();

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
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                File resourcesDirectory = new File("src/test/resources/webdriver/chromedriver_win32.exe");
                String CHROME_DRIVER_PATH = resourcesDirectory.getAbsolutePath();
                System.out.println(CHROME_DRIVER_PATH);
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("ie")) {
                File resourcesDirectory = new File("src/test/resources/webdriver/IEDriverServer_64.exe");
                String IEXPLORER_DRIVER_PATH = resourcesDirectory.getAbsolutePath();
                System.setProperty("webdriver.ie.driver", IEXPLORER_DRIVER_PATH);
                driver = new InternetExplorerDriver();
            }
        } else {
            LOGGER.error("Incorrect browser was passed");
        }
    }
}

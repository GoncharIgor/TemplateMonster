package com.templatemonster.demo;

import com.codeborne.selenide.WebDriverRunner;
import com.templatemonster.demo.pages.*;
import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.PropertyManager;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class BaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    protected static PropertyManager propertyManager;
    protected WebDriver driver;

    protected BasePage basePage;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected TemplateSearchResultPage templateSearchResultPage;
    protected ChatPage chatPage;
    protected CheckoutPage checkoutPage;
    protected PayPalPage payPalPage;
    protected CardPaymentPage cardPaymentPage;


    @BeforeTest
    static void generateProperties() {
        propertyManager = new PropertyManager();
        propertyManager.generateProperty();
    }

    @Parameters("environment")
    @BeforeClass
    public void setUp() {
        initializeWebDriver();
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

    private void initializeWebDriver() {
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    protected HomePage onHomePage() {
        open("http://www.templatemonster.com/");
        return new HomePage(driver);
    }
}

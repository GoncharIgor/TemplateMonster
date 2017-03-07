package com.templatemonster.demo.gridTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;


public class SeleniumGridTest {
    private WebDriver driver;
    private DesiredCapabilities capabilities;

    @Before
    public void setUp() throws IOException {
        //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

        //Now you can Initialize marionette driver to launch firefox
        capabilities = DesiredCapabilities.chrome();
        //  capabilities.setCapability("marionette", false);
        // driver = new MarionetteDriver(capabilities);
        //driver = new ChromeDriver(capabilities);
         driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    @Test
    public void shouldGetGoogleTitle() {
        driver.get("http://www.google.pl");
        String title = driver.getTitle();
        assert (title.contains("Google"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
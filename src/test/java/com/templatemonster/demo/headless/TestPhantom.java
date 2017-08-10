package com.templatemonster.demo.headless;

import com.templatemonster.demo.util.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class TestPhantom {
   /* @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();*/

    @Test
    public void testPhantomJs() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\Programms\\PhantomJs\\bin\\phantomjs.exe");
        WebDriver driver = WebDriverManager.initializeWebDriver("chrome");
       // PhantomJSDriver driver = new PhantomJSDriver(caps);
        driver.get("https://www.wikipedia.org/");
        System.out.println("Page title: " + driver.getTitle());
        driver.findElement(By.partialLinkText("English")).click();
        System.out.println("Page title: " + driver.getTitle());
        driver.close();

    }

}

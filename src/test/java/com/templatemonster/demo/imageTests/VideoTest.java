package com.templatemonster.demo.imageTests;

import com.templatemonster.demo.util.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VideoTest {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private WebElement video;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.initializeWebDriver("chrome");
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void testVideoSrc() {
        driver.get("https://www.youtube.com/watch?v=Xuj_PlhDz9k");
         video = driver.findElement(By.tagName("video"));
        String src = (String) javascriptExecutor.executeScript("return arguments[0].currentSrc", video);
        System.out.println(src);
    }

    @Test
    public void testVideoTime() {
        driver.get("https://www.youtube.com/watch?v=Xuj_PlhDz9k");
        video = driver.findElement(By.tagName("video"));
        double duration = (Double) javascriptExecutor.executeScript("return arguments[0].duration", video);
        System.out.println(duration);
    }

    @Test
    public void testVideoControl() throws InterruptedException {
        driver.get("https://www.youtube.com/watch?v=Xuj_PlhDz9k");
        video = driver.findElement(By.tagName("video"));
        Thread.sleep(1000);
        javascriptExecutor.executeScript("return arguments[0].pause()", video);
        Thread.sleep(2000);
        javascriptExecutor.executeScript("return arguments[0].play()", video);
        Thread.sleep(2000);
    }

    @AfterMethod
    public void shutDown() {
        driver.quit();
    }

}

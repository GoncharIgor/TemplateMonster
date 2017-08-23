package com.templatemonster.demo.imageTests;


import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.templatemonster.demo.util.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertTrue;


@Listeners(VideoListener.class) //pirogov video recorder anotation
public class WriteVideoTest {
    protected WebDriver driver;

    @Test
    @Video
    public void shouldFailAndCreateRecordWithTestName() throws InterruptedException {
        Thread.sleep(1000);
        assert false;
    }

    @Test
    @Video(name = "second_test")
    public void videoShouldHaveNameSecondTest() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(false);
    }

    @Test
    @Video(name = "successful_test")
    public void videoWillNotBeCreatedBecauseItIsNotFailed() throws InterruptedException {
    //    System.setProperty("video.save.mode", "ALL"); //
        Thread.sleep(1000);
        assertTrue(true);
    }


    @Test
    @Video(name = "google_test")
    public void googleTest() throws InterruptedException {
        driver = WebDriverManager.initializeWebDriver("chrome");
        driver.get("https://github.com/SergeyPirogov/video-recorder-java");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/a")).click();
        Thread.sleep(1000);
        driver.close();
        assertTrue(false);
    }

}

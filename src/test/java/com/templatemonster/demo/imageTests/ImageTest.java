package com.templatemonster.demo.imageTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.util.ScreenShotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest extends TemplateMonsterBaseTest {
    private ScreenShotUtil screenShotUtil;
    private By logoLocator = By.cssSelector(".logo-icon.svg");

    @Test
    public void checkLogoOnHomePage() throws IOException {
        screenShotUtil = new ScreenShotUtil(driver);

        openHomePage();
        screenShotUtil.takeScreenShotFromPartOfThePage("aaa", logoLocator);
    }

    @Test
    public void compare2Images() throws IOException {
        screenShotUtil = new ScreenShotUtil(driver);
        BufferedImage img1 = screenShotUtil.getImageFromFile("screenShots/aaa.png");
        BufferedImage img2 = screenShotUtil.getImageFromFile("screenShots/bbb.png");

        double a = screenShotUtil.getDifferenceOfImagesInPercent(img1, img2);
        System.out.println("Difference is: " + a);
    }
}

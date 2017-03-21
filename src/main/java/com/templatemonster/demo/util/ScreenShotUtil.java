package com.templatemonster.demo.util;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

public class ScreenShotUtil {
    private WebDriver driver;

    public ScreenShotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShot(String screenShotName) throws IOException {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile, new File("screenShots/" + screenShotName + ".png"));
    }

    public void takeScreenShotFromPartOfThePage(String screenShotName, By locator) throws IOException {
        SelenideElement ele = $(locator);
        // Get entire page screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        //  Get the location of element on the page and its width and height
        Point point = ele.getLocation();
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);

        // Copy the element screenshot to disk
        FileUtils.copyFile(screenshot, new File("screenSchots/" + screenShotName + ".png"));
    }

}

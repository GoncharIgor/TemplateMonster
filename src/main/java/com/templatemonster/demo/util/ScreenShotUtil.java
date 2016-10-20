package com.templatemonster.demo.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by i.gonchar on 27.09.2016.
 */
public class ScreenShotUtil {
    private WebDriver driver;

    public ScreenShotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShot(String screenShotName) throws IOException {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile, new File("screenSchots/" + screenShotName + ".png"));
    }

}

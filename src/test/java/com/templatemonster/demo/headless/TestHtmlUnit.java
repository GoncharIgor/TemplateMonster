package com.templatemonster.demo.headless;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class TestHtmlUnit {

    @Test
    public void testGoogle(){
        //It works only with correct version of browser driver and selenium
        // Selenium version == 3.4.0
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.google.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}



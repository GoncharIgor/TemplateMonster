package com.templatemonster.demo.headless;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 10/03/2017.
 */
public class TestPhantom {
   /* @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();*/

    @Test
    public void setUp() throws Exception {
        //Chrome
       File src = new File("C:\\Users\\i.gonchar\\IdeaProjects\\TemplateMonster\\src\\test\\resources\\drivers\\chromedriver.exe");
       System.setProperty("webdriver.chrome.driver", src.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
       WebDriver driver = new ChromeDriver(options);

        //Загружает phantomjs из репозитария maven, распаковывает и возвращает путь к этому браузеру
        // String phantomJsPath = PhantomJsDowloader.getPhantomJsPath();
/*        File src = new File("D:\\Programms\\Drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();*/


        driver.get("https://google.com");
        String title = driver.getTitle();
        System.out.println(title);
        driver.findElement(By.id("lst-ib")).sendKeys("selenium");
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("h3.r a")).click();

        title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }

}

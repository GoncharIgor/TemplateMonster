package com.templatemonster.demo.imageTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.templatemonster.demo.util.ScreenShotUtil;
import com.templatemonster.demo.util.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

public class CanvasTest {
    private WebDriver driver;
    private ScreenShotUtil screenShotUtil;
    private By canvasLoocator = By.xpath("//*[@id=\"container-panel-260341\"]/div/div/div[3]/div/div/div/div/ui-bb-loading-indicator-ng/ui-bb-substitute-error-ng/ng-transclude/div/div/ui-bb-empty-state-ng/ng-transclude/ui-bb-chartjs-chart-bar/canvas");

    @Test
    public void testCanvas() throws InterruptedException {
        driver = WebDriverManager.initializeWebDriver("chrome");
        driver.get("http://small-fog-24-editorial.backbase.dev:9080/portalserver/retail-banking-demo/insights?enable-mocks");

        $(By.name("username")).sendKeys("admin");
        $(By.name("password")).sendKeys("admin");
        $(By.name("password")).submit();
        $(By.linkText("Home")).shouldBe(Condition.visible);
        driver.get("http://small-fog-24-editorial.backbase.dev:9080/portalserver/retail-banking-demo/insights?enable-mocks");
        $(By.linkText("Spending Analysis")).shouldBe(Condition.visible);

        SelenideElement canvas = $(canvasLoocator);

        screenShotUtil = new ScreenShotUtil(driver);
        try {
            Thread.sleep(2000);
            screenShotUtil.takeScreenShotFromPartOfThePage("canvasTest", canvasLoocator);
        } catch (IOException e) {
            System.out.println("Screenshot was not taken");
        }
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}

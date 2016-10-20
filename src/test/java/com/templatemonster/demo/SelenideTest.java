package com.templatemonster.demo;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.dataProviders.TestDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by i.gonchar on 04.10.2016.
 */
public class SelenideTest extends BaseTest {
    private final By ACCOUNT_HEADER_LOCATOR = By.id("header-signin-link");

    @Test (dataProviderClass = TestDataProvider.class, dataProvider = "general_browser_and_environment")
    public void loginSelenideTest(String url) throws InterruptedException {
        open(url);
        $(ACCOUNT_HEADER_LOCATOR).shouldBe(Condition.visible).click();
    }

}

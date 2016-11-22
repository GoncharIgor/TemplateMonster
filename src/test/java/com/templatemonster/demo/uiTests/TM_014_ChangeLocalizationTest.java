package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.BaseTest;
import com.templatemonster.demo.pages.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 18.11.2016.
 */
public class TM_014_ChangeLocalizationTest extends BaseTest {
    private HomePage homePage;

    @Test
    public void tm_014_ChangeLocalizationTest() throws InterruptedException {
        homePage = openHomePage()
                .checkLocalizationSelected("US")
                .changeLocalization("RU")
                .checkLocalizationSelected("RU");
    }
}

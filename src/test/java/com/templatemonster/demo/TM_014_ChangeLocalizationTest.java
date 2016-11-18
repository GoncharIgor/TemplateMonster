package com.templatemonster.demo;

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
                .checkLocalizationIsSelected("US")
                .changeLocalization("RU");
    }
}

package com.templatemonster.demo;

import com.templatemonster.demo.pages.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 17.11.2016.
 */
public class TM_013_CheckThemeTypesTest extends BaseTest {
    private HomePage homePage;

    @Test
    public void tm_013_CheckHomePageTest() {
        homePage = openHomePage()
                .checkThemeTypes();

    }
}

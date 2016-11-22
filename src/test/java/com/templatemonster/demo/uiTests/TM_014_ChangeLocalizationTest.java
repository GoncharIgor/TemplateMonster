package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 18.11.2016.
 */
public class TM_014_ChangeLocalizationTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void tm_014_ChangeLocalizationTest() throws InterruptedException {
        homePage = openHomePage()
                .checkLocalizationSelected("US")
                .changeLocalization("RU")
                .checkLocalizationSelected("RU");
    }
}

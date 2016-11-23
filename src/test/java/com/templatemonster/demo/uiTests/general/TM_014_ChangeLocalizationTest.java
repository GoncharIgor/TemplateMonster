package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 18.11.2016.
 */
public class TM_014_ChangeLocalizationTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void tm_014_ChangeLocalizationTest() {

        homePage = openHomePage()
                .checkLocalizationSelected("US")
                .changeLocalization("RU")
                .checkLocalizationSelected("RU");
    }
}

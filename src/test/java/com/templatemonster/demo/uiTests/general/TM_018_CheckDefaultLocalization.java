package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 19.12.2016.
 */
public class TM_018_CheckDefaultLocalization extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void defaultLocalizationShouldBeSelected() {
        homePage = openHomePage()
                .checkLocalizationSelected("US");
    }
}

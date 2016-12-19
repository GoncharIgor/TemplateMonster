package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Test case:
 * 1. Open index page
 * <p>
 * Expected result:
 * 1. US localization is selected by default
 */
public class TM_018_CheckDefaultLocalization extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void defaultLocalizationShouldBeSelected() {
        homePage = openHomePage()
                .checkLocalizationSelected("US");
    }
}

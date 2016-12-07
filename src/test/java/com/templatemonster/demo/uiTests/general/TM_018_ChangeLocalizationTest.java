package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Test case:
 * Precondition: 'En' localization is selected
 * 1. Change localization to 'Ru'
 * <p>
 * Expected result:
 * 1. All major web-site elements are translated to Russian language
 */
public class TM_018_ChangeLocalizationTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void userChangesLocalizationToRu() {

        homePage = openHomePage()
                .checkLocalizationSelected("US")
                .changeLocalization("RU")
                .checkLocalizationSelected("RU");
    }
}

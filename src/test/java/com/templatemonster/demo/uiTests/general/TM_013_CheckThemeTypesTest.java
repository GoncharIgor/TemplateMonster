package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 17.11.2016.
 */
public class TM_013_CheckThemeTypesTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test
    public void tm_013_CheckHomePageTest() {

        homePage = openHomePage()
                .checkThemeTypes();

    }
}

package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.BaseTest;
import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.HomePage;
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

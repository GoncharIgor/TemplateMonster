package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.TemplateSearchResultPage;
import org.testng.annotations.Test;


/**
 * Created by i.gonchar on 16.11.2016.
 */
public class TM_012_TemplateSearchResultTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;

    @Test
    public void tm_012_TemplateSearchResultTest() {
        templateSearchResultPage = openWordPressTemplate("59018")
                .checkTemplateHeading("InMotion - Dance School WordPress Theme")
                .checkPrevievImage()
                .checkWhatTemplateInfoTabIsSelected(1);

    }
}
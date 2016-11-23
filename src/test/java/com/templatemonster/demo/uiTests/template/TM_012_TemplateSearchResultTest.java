package com.templatemonster.demo.uiTests.template;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;


/**
 * Created by i.gonchar on 16.11.2016.
 */
public class TM_012_TemplateSearchResultTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;

    @Test
    public void tm_012_TemplateSearchResultTest() {
        //Test data
        String templateId = propertyManager.getProperty("danceSchoolThemeID");

        //Test steps
        templateSearchResultPage = openWordPressTemplate(templateId)
                .checkTemplateHeading("InMotion - Dance School WordPress Theme")
                .checkPrevievImageIsVisible()
                .checkWhatTemplateInfoTabIsSelected(1);

    }
}

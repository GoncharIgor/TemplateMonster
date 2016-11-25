package com.templatemonster.demo.uiTests.template;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;


/**
 * Тестовый сценарий:
 * 1. Открыть найденный шаблон
 * 2. По очереди открыть вкладки
 * <p>
 * Ожидаемый результат:
 * 1.
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

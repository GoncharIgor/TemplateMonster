package com.templatemonster.demo.uiTests.template;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.*;


/**
 * Тестовый сценарий:
 * 1. Зайти на проект (индексная страница)
 * 2. В поле поиска ввести номер шаблона и нажать Enter/кнопку поиска
 * <p>
 * Ожидаемый результат:
 * 1. Открывается страница превью соответствующего шаблона
 * 2. Поисковый запрос очищается
 */
public class TM_016_SearchTemplateTest extends TemplateMonsterBaseTest {
    private By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private TemplateSearchResultPage templateSearchResultPage;

    @Test
    public void tm_002_SearchTemplateTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");

        //Test steps
        templateSearchResultPage = openHomePage()
                .searchForTemplate(templateId);
        assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        assertEquals("", $(TEMPLATE_SEARCH_FIELD_LOCATOR).getAttribute("value"), "Template search field is not empty");
    }
}

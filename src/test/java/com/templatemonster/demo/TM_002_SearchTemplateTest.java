package com.templatemonster.demo;

import com.templatemonster.demo.pages.TemplateSearchResultPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
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
public class TM_002_SearchTemplateTest extends BaseTest {
    private By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");
    private TemplateSearchResultPage templateSearchResultPage;

    @Test(groups = {"functionalTests"})
    public void tm_002_SearchTemplateTest() {
        //Test data
        String templateId = propertyManager.getProperty("environmentalThemeID");

        //Test steps
        homePage = basePage.navigateToHomePage();
        assertTrue(homePage.isHomePageOpened(), "Home page was not opened");
        templateSearchResultPage = homePage.searchForTemplate(templateId);
        assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        assertEquals("", driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).getAttribute("value"), "Template search field is not empty");
    }
}

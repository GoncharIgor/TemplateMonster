package com.templatemonster.demo;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


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
    private final By TEMPLATE_SEARCH_FIELD_LOCATOR = By.name("keywords");

    @Test(groups = {"functionalTests"})
    public void tm_002_SearchTemplateTest() throws InterruptedException {
        String templateId = propertyManager.getProperty("environmentalThemeID");

        homePage = basePage.navigateToHomePage();
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page was not opened");
        templateSearchResultPage = homePage.searchForTemplate(templateId);
        Assert.assertTrue(templateSearchResultPage.isTemplateOpened(templateId), "Needed template was not opened");
        Assert.assertEquals("", driver.findElement(TEMPLATE_SEARCH_FIELD_LOCATOR).getAttribute("value"), "Template search field is not empty");
    }
}

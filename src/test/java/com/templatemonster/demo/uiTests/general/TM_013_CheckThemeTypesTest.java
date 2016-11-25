package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import com.templatemonster.demo.pages.pagesWithHeader.MultiThemesPage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * 1. Открыть стартовую страницу
 * 2. Открыть по очереди список шаблонов по видам в навигационном меню
 * <p>
 * Ожидаемый результат:
 * 1. Открываются шаблоны по видам. Название вида отображается в левом хедере
 */

public class TM_013_CheckThemeTypesTest extends TemplateMonsterBaseTest {
    private MultiThemesPage multiThemesPage;

    @Test
    public void userOpensDifferentThemeTypes() {
        multiThemesPage = openHomePage()
                .checkThemeTypes();
    }
}

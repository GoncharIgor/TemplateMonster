package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Тестовый сценарий:
 * Предусловие: выбрана английская локализация
 * 1. Поменять локализацию на Русскую
 * <p>
 * Ожидаемый результат:
 * 1. Все ключевые элементы сайта переведены на русский язык
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

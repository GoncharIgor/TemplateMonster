package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.TemplateSearchResultPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Проверка lazy load на странице превью (блок Overview)
 * Тестовый сценарий:
 * 1. Перейти на страницу превью шаблона у которого есть картинки в блоке Overview (проперть image-key-features)
 * – например, http://www.templatemonster.com/wordpress-themes/52112.html
 * 2. Проскролить страницу вниз
 * <p>
 * Ожидаемый результат:
 * 1. Загрузка картинок в блоке происходит по мере скролла страницы
 */
public class TM_009_PageScrollTest extends TemplateMonsterBaseTest {
    private TemplateSearchResultPage templateSearchResultPage;


    @Test
    public void imagesAreLoadedWhilePageIsScrolledDown() {
        //Test steps
        open("http://www.templatemonster.com/wordpress-themes/52112.html");
        templateSearchResultPage = new TemplateSearchResultPage(driver)
                .scrollPageDownToImageNumber(27);

    }
}

package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.dataProviders.TestDataProvider;
import com.templatemonster.demo.pages.basePages.BasePageHeader;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test case:
 * Precondition: 'En' localization is selected
 * 1. Change localization to 'Ru'
 * <p>
 * Expected result:
 * 1. All major web-site elements are translated to Russian language
 */
public class TM_018_ChangeLocalizationTest extends TemplateMonsterBaseTest {
    private BasePageHeader basePageHeader;

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_018_CHANGE_LOCALIZATION_TEST)
    public void userChangesLocalizationToRu(List<String> testData) {

        basePageHeader = openHomePage()
                .checkLocalizationSelected(testData.get(0))
                .changeLocalization(testData.get(2))
                .checkLocalizationSelected(testData.get(2));
    }
}

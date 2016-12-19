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
public class TM_019_ChangeLocalizationTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = TestDataProvider.TM_018_CHANGE_LOCALIZATION_TEST)
    public void userChangesLocalizationToRu(List<String> testData) {

        homePage = openHomePage();
        for (int i = 1; i < testData.size(); i++) {
            homePage.changeLocalization(testData.get(i))
                    .checkLocalizationSelected(testData.get(i));
        }

    }
}

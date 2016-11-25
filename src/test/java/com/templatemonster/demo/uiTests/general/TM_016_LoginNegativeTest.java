package com.templatemonster.demo.uiTests.general;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;
import com.templatemonster.demo.pages.pagesWithHeader.HomePage;
import org.testng.annotations.Test;

/**
 * Created by i.gonchar on 25.11.2016.
 */
public class TM_016_LoginNegativeTest extends TemplateMonsterBaseTest {
    private HomePage homePage;

    @Test(groups = {"userLoginTests"})
    public void userCannotLoginWithInvalidEmailAndPassword() {
        //Test data

        //Test steps
    }
}

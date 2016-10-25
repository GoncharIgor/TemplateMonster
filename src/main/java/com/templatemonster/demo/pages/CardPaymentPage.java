package com.templatemonster.demo.pages;

import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.WebDriver;

/**
 * Created by i.gonchar on 30.09.2016.
 */
public class CardPaymentPage extends BasePage {
    public CardPaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCardpaymentPageOpened() {
        WaitHelper.waitAdditional(12);
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("https://www2.1stpayments.net/");
    }
}

package com.templatemonster.demo.pages.paymentPages;

import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.WebDriver;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class PayPalPage extends BasePage {
    public PayPalPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPayPalPageOpened() {
        WaitHelper.waitAdditional(5);
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("https://www.paypal.com/");
    }
}

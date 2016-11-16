package com.templatemonster.demo.pages;

import com.codeborne.selenide.Condition;
import com.google.common.base.Predicate;
import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 10.11.2016.
 */
public class TemplateDownloadPage extends BasePage {
    private By RADIO_NEW_CUSTOMER_LOCATOR = By.id("new-customer-radio");
    private By BUTTON_CONTINUE_AS_GUEST_LOCATOR_ = By.id("checkout-cont-guest");
    private By BUTTON_SUBMIT_USER_DETAILS_FORM_LOCATOR_ = By.id("freeinfo-form");
    private By FIELD_USER_NAME_LOCATOR = By.id("freeinfo-form-name");
    private By FIELD_USER_EMAIL_LOCATOR = By.id("freeinfo-form-email");
    private By FIELD_USER_PHONE_NUMBER_LOCATOR = By.id("freeinfo-form-contactphone");
    private By HEADER_ORDER_STATUS_LOCATOR = By.className("order-status-approved");
    private By ORDER_MESSAGE_LOCATOR = By.xpath(".//*[@id='content']/div/div/div/div[2]/div/div[1]/div[1]/p");

    public TemplateDownloadPage(WebDriver driver) {
        super(driver);
    }

    public TemplateDownloadPage continueAsAGuest() {
        $(RADIO_NEW_CUSTOMER_LOCATOR).shouldBe(Condition.selected);
        $(BUTTON_CONTINUE_AS_GUEST_LOCATOR_).shouldBe(Condition.enabled).click();
        return this;
    }

    public TemplateDownloadPage fillUSerDetailsForm(String userFullName, String email, String phone) {
        $(FIELD_USER_NAME_LOCATOR).shouldBe(Condition.visible).setValue(userFullName);
        $(FIELD_USER_EMAIL_LOCATOR).shouldBe(Condition.visible).setValue(email);
        $(FIELD_USER_PHONE_NUMBER_LOCATOR).shouldBe(Condition.visible).setValue(phone);
        return this;
    }

    public TemplateDownloadPage submitUserDetailsForm() {
        $(BUTTON_SUBMIT_USER_DETAILS_FORM_LOCATOR_).submit();
        return this;
    }

    public boolean checkOrderStatus(String email) {
        waitForLoad(driver);
        WaitHelper.waitAdditional(7);
        $(ORDER_MESSAGE_LOCATOR).shouldHave(Condition.text(email));
        return driver.findElement(HEADER_ORDER_STATUS_LOCATOR).getText().equalsIgnoreCase("Order is successfully accomplished");


    }


}

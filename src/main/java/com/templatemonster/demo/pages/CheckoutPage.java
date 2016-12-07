package com.templatemonster.demo.pages;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.pages.paymentPages.CardPaymentPage;
import com.templatemonster.demo.pages.paymentPages.PayPalPage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class CheckoutPage extends BasePage {
    private By CHECKOUT_EMAIL_LOCATOR = By.id("signin3-form-email");
    private By CHECKOUT_PASSWORD_LOCATOR = By.xpath(".//*[@id='signin3-form-password']");
    private By CHECKOUT_NAME_AND_SURNAME_LOCATOR = By.id("billinginfo3-form-fullname");
    private By CHECKOUT_PHONE_NUMBER_LOCATOR = By.id("billinginfo3-form-phone");
    private By CHECKOUT_ZIP_CODE_LOCATOR = By.id("billinginfo3-form-postalcode");
    private By CHECKOUT_CITY_LOCATOR = By.id("billinginfo3-form-cityname");
    private By PAYPAL_BUTTON_LOCATOR = By.id("checkout-payment-buy-PayPal");
    private By TRANSACTPRO_BUTTON_LOCATOR = By.id("checkout-payment-buy-TransactPro");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageOpened() {
        WaitHelper.waitAdditional(2);
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://secure.templatemonster.com/checkout.php");
    }

    public CheckoutPage addEmailToCheckout(String email) {
        $(CHECKOUT_EMAIL_LOCATOR).clear();
        driver.findElement(CHECKOUT_EMAIL_LOCATOR).sendKeys(email, Keys.ENTER);
        return this;
    }

    public CheckoutPage addValidBillingInformation(String name, String surname, String phoneNumber, String postalCode) {
        WaitHelper.waitAdditional(2);
        $(CHECKOUT_NAME_AND_SURNAME_LOCATOR).sendKeys(name + " " + surname);
        $(CHECKOUT_ZIP_CODE_LOCATOR).sendKeys(postalCode);
        $(CHECKOUT_PHONE_NUMBER_LOCATOR).sendKeys(phoneNumber);
        $(CHECKOUT_PHONE_NUMBER_LOCATOR).sendKeys(phoneNumber);
        driver.findElement(CHECKOUT_CITY_LOCATOR).sendKeys(Keys.ENTER);
        WaitHelper.waitAdditional(2);
        return this;
    }

    public PayPalPage payCheckoutViaPayPal() {
        WaitHelper.waitAdditional(5);
        $(PAYPAL_BUTTON_LOCATOR).click();
        return new PayPalPage(driver);
    }

    public CardPaymentPage payCheckoutViaCard() {
        WaitHelper.waitAdditional(3);
        $(TRANSACTPRO_BUTTON_LOCATOR).click();
        return new CardPaymentPage(driver);
    }

    public CheckoutPage checkoutUserAuthorization(String email, String password) {
        $(CHECKOUT_EMAIL_LOCATOR).clear();
        $(CHECKOUT_EMAIL_LOCATOR).shouldBe(Condition.visible).sendKeys(email, Keys.ENTER);
        WaitHelper.waitAdditional(3);
        $(CHECKOUT_PASSWORD_LOCATOR).sendKeys(password, Keys.ENTER);
        WaitHelper.waitAdditional(2);
        return this;
    }

    public boolean addInvalidEmailsToCheckout(List<String> invalidEmails) {
        int alertNotificationsAmount = 0;
        for (String email : invalidEmails) {
            addInvalidEmailToCheckout(email);
            if (isInvalidEmailNotificationDisplayed()) {
                alertNotificationsAmount++;
            }
        }
        return alertNotificationsAmount == invalidEmails.size();
    }

    private void addInvalidEmailToCheckout(String email) {
        $(CHECKOUT_EMAIL_LOCATOR).clear();
        driver.findElement(CHECKOUT_EMAIL_LOCATOR).sendKeys(email, Keys.ENTER);
    }

    private boolean isInvalidEmailNotificationDisplayed() {
        if ($(CHECKOUT_EMAIL_LOCATOR).equals(driver.switchTo().activeElement())) {
            return true;
        }
        return false;
    }
}

package com.templatemonster.demo.pages;

import com.templatemonster.demo.pages.basePages.BasePage;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by i.gonchar on 29.09.2016.
 */
public class CheckoutPage extends BasePage {
    private final By CHECKOUT_EMAIL_LOCATOR = By.id("signin3-form-email");
    private final By CHECKOUT_PASSWORD_LOCATOR = By.xpath(".//*[@id='signin3-form-password']");
    private final By CHECKOUT_NAME_AND_SURNAME_LOCATOR = By.id("billinginfo3-form-fullname");
    private final By CHECKOUT_PHONE_NUMBER_LOCATOR = By.id("billinginfo3-form-phone");
    private final By CHECKOUT_ZIP_CODE_LOCATOR = By.id("billinginfo3-form-postalcode");
    private final By CHECKOUT_CITY_LOCATOR = By.id("billinginfo3-form-cityname");

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
        driver.findElement(By.id("checkout-payment-buy-PayPal")).click();
        return new PayPalPage(driver);
    }

    public CardPaymentPage payCheckoutViaCard() {
        WaitHelper.waitAdditional(3);
        driver.findElement(By.id("checkout-payment-buy-TransactPro")).click();
        return new CardPaymentPage(driver);
    }

    public CheckoutPage checkoutUserAuthorization(String email, String password) {
        $(CHECKOUT_EMAIL_LOCATOR).clear();
        driver.findElement(CHECKOUT_EMAIL_LOCATOR).sendKeys(email, Keys.ENTER);
        WaitHelper.waitAdditional(3);
        $(CHECKOUT_PASSWORD_LOCATOR).sendKeys(password, Keys.ENTER);
        WaitHelper.waitAdditional(2);
        return this;
    }

    public boolean addInvalidEmailsToCheckout(String... invalidEmails) {
        int alertNotificationsAmount = 0;
        for (String email : invalidEmails) {
            addInvalidEmailToCheckout(email);
            if (isInvalidEmailNotificationDisplayed()) {
                alertNotificationsAmount++;
            }
        }
        return alertNotificationsAmount == invalidEmails.length;
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

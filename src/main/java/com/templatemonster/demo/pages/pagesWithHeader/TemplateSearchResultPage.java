package com.templatemonster.demo.pages.pagesWithHeader;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.CheckoutPage;
import com.templatemonster.demo.pages.TemplateDownloadPage;
import com.templatemonster.demo.pages.basePages.BasePageHeader;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TemplateSearchResultPage extends BasePageHeader {
    private By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Add to Cart')]");
    private By CHECKOUT_NOW_BUTTON_LOCATOR = By.xpath("//button[contains(.,'Checkout Now ')]");
    private By SHARE_AND_DOWNLOAD_BUTTON_LOCATOR = By.className("download-outer");
    private By TWITTER_LOGO_LOCATOR = By.className("onp-sl-feature-overlay");
    private By TEMPLATE_HEADING_LOCATOR = By.cssSelector(".preview-heading.overflowed-hidden");
    private By PREVIEW_IMAGE_LOCATOR = By.className("js-preview-scr");
    private By TEMPLATE_INFORMATION_TABS_LOCATOR = By.cssSelector("#previewTab li");
    private By CLOSE_MODAL_WINDOW_LOCATOR = By.xpath(".//*[@id='cart-popup']/div/div/div[1]/a");
    private By SERVICE_LOCATOR = By.cssSelector(".checkbox-list li a");

    public TemplateSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTemplateOpened(String templateId) {
        String expectedUrl = "http://www.templatemonster.com/wordpress-themes/" + templateId + ".html";
        String currentUrl = driver.getCurrentUrl();

        return currentUrl.equals(expectedUrl);
    }

    public CheckoutPage addOpenedTemplateToCartAndCheckout() {
        addToCart();
        //TO ADD: CHECK IF TEMPLATE WAS ADDED TO CART
        WaitHelper.waitAdditional(3);
        driver.findElement(CHECKOUT_NOW_BUTTON_LOCATOR).click();
        return new CheckoutPage(driver);
    }

    public TemplateSearchResultPage addTemplateToCartWithoutCheckout() {
        addToCart();
        $(CLOSE_MODAL_WINDOW_LOCATOR).click();
        return this;
    }

    public TemplateSearchResultPage addServiceToTemplate(int serviceNumber){
        $$(SERVICE_LOCATOR).get(serviceNumber - 1).click();
        return this;
    }

    private void addToCart(){
        driver.findElement(ADD_TO_CART_BUTTON_LOCATOR).click();
        driver.switchTo().activeElement();
    }


    public TemplateSearchResultPage hoverShareAndDownloadButton() {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(SHARE_AND_DOWNLOAD_BUTTON_LOCATOR)).perform();
        return this;
    }

    public TemplateDownloadPage shareTemplateWithTwitter() {
        $(TWITTER_LOGO_LOCATOR).click();
        switchToWindow(1);
        driver.close();
        switchToWindow(0);
        driver.findElement(By.id("preview-download-free-tmpl-51682")).click();
        return new TemplateDownloadPage(driver);
    }

    public TemplateSearchResultPage scrollPageDownToImageNumber(int imageNumber) {
        javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true);", $(By.xpath(".//*[@id='tab-overview']//span[" + imageNumber + "]/img")));
        $(By.xpath(".//*[@id='tab-overview']//span[" + imageNumber + "]/img")).shouldBe(Condition.visible);
        return this;
    }

    public TemplateSearchResultPage checkTemplateHeading(String heading) {
        $(TEMPLATE_HEADING_LOCATOR).shouldHave(text(heading));
        return this;
    }

    public TemplateSearchResultPage checkPrevievImageIsVisible() {
        $(PREVIEW_IMAGE_LOCATOR).shouldBe(Condition.visible);
        return this;
    }

    public TemplateSearchResultPage checkWhatTemplateInfoTabIsSelected(int tabNumber) {
        int selectedTab = 0;
        $$(TEMPLATE_INFORMATION_TABS_LOCATOR).shouldHave(CollectionCondition.size(3));
        for (int i = 1; i <= $$(TEMPLATE_INFORMATION_TABS_LOCATOR).size(); i++) {
            if ($$(TEMPLATE_INFORMATION_TABS_LOCATOR).get(i - 1).getAttribute("class").equals("active")) {
                selectedTab = i;
            }
        }
        Assert.assertEquals(selectedTab, tabNumber, "Incorrect tab was opened");
        return this;
    }

    public TemplateSearchResultPage selectTemplateInfoTab(int tabNumber) {
        driver.findElement(By.cssSelector("#previewTab li:nth-child(" + tabNumber + ")")).click();
        return this;
    }
}

package com.templatemonster.demo.pages.pagesWithHeader;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePageHeader;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingCartPage extends BasePageHeader {
    private By ORDER_TOTAL_LOCATOR = By.cssSelector(".price-block .template-price.js-total-price");
    private String DELETE_TEMPLATE_STRING_LOCATOR = "sc-dell-template-";
    private String TEMPLATE_HEADER_STRING_LOCATOR = ".cart-summary-item.js-item-template.template-";
    private By ALL_TEMPLATES_IN_CART = By.cssSelector(".cart-summary-content.js-cart-summary-content > li");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage checkOrderTotalPrice(int expectedAmount) {
        WaitHelper.waitAdditional(5);
        $(ORDER_TOTAL_LOCATOR).shouldHave(Condition.text(Integer.toString(expectedAmount)));
        return this;
    }

    public ShoppingCartPage deleteAllTemplatesFromCart() {
        List<String> allTemplates = getAllTemplateIDsStoredInChart();
        if (allTemplates.size() == 0) {
            LOGGER.info("Cart is empty");
        }
        for (int i = 0; i < allTemplates.size(); i++) {
            deleteSelectedTemplate(allTemplates.get(i));
        }
        WaitHelper.waitAdditional(3);
        return this;
    }

    public ShoppingCartPage deleteSelectedTemplate(String templateId) {
        String selectedTemplateId = DELETE_TEMPLATE_STRING_LOCATOR + templateId;
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.cssSelector(TEMPLATE_HEADER_STRING_LOCATOR + templateId + " h3"))).perform();
        $(By.id(selectedTemplateId)).click();
        WaitHelper.waitAdditional(1);
        return this;
    }

    private List<String> getAllTemplateIDsStoredInChart() {
        List<String> listOfTemplate = new ArrayList<>();
        for (int i = 0; i < $$(ALL_TEMPLATES_IN_CART).size(); i++) {
            listOfTemplate.add($$(ALL_TEMPLATES_IN_CART).get(i).getAttribute("class").replaceAll("\\D", ""));
        }
        return listOfTemplate;
    }

}

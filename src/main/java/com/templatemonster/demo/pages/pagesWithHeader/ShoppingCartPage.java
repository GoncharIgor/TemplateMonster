package com.templatemonster.demo.pages.pagesWithHeader;

import com.codeborne.selenide.Condition;
import com.templatemonster.demo.pages.basePages.BasePageHeader;
import com.templatemonster.demo.util.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingCartPage extends BasePageHeader {
    private By ORDER_TOTAL_LOCATOR = By.cssSelector(".price-block .template-price.js-total-price");
    private String DELETE_TEMPLATE_STRING_LOCATOR = "sc-dell-template-";
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
        for (int i = 0; i < allTemplates.size(); i++) {
            deleteSelectedTemplate(allTemplates.get(i));
        }
        return this;
    }

    public ShoppingCartPage deleteSelectedTemplate(String templateId) {
        String selectedTemplateId = DELETE_TEMPLATE_STRING_LOCATOR + templateId;
        $(By.id(selectedTemplateId)).click();
        WaitHelper.waitAdditional(1);
        return this;
    }

    private List<String> getAllTemplateIDsStoredInChart() {
        List<String> listOfTemplate = new ArrayList<>();
        for (int i = 0; i < $$(ALL_TEMPLATES_IN_CART).size(); i++) {
            listOfTemplate.add($$(ALL_TEMPLATES_IN_CART).get(i).getCssValue("class").replace("\\D", ""));
        }
        return listOfTemplate;
    }

}

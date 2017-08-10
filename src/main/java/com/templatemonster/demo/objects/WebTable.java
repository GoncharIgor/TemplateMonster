package com.templatemonster.demo.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTable {

    private WebElement webTable;

    public WebTable(WebElement element) {
        setWebTable(element);
    }

    public WebElement getWebTable() {
        return webTable;
    }

    public void setWebTable(WebElement webTable) {
        this.webTable = webTable;
    }

    public int getRowCount() {
        List<WebElement> tableRows = getTableRows();
        return tableRows.size();
    }

    public int getColumnCount() {
        List<WebElement> tableRows = getTableRows();
        WebElement headerRow = tableRows.get(0);
        List<WebElement> tableColumns = headerRow.findElements(By.tagName("td"));
        return tableColumns.size();
    }

    public WebElement getCell(int rowIndex, int ColIndex) {
        List<WebElement> tableRows = getTableRows();
        WebElement currentRow = tableRows.get(rowIndex - 1);
        List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
        return tableCols.get(ColIndex - 1);
    }

    public String getCellValue(int rowIndex, int colIndex) {
        return getCell(rowIndex, colIndex).getText();
    }

    private List<WebElement> getTableRows() {
        return getWebTable().findElements(By.tagName("tr"));
    }

}

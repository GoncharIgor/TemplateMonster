package com.templatemonster.demo.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//.xls files are managed

public class ExcelUtils extends BaseUtils {
    private HSSFWorkbook excelWBook;
    private HSSFSheet excelWSheet;
    private Cell cell;

    public ExcelUtils(String filePath) {
        initializeExcelFile(filePath);
    }

    public Object[][] getWholeTableArrayFromExcelFile() {
        String[][] tabArray;

        int startRow = 1;
        int startCol = 1;
        int ci, cj;
        int totalRows = excelWSheet.getLastRowNum();
        int totalCols = 2;

        tabArray = new String[totalRows][totalCols];
        ci = 0;
        for (int i = startRow; i <= totalRows; i++, ci++) {
            cj = 0;
            for (int j = startCol; j <= totalCols; j++, cj++) {
                tabArray[ci][cj] = getCellData(i, j);
            }
        }

        return (tabArray);
    }

    public List<String> getDataFromOneRowInExcelFile(int rowNumber) {
        List<String> result = new ArrayList<>();
        int noOfColumns = excelWSheet.getRow(rowNumber).getPhysicalNumberOfCells();

        for (int i = 1; i < noOfColumns; i++) {
            result.add(getCellData(rowNumber, i));
        }

        return result;
    }

    private void initializeExcelFile(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            excelWBook = new HSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheetAt(0);
            //excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            LOGGER.error("Could not find the Excel sheet");
            e.printStackTrace();
        }
    }

    private String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            int dataType = cell.getCellType();
            if (dataType == 3) {
                return "";
            } else {
                String CellData = cell.getStringCellValue();
                return CellData;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw (e);
        }
    }

    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                new Object[]{"aas", "23", "asd"},
                new Object[]{"aas", "23", "asd"},
                new Object[]{"aas", "23", "asd"}
        };
    }
}

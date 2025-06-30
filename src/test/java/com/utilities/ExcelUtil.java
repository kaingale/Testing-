package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;


public class ExcelUtil {

    public static Object[][] getExcelData(String sheetName, String FILE_PATH) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {
        	 Sheet sheet = workbook.getSheet(sheetName);
        	
        	 if (sheet == null) {
                 System.err.println("Error: Sheet '" + sheetName + "' not found.");
                 return new Object[0][0];
             }

             int rowCount = sheet.getPhysicalNumberOfRows();
             int colCount = sheet.getRow(0).getLastCellNum();
             
             Object[][] data = new Object[rowCount - 1][colCount];

             for (int i = 1; i < rowCount; i++) {  
                 Row row = sheet.getRow(i);
                 for (int j = 0; j < colCount; j++) {
                	 Cell cell = (row != null) ? row.getCell(j) : null;
                     data[i - 1][j] = (cell != null) ? cell.toString() : "";
                 }
                 
             }
             return data;
         } catch (IOException e) {
             e.printStackTrace();
             return new Object[0][0];
         }
    }
}


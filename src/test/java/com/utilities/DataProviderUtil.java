package com.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	
	@DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return ExcelUtil.getExcelData("Sheet1","To be added");  // data from excel
    }
}

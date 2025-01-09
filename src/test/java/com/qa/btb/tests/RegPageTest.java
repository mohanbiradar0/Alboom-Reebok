package com.qa.btb.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.qa.btb.base.BaseTest;
import com.qa.btb.constants.AppConstants;
import com.qa.btb.utils.CSVUtil;
import com.qa.btb.utils.ExcelUtil;
import com.qa.btb.utils.StringUtils;

public class RegPageTest extends BaseTest{
	
@BeforeClass
public void regSetup() {
	registrationPage=loginPage.navigateToRegisterPage();
}
@DataProvider
public Object[][] getUserRegTestData()
{
	return new Object[][]
			{
		{"mohan","biradar","tester@123"},
		{"rahul","biradar","tester@123"},
		{"abhishek","biradar","tester@123"}
			};
}
@DataProvider
public Object[][] getUserRegTestDataFromExcel()
{
	return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
}
@DataProvider
public Object[][] getUserRegTestDataFromCSV()
{
	return CSVUtil.csvData(AppConstants.REGISTER_CSV_SHEET_NAME);
}

@Test (dataProvider = "getUserRegTestData")
public void userRegTest(String firstName,String lastName,String password)
{
Assert.assertTrue(registrationPage.userRegister(firstName,lastName,StringUtils.getRandomEmailId(),password));
	
}











}










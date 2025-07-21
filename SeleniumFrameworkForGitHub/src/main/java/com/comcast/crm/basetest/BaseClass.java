package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	//create objects
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fLib=new FileUtility();
	public  ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	
	public WebDriver driver;
	public static WebDriver sdriver;
	
	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("==Connect to DB , Reort config==");
		dbLib.getDbconnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass
//	public void configBC(@Optional() String broswer) throws Throwable {
		public void configBC() throws Throwable {
		System.out.println("==Lunch the Browser==");
//		String BROWSER= broswer;
		String BROWSER= fLib.getDataFromPropertiesFile("browser");

		if(BROWSER.contains("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.contains("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		
	}
	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("==login==");
		String URL= fLib.getDataFromPropertiesFile("url");
		System.out.println("U:"+URL);
		String USERNAME= fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
			
	}
	@AfterMethod
	public void configAM() {
		System.out.println("==logout==");
		HomePage hp=new HomePage(driver);
		hp.logout(driver);	
	}
	@AfterClass
	public void configAC() {
		System.out.println("==close the Browser==");
		driver.quit();
		
	}
	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("==close DB , Report backup ==");
		dbLib.closeDbconnection();
	}

}

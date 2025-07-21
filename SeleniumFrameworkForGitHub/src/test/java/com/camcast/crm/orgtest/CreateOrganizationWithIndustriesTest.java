package com.camcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws Throwable {
		
		
		//create object
				FileUtility fLib=new FileUtility();
				ExcelUtility eLib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
				WebDriverUtility wLib=new WebDriverUtility();

				
				String BROWSER=fLib.getDataFromPropertiesFile("browser");
				String URL=fLib.getDataFromPropertiesFile("url");
				String USERNAME=fLib.getDataFromPropertiesFile("username");
				String PASSWORD=fLib.getDataFromPropertiesFile("password");
				
				//genarate random number		
				//read test script data from excel
				String orgName= eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
				String industry= eLib.getDataFromExcel("org", 4, 3);
				String type= eLib.getDataFromExcel("org", 4, 4);
				
				
		WebDriver driver;
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
		
		//useing implicit wait
		wLib.waitForPageToLoad(driver);
		
	// step 1: login to app
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2: navigate to organization module
		driver.findElement(By.xpath("(//a[contains(text(),'Organizations')])[1]")).click();
		
		//step 3: click on create organization button
		driver.findElement(By.xpath("//img[contains(@alt,'Create Organization')]")).click();
		
		//step 4: enter all the details created new Orgnanization
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		
		WebElement webele = driver.findElement(By.name("industry"));
		wLib.selectVisibleTest(webele, industry);
		
		WebElement webele1 = driver.findElement(By.name("accounttype"));
		wLib.selectVisibleTest(webele1, type);
		
		//click save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//verify the industries and type info
		String actIndutries=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndutries.contains(industry))
		{
			System.out.println(industry + "Energy information is verified ==PASS ");
		}
		else
		{
			System.out.println(industry + "Energy information is not verified ==FAIL");
		}
		
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type))
		{
			System.out.println(type + " Press information is verified ==PASS ");
		}
		else
		{
			System.out.println(type + " Press information is verified ==FAIL");
		}
		
		//click sign out  
		driver.quit();
		

	}

}

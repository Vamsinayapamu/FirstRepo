package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable {
		
		
		//create Object
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
				String lastName= eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();
		
		
			
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
		
	// step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2: navigate to Contacts module
		driver.findElement(By.xpath("(//a[contains(text(),'Contacts')])[1]")).click();
		
		//step 3: click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		
		//step 4: enter all the details created new contact
		
		String startDate=jLib.getStartDateYYYYMMDD();
		String endDate=jLib.getEndDateYYYYDDMM(30);
		
      //  String startDate=jLib.getSystemDateYYYYMMDD();
      //  String endDate = jLib.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		//click save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		
		//verify Header msg expected results
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate + "start date is verified ==PASS ");
		}
		else
		{
			System.out.println(startDate + "start date is not verified ==FAIL");
		}
		
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate))
		{
			System.out.println(endDate + "End date is verified ==PASS ");
		}
		else
		{
			System.out.println(endDate + "End date is not verified ==FAIL");
		}
		
		
		//click sign out  
		driver.quit();

	}

}

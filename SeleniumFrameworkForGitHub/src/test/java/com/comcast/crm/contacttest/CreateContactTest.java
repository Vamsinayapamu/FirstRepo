package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

public class CreateContactTest {

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
		String lastName= eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
					
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
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
				
				//click save button
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//verify Header msg expected results
				String actLastName=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(actLastName.contains(lastName))
				{
					System.out.println(lastName + "last name is verified ==PASS ");
				}
				else
				{
					System.out.println(lastName + "last name is not verified ==FAIL");
				}
			
				//click sign out  
				driver.quit();
		
	}

}

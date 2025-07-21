package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.mysql.cj.jdbc.Driver;

public class CreateContectWithOrgTest {

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
				String orgName= eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
				String contactLastName = eLib.getDataFromExcel("contact", 7, 3);	
				
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
				wLib.waitForPageToLoad(driver);
				
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
				
				//click save button
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//verify Header msg expected results
				String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgName))
				{
					System.out.println(orgName + "header verified ==PASS ");
				}
				else
				{
					System.out.println(orgName + "header is not verified ==FAIL");
				}
				
				//step 5: navigate to cantact module
				driver.findElement(By.xpath("(//a[contains(text(),'Contacts')])[1]")).click();
				
				//step 6: click on create contact button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//step 7: enter all the details created new contact
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactLastName);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				wLib.switchToTabOnURL(driver, "module=Accounts");
				
				
				driver.findElement(By.name("search_text")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				
				//switch to parent window
				wLib.switchToTabOnURL(driver, "Contacts&action");
				
				//click save button
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//verify Header msg expected results
				headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(contactLastName))
				{
					System.out.println(contactLastName + "header is verified ==PASS ");
				}
				else
				{
					System.out.println(contactLastName + "header is not verified ==FAIL");
				}
				
	
	//verify Header orgName info Expected Result
	String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actOrgName);

	if(actOrgName.trim().contains(orgName))
	{
		System.out.println(orgName + "information is created  ==PASS ");
	}
	else
	{
		System.out.println(orgName + "information is not created ==FAIL");
	}
	
	driver.quit();
	}
	

}

package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewProductPage {
	
	WebDriver driver;
	public CreatingNewProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(procuctName);
	@FindBy(xpath="//input[@name='productname']") WebElement enterProductName;
	//click save button
	//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	//driver.findElement(By.xpath("//input[@id='jscal_field_sales_start_date']")).sendKeys(startDate);
	@FindBy(id="jscal_field_sales_start_date") WebElement enterStarDate;
	@FindBy(id="jscal_field_sales_end_date") WebElement enterEndDate;
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") WebElement clicksaveBtn;
	
	public WebElement getclicksaveBtn()
	{
		return clicksaveBtn;
	}
	public WebElement getEnterStarDate() {
		return enterStarDate;
	}
	public WebElement getEnterEndDate() {
		return enterEndDate;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getEnterProductName() {
		return enterProductName;
	}

	public void enterProductDetails(String name)
	{
		enterProductName.sendKeys(name);
		clicksaveBtn.click();
	}
	
		
	

}

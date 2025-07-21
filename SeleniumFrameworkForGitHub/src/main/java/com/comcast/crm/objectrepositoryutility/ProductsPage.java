package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	WebDriver driver;
	public ProductsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
	@FindBy(xpath="//img[@alt='Create Product...']") WebElement productLink;
	public WebElement getproductLink()
	{
		return productLink;
	}
	
	
	
	

}

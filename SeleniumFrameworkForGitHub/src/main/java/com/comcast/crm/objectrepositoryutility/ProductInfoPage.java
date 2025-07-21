package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {

	WebDriver driver;
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//String headerInfo = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	//String actProduct=driver.findElement(By.id("mouseArea_Product Name")).getText();
	//String headerInfo = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	//String actProduct=driver.findElement(By.id("mouseArea_Product Name")).getText();
	//String actStartDate = driver.findElement(By.xpath("//td[@id='mouseArea_Sales Start Date']")).getText();
	//String actEndtDate = driver.findElement(By.xpath("//td[@id='mouseArea_Sales End Date']")).getText();

	
	
	@FindBy(xpath="//td[@id='mouseArea_Sales End Date']") WebElement actEndDate;
	@FindBy(xpath="//td[@id='mouseArea_Sales Start Date']") WebElement actstartdate;
	@FindBy(id="mouseArea_Product Name") WebElement actproduct;
	@FindBy(xpath="//span[@class='lvtHeaderText']") WebElement headerinfo;
	@FindBy(id="mouseArea_Product Name") private WebElement productNameInfo;
	@FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement productInfo;
	
	
	
	public WebElement getActEndtDate() {
		return actEndDate;
	}
	public WebElement getActstartdate() {
		return actstartdate;
	}
	public WebElement getActproduct() {
		return actproduct;
	}
	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	public String actEndtDateInfo()
	{
		return actEndDate.getText();
	}
	public String actstartdateInfo()
	{
		return actstartdate.getText();
	}
	public String actproductInfo()
	{
		return actproduct.getText();
	}
	public String headerinformation()
	{
		return headerinfo.getText();
	}
	
	
	public WebElement getproductInfo()
	{
		return productInfo;
	}
	public WebElement getProductNameInfo() {
		return productNameInfo;
	}
	
	public String productNameInfarmation()
	{
		return productNameInfo.getText();
		
	}
	public String productInfarmation()
	{
		return productInfo.getText();
		
	}
}

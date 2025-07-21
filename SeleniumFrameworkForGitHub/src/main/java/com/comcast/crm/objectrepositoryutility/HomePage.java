package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[@class='tabUnSelected']//a[text()='Organizations']") private WebElement orgBtnlink;
	@FindBy(xpath="//a[text()='Contacts']") private WebElement contactsBtnlink;
	//driver.findElement(By.xpath("//a[text()='Products']")).click();
	@FindBy(xpath="//a[text()='Products']") private WebElement productBtnLink;
	@FindBy(xpath="(//img[@style='padding: 0px;padding-left:5px'])[1]") private WebElement adminImg;
	@FindBy(linkText ="Sign Out") private WebElement signOutLink;
	
	public WebElement getcontactsBtnlink()
	{
		return contactsBtnlink;
	}
	public WebElement getorgBtnlink() {
		return orgBtnlink;
	}
	public WebElement getsignOutLink()
	{
		return signOutLink;
	}
	public WebElement getproductBtnLink()
	{
		return productBtnLink;
	}
	
	public void logout(WebDriver driver)
	{
		Actions action=new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOutLink.click();
	}
}

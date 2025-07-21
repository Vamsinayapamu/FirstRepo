package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='lastname']") private WebElement enterLastName;
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") private WebElement clickSaveLink;
	@FindBy(xpath="(//img[@title='Select'])[1]") private WebElement clickPlusBut;
	@FindBy(xpath="//input[@name='lastname']") private WebElement phoneNumber;	
	public WebElement getenterLastName()
	{
		return enterLastName;
	}
	public WebElement getclickSaveLink()
	{
		return clickSaveLink;
	}
	public void getclickPlusBut()
	{
		clickPlusBut.click();;
	}
	public void getphoneNumber(String phoneNum)
	{
		phoneNumber.sendKeys(phoneNum);;
	}
	public void enterConatctDetails(String name)
	{
		enterLastName.sendKeys(name);
		clickSaveLink.click();
	}
	public void enterConatctDetails1(String name)
	{
		enterLastName.sendKeys(name);
		clickPlusBut.click();
		//clickSaveLink.click();
	}
	
	
}

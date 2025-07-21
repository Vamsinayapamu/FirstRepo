package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname") private WebElement orgNameEnter;
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") private WebElement saveBtn;
	@FindBy(xpath="//select[@name='industry']") private WebElement industrySelect;
	@FindBy(id="phone") private WebElement phoneNumber;
	public WebElement getOrgNameEnter() {
		return orgNameEnter;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getIndustrySelect() {
		return industrySelect;
	}
	public WebElement getPhoneNumber() {
		return phoneNumber;
	}
	
	public void enterOrgName(String name)
	{	
		orgNameEnter.sendKeys(name);
		saveBtn.click();
	}
	public void enterPhoneNumber(String number)
	{	
		phoneNumber.sendKeys(number);
		saveBtn.click();
	}
	public void selectIndustry(String value)
	{
		Select se=new Select(industrySelect);
		se.selectByVisibleText(value);
	//	saveBtn.click();
	}
	
	public void phonenum(String name,String phone){
		orgNameEnter.sendKeys(name);
		phoneNumber.sendKeys(phone);
		saveBtn.click();
	}

}

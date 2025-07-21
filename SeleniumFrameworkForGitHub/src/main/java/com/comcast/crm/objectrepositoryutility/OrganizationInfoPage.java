package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[@class='dvHeaderText']") private WebElement orfInfo;
	@FindBy(xpath="//td[@id='mouseArea_Organization Name']") private WebElement orgNameInfo;
	@FindBy(xpath="//span[@id='dtlview_Industry']") private WebElement indusrtyInfo;
	@FindBy(xpath="//span[@id='dtlview_Phone']") private WebElement phNumberInfo;
	public WebElement getOrfInfo() {
		return orfInfo;
	}
	public WebElement getOrgNameInfo() {
		return orgNameInfo;
	}
	public WebElement getIndusrtyInfo() {
		return indusrtyInfo;
	}
	public WebElement getPhNumberInfo() {
		return phNumberInfo;
	}
	
	
	
	public String getOrgInfo()
	{	
		return orfInfo.getText();	
	}
	public String orgNameInfo()
	{	
		return orgNameInfo.getText();	
	}
	public String getindusrtyInfo()
	{
		return indusrtyInfo.getText();	
	}
	public String getphNumberInfo()
	{
		return phNumberInfo.getText();	
	}
	
	
}

package com.comcast.crm.objectrepositoryutility;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchToContactsPage {

	WebDriver driver;
	public SwitchToContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="search_text") private WebElement searchFiels;
	@FindBy(name="search") private WebElement searchBtn;
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") private WebElement saveBtn;
	public WebElement getSearchFiels() {
		return searchFiels;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getsaveBtn() {
		return saveBtn;
	}
	public void searchName(String name)
	{
		searchFiels.sendKeys(name);
		searchBtn.click();
	}
	
}

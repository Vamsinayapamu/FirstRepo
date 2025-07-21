package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsWithPhoneNumber {

	WebDriver driver;
	public ContactsWithPhoneNumber(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='lastname']") private WebElement lastName;
	@FindBy(name="support_start_date") private WebElement startdate;
	@FindBy(name="support_end_date") private WebElement enddate;
    @FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") private WebElement saveBtn;
	//String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
    @FindBy(id="dtlview_Support Start Date") private WebElement verifydate;
	//String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
    @FindBy(id="dtlview_Support End Date") private WebElement veridyEnddate;
    
	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getStartdate() {
		return startdate;
	}
	public WebElement getenddate()
	{
		return enddate;
	}
	public WebElement getsaveBtn()
	{
		return saveBtn;
	}
	public WebElement getverifydate()
	{
		return verifydate;
	}
	public WebElement getveridyEnddate()
	{
		return veridyEnddate;
	}
	
	public String varifyDate() {
		return verifydate.getText();
	}
	public String veridyEnddate() {
		return veridyEnddate.getText();
	}
	
	public void EnterDate(String name, String sdate,String edate)
	{
		lastName.sendKeys(name);
		startdate.clear();
		startdate.sendKeys(sdate);
		enddate.clear();
		enddate.sendKeys(edate);
		saveBtn.click();
		
	}
	
}

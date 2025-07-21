package using.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactsInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.SwitchToContactsPage;

public class CreateContactWithOrgTest extends BaseClass1{
	
	@Test
	public void createContactWithOrg_Test() throws Throwable {
		//genarate random number		
			//read test script data from excel
			String orgName= eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
			String lastName= eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();	
			
			//step 3: click on create organization button
			HomePage hp=new HomePage(driver);
			hp.getorgBtnlink().click();
			
			//step 3: click on create new organization button
			OrganizationsPage or=new OrganizationsPage(driver);
			or.getclickNewOrgLink().click();;
	        //step 4: enter all the details created new Orgnanization
			//click save button
			CreatingNewOrganizationPage cOrg=new CreatingNewOrganizationPage(driver);
			cOrg.enterOrgName(orgName);
			
			//verify Header msg expected results
			OrganizationInfoPage verify=new OrganizationInfoPage(driver);
		    String header=verify.getOrgInfo();
			//String actOrg = verify.orgNameInfo();
			if(header.contains(orgName)) {
				System.out.println(orgName + "header is verify==PASS");
			}
			else
			{
				System.out.println(orgName + "header is not verify==FAIL");
			}
			
			hp.getcontactsBtnlink().click();;
			
			//step 3: click on create contact button
			ContactsPage cp=new ContactsPage(driver);
			cp.getclickNewContactLink().click();;
			
			//step 4: enter all the details created new contact
			//click save button
	        CreatingNewContactPage cnc=new CreatingNewContactPage(driver);
	        cnc.enterConatctDetails1(lastName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

			//=====
			//switch to child window
			wLib.switchToTabOnURL(driver, "module=Accounts");

			SwitchToContactsPage scp=new SwitchToContactsPage(driver);
			{
				scp.searchName(orgName);
			}
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			
			//switch to parent window
			wLib.switchToTabOnURL(driver, "Contacts&action");
			
			//click save button
			scp.getsaveBtn().click();
			//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
			
			//verify Header msg expected results
			ContactsInfoPage cip=new ContactsInfoPage(driver);
			String headerInfo = cip.getverifyContactName();
			//String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(lastName))
			{
				System.out.println(lastName + "header is verified ==PASS ");
			}
			else
			{
				System.out.println(lastName + "header is not verified ==FAIL");
			}
			

	         //verify Header orgName info Expected Result
			OrganizationInfoPage oip=new OrganizationInfoPage(driver);
			String actOrgName = oip.orgNameInfo();
	         //String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	         System.out.println(actOrgName);

	         if(actOrgName.trim().contains(orgName))
	         {
	            System.out.println(orgName + " information is created  ==PASS ");
	         }
	         else
	         {
	            System.out.println(orgName + " information is not created ==FAIL");
	         }
	}

}

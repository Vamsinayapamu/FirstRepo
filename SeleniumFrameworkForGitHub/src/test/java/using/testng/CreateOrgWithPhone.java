package using.testng;

import java.io.IOException;

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
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithPhone extends BaseClass1 {
	
	@Test
	public void create_PhoneNumber() throws IOException, Throwable
	{	
		//read test script data from excel
		String orgName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
			
		
		HomePage hp=new HomePage(driver);
		hp.getorgBtnlink().click();
		
		OrganizationsPage or=new OrganizationsPage(driver);
		or.getclickNewOrgLink().click();
		
		CreatingNewOrganizationPage cno=new CreatingNewOrganizationPage(driver);
		cno.phonenum(orgName, phoneNumber);
		
		//verify phone number
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actPhoneNumber=oip.getphNumberInfo();
		
		if(actPhoneNumber.contains(phoneNumber))
		{
			System.out.println(phoneNumber + "Phone number verified ==PASS ");
		}
		else
		{
			System.out.println(phoneNumber + "phone number is not verified ==FAIL");
		}
	}
}

package using.testng;

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

public class CreateOrg extends BaseClass1 {

	@Test
	public void createContact_Test() throws Throwable {
				
	//read test script data from excel
	String orgName= eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
	
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
	String actOrg = verify.orgNameInfo();
	if(header.contains(orgName)) {
		System.out.println(orgName + "header is verify==PASS");
	}
	else
	{
		System.out.println(orgName + "header is not verify==FAIL");
	}
	//verify header orgName is expected Result
	if(actOrg.contains(orgName)) {
		System.out.println(orgName + "orgName is verify==PASS");
	}
	else
	{
		System.out.println(orgName + "orgName is not verify==FAIL");
	}
	
	}
	
	
}

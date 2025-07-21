package createorganization;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass1{

	//1
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
	System.out.println("==H"+ header +" =A"+ actOrg +"==");
	boolean status= header.contains(orgName);
	Assert.assertEquals(status, true);
	/*
	if(header.contains(orgName)) {
		System.out.println(orgName + "header is verify==PASS");
	}
	else
	{
		System.out.println(orgName + "header is not verify==FAIL");
	} */
	//verify header orgName is expected Result
	boolean status1= actOrg.contains(orgName);
	Assert.assertEquals(status1, true);
	/*
	if(actOrg.contains(orgName)) {
		System.out.println(orgName + "orgName is verify==PASS");
	}
	else
	{
		System.out.println(orgName + "orgName is not verify==FAIL");
	} */
	
	}
	
	// 2
	@Test
	public void createOrg_Industry() throws Throwable {

		// read test script data from excel
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industryName = eLib.getDataFromExcel("org", 4, 3);

		// step 3: click on create organization button
		HomePage hp = new HomePage(driver);
		hp.getorgBtnlink().click();

		// step 3: click on create new organization button
		OrganizationsPage or = new OrganizationsPage(driver);
		or.getclickNewOrgLink().click();
		;
		// step 4: enter all the details created new Orgnanization
		// click save button
		CreatingNewOrganizationPage cOrg = new CreatingNewOrganizationPage(driver);
		cOrg.enterOrgName(orgName);

		cOrg.selectIndustry(industryName);
		// verify Header msg expected results
		OrganizationInfoPage verify = new OrganizationInfoPage(driver);
		String header = verify.getOrgInfo();
		String actOrg = verify.orgNameInfo();
		String actIndustry = verify.getindusrtyInfo();

		System.out.println(actIndustry);
		boolean status2 = header.contains(orgName);
		Assert.assertEquals(status2, true);

		/*
		 * if(header.contains(orgName)) { System.out.println(orgName +
		 * " header is verify==PASS"); } else { System.out.println(orgName +
		 * " header is not verify==FAIL"); }
		 */
		// verify header orgName is expected Result
		boolean status3 = actOrg.contains(orgName);
		Assert.assertEquals(status3, true);
		/*
		 * if(actOrg.contains(orgName)) { System.out.println(orgName +
		 * " orgName is verify==PASS"); } else { System.out.println(orgName +
		 * " orgName is not verify==FAIL"); }
		 */
		boolean status4 = actIndustry.contains(industryName);
		Assert.assertEquals(status4, true);
		/*
		 * if(actIndustry.contains(industryName)) { System.out.println(industryName +
		 * " industryName is verify==PASS"); } else { System.out.println(industryName +
		 * " industryName is not verify==FAIL"); }
		 */
	}
	
	// 3
	@Test
	public void create_PhoneNumber() throws IOException, Throwable {
		// read test script data from excel
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getorgBtnlink().click();

		OrganizationsPage or = new OrganizationsPage(driver);
		or.getclickNewOrgLink().click();

		CreatingNewOrganizationPage cno = new CreatingNewOrganizationPage(driver);
		cno.phonenum(orgName, phoneNumber);

		// verify phone number
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getphNumberInfo();
		boolean status5 = actPhoneNumber.contains(phoneNumber);
		Assert.assertEquals(status5, true);
		/*
		 * if(actPhoneNumber.contains(phoneNumber)) { System.out.println(phoneNumber +
		 * "Phone number verified ==PASS "); } else { System.out.println(phoneNumber +
		 * "phone number is not verified ==FAIL"); }
		 */
	}
		

	
}

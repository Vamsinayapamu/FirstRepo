package using.testng;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithIndustry extends BaseClass1 {
	
	@Test
	public void createOrg_Industry() throws Throwable {
	
				//read test script data from excel
				String orgName= eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
				String industryName= eLib.getDataFromExcel("org", 4, 3);
				
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
				
				cOrg.selectIndustry(industryName);
				//verify Header msg expected results
				OrganizationInfoPage verify=new OrganizationInfoPage(driver);
			    String header=verify.getOrgInfo();
				String actOrg = verify.orgNameInfo();
				String actIndustry=verify.getindusrtyInfo();
				
				System.out.println(actIndustry);
				if(header.contains(orgName)) {
					System.out.println(orgName + " header is verify==PASS");
				}
				else
				{
					System.out.println(orgName + " header is not verify==FAIL");
				}
				//verify header orgName is expected Result
				if(actOrg.contains(orgName)) {
					System.out.println(orgName + " orgName is verify==PASS");
				}
				else
				{
					System.out.println(orgName + " orgName is not verify==FAIL");
				}
				
				if(actIndustry.contains(industryName)) {
					System.out.println(industryName + " industryName is verify==PASS");
				}
				else
				{
					System.out.println(industryName + " industryName is not verify==FAIL");
				}
	
		
}
	
	
	

}

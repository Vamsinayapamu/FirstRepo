package using.testng;

import java.time.Duration;

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
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.ContactsWithPhoneNumber;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDate extends BaseClass1 {
	@Test
	public void createContact_Test() throws Throwable {
		
		// genarate random number
		// read test script data from excel
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		// step 2: navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getcontactsBtnlink().click();
		;

		// step 3: click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getclickNewContactLink().click();

		// step 4: enter all the details created new contact

		String startDate = jLib.getStartDateYYYYMMDD();
		String endDate = jLib.getEndDateYYYYDDMM(30);

		ContactsWithPhoneNumber co = new ContactsWithPhoneNumber(driver);
		{
			co.EnterDate(lastName, startDate, endDate);
		}

		// verify Header msg expected results
		String actStartDate = co.varifyDate();
		if (actStartDate.contains(startDate)) {
			System.out.println(startDate + "start date is verified ==PASS ");
		} else {
			System.out.println(startDate + "start date is not verified ==FAIL");
		}

		String actEndDate = co.veridyEnddate();
		if (actEndDate.contains(endDate)) {
			System.out.println(endDate + "End date is verified ==PASS ");
		} else {
			System.out.println(endDate + "End date is not verified ==FAIL");
		}
	}
}

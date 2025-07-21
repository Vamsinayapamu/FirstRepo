package using.testng;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactTest extends BaseClass1 {
	@Test
	public void createContact_Test() throws Throwable {
		
		// read test script data from excel
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		String URL = fLib.getDataFromPropertiesFile("url");
		System.out.println("U:" + URL);
		// step 2: navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getcontactsBtnlink().click();
		;

		// step 3: click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getclickNewContactLink().click();

		// step 4: enter all the details created new contact
		CreatingNewContactPage cnc = new CreatingNewContactPage(driver);
		cnc.enterConatctDetails(lastName);

		// verify Header msg expected results
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actLastName = cip.getverifyContactName();
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + " last name is verified ==PASS ");
		} else {
			System.out.println(lastName + " last name is not verified ==FAIL");
		}

	}

}

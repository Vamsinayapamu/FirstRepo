package using.pom.createorgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.ContactsWithPhoneNumber;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateUsingPom {

	public static void main(String[] args) throws Throwable {
		
		
		// create Object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// genarate random number
		// read test script data from excel
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		WebDriver driver;
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

        // step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
        //driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);

        //step 2: navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getcontactsBtnlink().click();
		;

       //step 3: click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getclickNewContactLink().click();

//step 4: enter all the details created new contact

		String startDate = jLib.getStartDateYYYYMMDD();
		String endDate = jLib.getEndDateYYYYDDMM(30);

		ContactsWithPhoneNumber co = new ContactsWithPhoneNumber(driver);
		{
			co.EnterDate(lastName, startDate, endDate);
		}

//verify Header msg expected results
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

//click sign out  
		driver.quit();

	}

}

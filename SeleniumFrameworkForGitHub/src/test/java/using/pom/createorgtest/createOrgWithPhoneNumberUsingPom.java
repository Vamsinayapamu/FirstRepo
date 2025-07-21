package using.pom.createorgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class createOrgWithPhoneNumberUsingPom {

	
public static void main(String[] args) throws Throwable {
		
		
	//create object
	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtility jLib=new JavaUtility();
	WebDriverUtility wLib=new WebDriverUtility();

	
	String BROWSER=fLib.getDataFromPropertiesFile("browser");
	String URL=fLib.getDataFromPropertiesFile("url");
	String USERNAME=fLib.getDataFromPropertiesFile("username");
	String PASSWORD=fLib.getDataFromPropertiesFile("password");
	
	//genarate random number		
	//read test script data from excel
	String orgName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
	String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		
	WebDriver driver;
	if(BROWSER.contains("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if(BROWSER.contains("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else if(BROWSER.contains("edge"))
	{
		driver=new EdgeDriver();
	}
	else
	{
		driver=new ChromeDriver();
	}
	
	//useing implicit wait
	wLib.waitForPageToLoad(driver);
	
// step 1: login to app
	
//	driver.manage().window().maximize();
//	driver.get(URL);
//	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//	driver.findElement(By.id("submitButton")).click();
	LoginPage lp=new LoginPage(driver);
	lp.loginToapp(URL, USERNAME, PASSWORD);
	
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
	
	//click sign out  
	driver.quit();
	
	}

}

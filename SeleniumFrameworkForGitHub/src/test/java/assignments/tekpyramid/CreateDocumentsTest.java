package assignments.tekpyramid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateDocumentsTest {

	public static void main(String[] args) throws Throwable {
		
		//create object
				FileUtility fLib=new FileUtility();
				ExcelUtility eLib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
				WebDriverUtility wLib=new WebDriverUtility();

						
				String BROWSER=fLib.getDataFromPropertiesFile("browser");
				String URL=fLib.getDataFromPropertiesFile("url");
				System.out.println(URL);
				
				String USERNAME=fLib.getDataFromPropertiesFile("username");
				String PASSWORD=fLib.getDataFromPropertiesFile("password");
						
				//genarate random number		
				//read test script data from excel
				String documentName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
					System.out.println(documentName);		
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
				
				driver.get(URL);
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//step 1 : click document
				driver.findElement(By.xpath("//a[text()='Documents']")).click();
				//a[text()='Products']
				//click plus
				driver.findElement(By.xpath("//img[@alt='Create Document...']")).click();
				
				//enter document name
				driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(documentName);
				
				//upload file
				driver.findElement(By.xpath("//input[@name='filename']")).sendKeys
				("C:\\Users\\Vamsi\\OneDrive\\Desktop\\Vamsi Automation Resume (2).pdf");
				
				
				
				//save 
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
				//driver.quit();
		

	}

}

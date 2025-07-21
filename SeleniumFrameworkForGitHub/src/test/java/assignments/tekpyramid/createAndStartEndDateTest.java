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
import com.comcast.crm.objectrepositoryutility.CreatingNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.ProductInfoPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;

public class createAndStartEndDateTest {

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
				String procuctName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
							
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
//				driver.manage().window().maximize();
//				driver.get(URL);
//				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//				driver.findElement(By.id("submitButton")).click();
				LoginPage lp=new LoginPage(driver);
				lp.loginToapp(URL, USERNAME, PASSWORD);
				
				
				//step 2 : click products
				driver.findElement(By.xpath("//a[text()='Products']")).click();
				HomePage hp=new HomePage(driver);
				hp.getproductBtnLink().click();
				
				//step 3 : click plus botton
				//driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
				ProductsPage pg=new ProductsPage(driver);
				pg.getproductLink().click();
				
				//step 5 : enter product name
				//driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(procuctName);
				CreatingNewProductPage cnp=new CreatingNewProductPage(driver);
				cnp.getEnterProductName().sendKeys(procuctName);
				
				//enter start date
				String startDate=jLib.getStartDateYYYYMMDD();
				//driver.findElement(By.xpath("//input[@id='jscal_field_sales_start_date']")).sendKeys(startDate);
				cnp.getEnterStarDate().sendKeys(startDate);
				
				//enter end date
				String endDate=jLib.getEndDateYYYYDDMM(30);
				//cnp.getEnterEndDate().sendKeys(endDate);
				cnp.getEnterEndDate().sendKeys(endDate);
				//driver.findElement(By.xpath("//input[@id='jscal_field_sales_end_date']")).sendKeys(endDate);

				//click save button
				//getclicksaveBtn
				cnp.getclicksaveBtn().click();
				//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				//verify header msg
				//String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				//String headerInfo = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				//System.out.println("A:"+headerInfo+" E:"+procuctName);
				ProductInfoPage pip=new ProductInfoPage(driver);
				String headerInfo=pip.headerinformation();
				if(headerInfo.contains(procuctName))
				{
					System.out.println(procuctName + "is created ==PASS");
				}
				else
				{
					System.out.println(procuctName + "is not created ==FAIL");
				}
				
				//verify product as expected
				//String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
				String  actProduct=pip.actproductInfo();
				//String actProduct=driver.findElement(By.id("mouseArea_Product Name")).getText();
				if(actProduct.contains(procuctName))
				{
					System.out.println(procuctName + " product is created===PASS ");
				}
				else
				{
					System.out.println(procuctName + " product is not created===FAIL ");
				}
				
				//Start date verification
				//String actStartDate = driver.findElement(By.xpath("//td[@id='mouseArea_Sales Start Date']")).getText();
				String actStartDate=pip.actstartdateInfo();
				if(actStartDate.contains(startDate))
				{
					System.out.println(startDate + " start date is added==PASS");
				}
				else
				{
					System.out.println(startDate + " Start date is not added==FAIL");
				}
				
				//End date verification
				//String actEndtDate = driver.findElement(By.xpath("//td[@id='mouseArea_Sales End Date']")).getText();
				String actEndtDate=pip.actEndtDateInfo();
				if(actEndtDate.contains(endDate))
				{
					System.out.println(endDate + " End date is added==PASS");
				}
				else
				{
					System.out.println(endDate + " End date is not added==FAIL");
				}
				driver.quit();		
			}	
}

package using.pom.createorgtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.ProductInfoPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;

public class CreateProductTest {
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
			
			LoginPage lp=new LoginPage(driver);
			lp.loginToapp(URL, USERNAME, PASSWORD);
			
			//step 2 : click products
			//driver.findElement(By.xpath("//a[text()='Products']")).click();
			HomePage hp=new HomePage(driver);
			hp.getproductBtnLink().click();
			
			ProductsPage pg=new ProductsPage(driver);
			pg.getproductLink().click();
			
			CreatingNewProductPage cnp=new CreatingNewProductPage(driver);
			cnp.enterProductDetails(procuctName);
			
			//System.out.println("A:"+headerInfo+" E:"+procuctName);
			ProductInfoPage pi=new ProductInfoPage(driver);
			String headerInfo=pi.productInfarmation();
			if(headerInfo.contains(procuctName))
			{
				System.out.println(procuctName + "is created ==PASS");
			}
			else
			{
				System.out.println(procuctName + "is not created ==FAIL");
			}
			
			String actProduct= pi.productNameInfarmation();
			//String actProduct=driver.findElement(By.id("mouseArea_Product Name")).getText();
			if(actProduct.contains(procuctName))
			{
				System.out.println(procuctName + " is created===PASS ");
			}
			else
			{
				System.out.println(procuctName + " is not created===FAIL ");
			}
			System.out.println("A:"+actProduct+" E:"+procuctName);
			driver.quit();
		}

}

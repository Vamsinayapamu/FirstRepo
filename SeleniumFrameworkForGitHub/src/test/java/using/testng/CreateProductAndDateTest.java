package using.testng;

import java.io.IOException;

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
import com.comcast.crm.objectrepositoryutility.CreatingNewProductPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.ProductInfoPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;

public class CreateProductAndDateTest extends BaseClass1 {
	@Test
	public void create_prodct_date() throws IOException, Throwable
	{
		
		//read test script data from excel
		String procuctName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
							
		//step 2 : click products
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		HomePage hp=new HomePage(driver);
		hp.getproductBtnLink().click();
		
		//step 3 : click plus botton
		ProductsPage pg=new ProductsPage(driver);
		pg.getproductLink().click();
		
		//step 5 : enter product name
		CreatingNewProductPage cnp=new CreatingNewProductPage(driver);
		cnp.getEnterProductName().sendKeys(procuctName);
		
		//enter start date
		String startDate=jLib.getStartDateYYYYMMDD();
		cnp.getEnterStarDate().sendKeys(startDate);
		
		//enter end date
		String endDate=jLib.getEndDateYYYYDDMM(30);
		cnp.getEnterEndDate().sendKeys(endDate);

		//click save button
		cnp.getclicksaveBtn().click();
		
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
		String  actProduct=pip.actproductInfo();
		if(actProduct.contains(procuctName))
		{
			System.out.println(procuctName + " product is created===PASS ");
		}
		else
		{
			System.out.println(procuctName + " product is not created===FAIL ");
		}
		
		String actStartDate=pip.actstartdateInfo();
		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate + " start date is added==PASS");
		}
		else
		{
			System.out.println(startDate + " Start date is not added==FAIL");
		}
		
		String actEndtDate=pip.actEndtDateInfo();
		if(actEndtDate.contains(endDate))
		{
			System.out.println(endDate + " End date is added==PASS");
		}
		else
		{
			System.out.println(endDate + " End date is not added==FAIL");
		}
		
	}

}

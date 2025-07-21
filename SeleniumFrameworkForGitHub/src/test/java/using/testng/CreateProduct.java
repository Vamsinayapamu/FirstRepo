package using.testng;

import java.io.IOException;

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

public class CreateProduct extends BaseClass1{
	
	@Test
	public void create_product() throws IOException, Throwable
	{
		
		// read test script data from excel
		String procuctName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getproductBtnLink().click();

		ProductsPage pg = new ProductsPage(driver);
		pg.getproductLink().click();

		CreatingNewProductPage cnp = new CreatingNewProductPage(driver);
		cnp.enterProductDetails(procuctName);

		// System.out.println("A:"+headerInfo+" E:"+procuctName);
		ProductInfoPage pi = new ProductInfoPage(driver);
		String headerInfo = pi.productInfarmation();
		if (headerInfo.contains(procuctName)) {
			System.out.println(procuctName + "is created ==PASS");
		} else {
			System.out.println(procuctName + "is not created ==FAIL");
		}

		String actProduct = pi.productNameInfarmation();
		if (actProduct.contains(procuctName)) {
			System.out.println(procuctName + " is created===PASS ");
		} else {
			System.out.println(procuctName + " is not created===FAIL ");
		}
		
		
	}

}

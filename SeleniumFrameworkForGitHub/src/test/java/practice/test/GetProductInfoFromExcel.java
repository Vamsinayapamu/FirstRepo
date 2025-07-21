package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoFromExcel {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String phone, String name)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(phone , Keys.ENTER);
		
		//capture product info
		String s= "(//span[text()='"+name+"'])[1]/../../../..//span[@class='a-price-whole']";
		String price=driver.findElement(By.xpath(s)).getText();
		System.out.println(price);
	}

	@DataProvider
	public Object[][] getData() throws IOException, Throwable
	{
		//Object[][] objArr=new Object[3][2];
		
		ExcelUtility eLib=new ExcelUtility();
		int rowCount = eLib.getRowCount("product");
		System.out.println(rowCount);
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
			objArr[i][0] = eLib.getDataFromExcel("product", i+1, 0);
			objArr[i][0] = eLib.getDataFromExcel("product", i+1, 1);
			
		}
		return objArr;
	}
	
}

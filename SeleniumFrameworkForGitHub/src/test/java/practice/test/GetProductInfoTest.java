package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
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
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][2];
		
		objArr[0][0] = "iphone";
		objArr[0][1] = "Apple iPhone 15 (128 GB) - Black";
		
		objArr[1][0] = "iphone";
		objArr[1][1] = "Apple iPhone 15 (128 GB) - Blue";
		
		objArr[2][0] = "iphone";
		objArr[2][1] = "Apple iPhone 15 (128 GB) - Pink";
		
		return objArr;
		
		
		
	}
	
	
}

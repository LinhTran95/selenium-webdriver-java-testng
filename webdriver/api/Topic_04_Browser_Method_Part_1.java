package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Method_Part_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		//Cac ham / method/ comman de tuong tac vs browser
		// Mo trinh duyet
		driver.get("https://www.google.com/");
		
		//Dong trinh duyet- dong tab dang mo 
		driver.close();
		
		// Dong trinh duyet- Dong het cac tab/ browser dang mo
		driver.quit();
		
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/");
		
		//Lay ID, window cua tab dang dung (Active)
		driver.getWindowHandle();
		// Lay tat ca cac ID cua  tab dang dung (Active)
		driver.getWindowHandles();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	    //Phong to browser
		driver.manage().window().maximize();
	    driver.manage().window().fullscreen();
	    
	    driver.switchTo().alert();
	    driver.switchTo().frame(0);
	    driver.switchTo().window(" ");	
	    
	    
		//cac ham / method/ command d tuong tac vs element 
		//driver.findElement(By.xpath(" "));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
package api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tabs {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		
	}

	@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");
		//before click
		String parentID = driver.getWindowHandles();
		
		
		driver.findElement(By.xpath("//*[@id='k-footer']//img[@alt='facebook']")).click();
		
		swithToWindowByID(parentID);
		
		
	
		
		//after click 
		
		
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
	}
	@Test
	public void TC_02_Default_Radio_Checkbox() {
		
	}
	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		
	}
	public void swithToWindowByID(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
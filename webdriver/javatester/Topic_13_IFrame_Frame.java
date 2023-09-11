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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_IFrame_Frame {
	WebDriver driver;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	public void TC_01_WordPress() {
	//	driver.get("https://skills.kynaenglish.vn/");
	//Switch to facebook frame 	
		//Use Index
		driver.switchTo().frame(0);
		// Use Web Element 
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contain(@title,'Facebook Social Plugin']")));

	//	String facebookFrame = driver.findElement(By.xpath("//div[@class='face-content']")).getText();
	// System.out.println(facebookFrame);
		//Default content 
		driver.switchTo().defaultContent();
		//Switch to Google doc frame 
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		//Verify doc header display 
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='KHOÁ HỌC SELENIUM AUTOMATION TESTING']")).isDisplayed());
	}
	@Test
	public void TC_02_Kyna() {
		driver.switchTo().frame("cs_chat_iframe");
		
		//Switch to iframe 
		//driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@class,'favicon')]")));
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		sleepInSecond(3);
	   // Input data to required fields 
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Nguyen Van A");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123956477");
		
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByValue("60021729");
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("tinh tinh");
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(@class,'logged_in_name') and text()='Nguyen Van A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(@class,'logged_in_phone') and text()='0123956477']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@name='message' and text()='tinh tinh']")).isDisplayed());
		
		sleepInSecond(5);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		sleepInSecond(5);
		
		List<WebElement> excelText= driver.findElements(By.xpath("//div[@class='content']/h4"));
		for (WebElement text : excelText) {
			Assert.assertTrue(text.getText().contains("Excel"));
		}
	
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
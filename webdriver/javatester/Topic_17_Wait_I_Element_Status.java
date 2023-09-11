package api;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_17_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait excliptWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		excliptWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Wait for "Start" button is visible in 15 mins
		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='start']")));

	}

	@Test
	public void TC_02_Invisible() {
		driver.get("http://live.techpanda.org/");

		// Có trong DOM
		excliptWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));

		// Không có trong DOM
		excliptWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));

	}

	@Test
	public void TC_03_Presence() {
		driver.get("http://live.techpanda.org/");

		// Có trong DOM + không có trong UI (che mất)
		excliptWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));

		// Có trong DOM + có trong UI
		excliptWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search']")));

	}

	@Test
	public void TC_04_Staleness() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		// Sau khi click thì error mesage xuất hiện  -> lưu vào 1 biến
		WebElement errorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		
		// Element thay đổi trạng thái/ không còn nữa 
		driver.navigate().refresh();
		
		excliptWait.until(ExpectedConditions.stalenessOf(errorMessage));


	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
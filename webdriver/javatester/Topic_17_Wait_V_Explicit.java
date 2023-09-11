package api;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;
import java.time.temporal.WeekFields;
import java.util.Date;
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

public class Topic_17_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait excliptWait;
	String projectPath = System.getProperty("user.dir");
	String dummiesDoc = "dummies.docx";
	String nothingDoc = "nothing.docx";

	String dummiesPath = projectPath + getFileSeparator() + "uploadFile" + getFileSeparator() + dummiesDoc;
	String nothingPath = projectPath + getFileSeparator() + "uploadFile" + getFileSeparator() + nothingDoc;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		excliptWait = new WebDriverWait(driver, 45);

	}

	@Test
	public void TC_01_Enough() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();
		excliptWait = new WebDriverWait(driver, 2);

		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), 11);
	}

	@Test
	public void TC_02_Les() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();
		excliptWait = new WebDriverWait(driver, 1);

		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));

		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), 11);

	}

	@Test
	public void TC_03_Greater() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();
		excliptWait = new WebDriverWait(driver, 10);

		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));

		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), 11);

	}

	@Test
	public void TC_04() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait for Date Picker visible
		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));

		// Verified text in 'Selected Date'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),
				"No Selected Dates to display.");

		// Wait for current Date is clickable
		excliptWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Friday, June 02, 2023']")));
		driver.findElement(By.xpath("//td[@title='Friday, June 02, 2023']")).click();

		// Wait for Ajax loading icon invisible
		excliptWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));

		Assert.assertTrue(driver.findElement(By.xpath("//td[@title='Friday, June 02, 2023' and @class='rcSelected']"))
				.isDisplayed());
		// Verified text in 'Selected Date'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),
				"No Selected Dates to display.");
	}

	@Test
	public void TC_05() {
		driver.get("https://filebin.net/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(dummiesPath + "\n" + nothingPath);

		excliptWait.until(
				ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//input[@type='file']"))));

		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//a[text()='" + dummiesDoc + "']")));
		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//a[text()='" + nothingDoc + "']")));

		Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + dummiesDoc + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody//a[text()='" + nothingDoc + "']")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch blockTopic_17_Wait_V_Explicit.java
			e.printStackTrace();
		}
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	public String getFileSeparator() {
		return File.separator;

	}
}
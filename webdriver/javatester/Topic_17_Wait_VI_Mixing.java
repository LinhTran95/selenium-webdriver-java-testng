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

public class Topic_17_Wait_VI_Mixing {
	WebDriver driver;
	WebDriverWait excliptWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		excliptWait = new WebDriverWait(driver, 45);

	}

	@Test
	public void TC_01_Element_Found() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		System.out.println("Start time (ex):" + getDateTimeNow());
		excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='FirstName']")));
		System.out.println("End time (ex): " + getDateTimeNow());

		System.out.println("Start time (im):" + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("ABC");
		System.out.println("End time (im): " + getDateTimeNow());

	}

	@Test
	public void TC_02_Element_Not_Found() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		// Chờ hết timeout của implicit
		// Sau đó đánh fail testcase
		// Throw exception: NoSuchElement

		System.out.println("Start time (im):" + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("ABC");
		System.out.println("End time (im): " + getDateTimeNow());

	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		excliptWait = new WebDriverWait(driver, 5);

		System.out.println("Start time (im):" + getDateTimeNow());
		try {
			excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Contry']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time (im): " + getDateTimeNow());

	}

	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		excliptWait = new WebDriverWait(driver, 5);

		System.out.println("Start time (im):" + getDateTimeNow());
		try {
			excliptWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Contry']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time (im): " + getDateTimeNow());

	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_WebElemet() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		excliptWait = new WebDriverWait(driver, 5);

		System.out.println("Start time (im):" + getDateTimeNow());
		try {
			excliptWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='Contry']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End time (im): " + getDateTimeNow());

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
}
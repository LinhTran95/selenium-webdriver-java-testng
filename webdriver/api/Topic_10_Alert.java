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

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait excliptWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		driver = new FirefoxDriver();

		excliptWait = new WebDriverWait(driver, 10);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		excliptWait.until(ExpectedConditions.alertIsPresent());

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		excliptWait.until(ExpectedConditions.alertIsPresent());

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

	}

	@Test
	public void TC_03_Prompt_Alert() {
		String alertText = "HaHaHa";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		excliptWait.until(ExpectedConditions.alertIsPresent());

		alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(2);
		alert.sendKeys(alertText);
		sleepInSecond(2);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + alertText);
	}

	@Test
	public void TC_04_Authentication_Alert() {
		// Selenium By Pass
		// http://username:password@the-internet.herokuapp.com/basic_auth
		driver.get("http://the-internet.herokuapp.com/basic_auth");

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Congratulations! You must have the proper credentials.']"))
						.isDisplayed());

	}

	@Test
	public void TC05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(getCredentialToUrl(url, "admin", "admin"));

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Congratulations! You must have the proper credentials.']"))
						.isDisplayed());
	}

	@Test
	public void TC06_Authentication_AutoIT() throws IOException {
		String projectPath = System.getProperty("user.dir");
		String autoITScriptPath = projectPath + "\\AutoIT\\authen_firefox.exe";
		driver.get("http://the-internet.herokuapp.com/basic_auth");

		Runtime.getRuntime().exec(new String[] { autoITScriptPath, "admin", "admin" });
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Congratulations! You must have the proper credentials.']"))
						.isDisplayed());
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

	public String getCredentialToUrl(String url, String username, String password) {
		String[] newUrl = url.split("//");
		url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		return url;

	}

}
package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Part_3 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		boolean emailTextboxStatus = driver.findElement(By.xpath("//*[@id='mail']")).isDisplayed();
		if (emailTextboxStatus == true) {
			System.out.println("Email text box is displayed");
		} else {
			System.out.println("Email text box is  not displayed");
		}

		boolean educationTextbox = driver.findElement(By.xpath("//*[@id='edu']")).isDisplayed();

		if (educationTextbox == true) {
			System.out.println("Education text box is displayed");
		} else {
			System.out.println("Education text box is  not displayed");
		}

		boolean ageUnder = driver.findElement(By.xpath("//*[@id='under_18']")).isDisplayed();

		if (ageUnder == true) {
			System.out.println("Age under radio checkbox is displayed");
		} else {
			System.out.println("Age under radio checkbox is not displayed");
		}

	}
	//public boolean isElementDisplay(By by) {
		//WebElement element = driver.findElement(by);
		
	//}
	public void sendkeyToElement() {
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		boolean emailTextboxStatus = driver.findElement(By.xpath("//*[@id='mail']")).isEnabled();
		if (emailTextboxStatus == true) {
			System.out.println("Email text box is enable");
		} else {
			System.out.println("Email text box is disbale");
		}

		boolean silderOneStatus = driver.findElement(By.xpath("//*[@id='slider-1']")).isEnabled();
		if (silderOneStatus == true) {
			System.out.println("Slider one is enable");
		} else {
			System.out.println("Slider one is disbale");
		}

		boolean silderTwoStatus = driver.findElement(By.xpath("//*[@id='slider-1']")).isEnabled();
		if (silderTwoStatus == true) {
			System.out.println("Slider two is enable");
		} else {
			System.out.println("Slider two is disbale");
		}

	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Click to Age Under 18/ Language Java
		driver.findElement(By.id("java")).click();
		driver.findElement(By.xpath("//*[@id='under_18']")).click();

		// Verify element is selected
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='under_18']")).isSelected());

		// Click to Age Under 18/ Language Java
		driver.findElement(By.id("java")).click();
		driver.findElement(By.xpath("//*[@id='under_18']")).click();

		// Verify element is selected
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//*[@id='under_18']")).isSelected());

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
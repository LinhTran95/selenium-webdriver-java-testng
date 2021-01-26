package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	// WebDriverWait explicitWait;
	// JavascriptExecutor jsExecuter;
	// Actions action;
	String firstName, lastName, emaiAddress, compayName, passWord, date, month, year;
	Random rand = new Random();

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// explicitWait = new WebDriverWait(driver, 20);
		// jsExecuter = (JavascriptExecutor) driver;
		// action = new Actions(driver);
		firstName = "Bruce";
		lastName = "Wayne";
		emaiAddress = "BWayne" + rand.nextInt(999999) + "@mail.com";
		compayName = "Wayne Company";
		passWord = "123456";
		date = "30";
		month = "September";
		year = "1989";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Register() {
		// Open register page
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		// Fill information in required fields
		checkToCheckBoxOrRadio(By.xpath("//*[@id='gender-male']"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		// Select by index
		// select.selectByIndex(31);
		// sleepInSecond(5);
		// Select by value
		// select.selectByValue("15");
		// sleepInSecond(5);
		// Select by text
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		sleepInSecond(5);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// Kiem tra dropdrown co multiple ko
		// Assert.assertFalse(select.isMultiple());

		// Dropdown Multiple
		// select.getAllSelectedOptions();

		// Get all Item in drodown
		// List <WebElement> allItems = select.getOptions();
		// Assert.assertEquals(allItems.size(), 13);

		// **Dropdown Multiple: Verify chon dung hay sai
		// ** List <WebElement> itemSelected = select.getAllSelectedOptions();
		// for (WebElement item: itemSelected) {
		// item.getText();
		// }

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		sleepInSecond(5);

		driver.findElement(By.id("Email")).sendKeys(emaiAddress);
		driver.findElement(By.id("Company")).sendKeys(compayName);

		checkToCheckBoxOrRadio(By.xpath("//*[@id='Newsletter']"));

		driver.findElement(By.id("Password")).sendKeys(passWord);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passWord);

		// Register
		driver.findElement(By.id("register-button")).click();

		// Verified message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		// GO to "My account"
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		// Check correct data
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("Newsletter")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emaiAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), compayName);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		// Log out

		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

	}

	@Test
	public void TC_02_MultpleSelect() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		ArrayList<String> allItemText = new ArrayList<String>();
		allItemText.add("Automation");
		allItemText.add("Mobile");
		allItemText.add("Security");
		allItemText.add("Intergration");
		allItemText.add("Functional UI");

		for (String item : allItemText) {
			select.selectByVisibleText(item);
		}
		sleepInSecond(5);

		Assert.assertTrue(select.isMultiple());

		// Kiem tra select correct data
		List<WebElement> allSelectedItem = select.getAllSelectedOptions();
		ArrayList<String> allSelectedText = new ArrayList<String>();

		for (WebElement item : allSelectedItem) {
			allSelectedText.add(item.getText());

		}
		Assert.assertEquals(allSelectedItem.size(), 5);

	}

	public void checkToCheckBoxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();

		}
	}

	public void unCheckToCheckBox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
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

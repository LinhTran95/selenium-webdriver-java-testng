package api;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.time.temporal.WeekFields;
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

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attemp=1");

		By loginButton = By.cssSelector(".fhs-btn-register");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		// Verify Login Button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		// Input to Email/ Password textbox

		driver.findElement(By.xpath("//*[@id='login_username']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//*[@id='login_password']")).sendKeys("123");

		// Verify Login button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		driver.navigate().refresh();

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		removeDisabledAttributeByJs(loginButton);
		sleepInSecond(2);

		driver.findElement(loginButton).click();

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());

		// De-select

		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		sleepInSecond(1);

		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());

		// Select Radio
		driver.findElement(By.xpath("//input[@value='I have a strict diet']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a strict diet']")).isSelected());

//		//Click to all check boxes
//		List <WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
//		
//		//Click all checkboxes
//		for (WebElement checkbox: checkboxes)
//		{
//			checkbox.click();
//			sleepInSecond(1);
//		}
//		// Verify all checkboxes are selected 
//		for (WebElement checkbox: checkboxes)
//		{
//			Assert.assertTrue(checkbox.isSelected());
//		}
	}

	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");

		/*
		 * //Click to Radio driver.findElement(By.xpath(
		 * "//input[@value='Spring']/precending-sibling::span[@class='mat-radio-outer-circle']"
		 * )).click(); sleepInSecond(3);
		 * 
		 * //Verify
		 * Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).
		 * isSelected());
		 */

		By springRadio = By.xpath("//input[@value='Spring']");

		clickByJs(springRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());

	}

	@Test 
	public void TC_04_Customer_Radio_Checkbox_II() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//Before Click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked= 'false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		
		//After Click 
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked= 'true']")).isDisplayed());

		
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

	public void removeDisabledAttributeByJs(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("argument[0].removeAttribute('disbale')", element);
	}

	public void clickByJs(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}
}
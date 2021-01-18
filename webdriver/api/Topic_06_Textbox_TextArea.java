package api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String loginPageUrl, userID, password, customerID;
	String name, dob, addr, city, state, pin, phone, email;
	String editAddress, editState, editCity, editPin, editPhone, editEmail;
	Random rand = new Random();

	By nameTextboxBY = By.name("name");
	By genderRadioBY = By.name("gender");
	By dobTextboxBY = By.name("dob");
	By addrTextboxBY = By.name("addr");
	By cityTextboxBY = By.name("city");
	By stateTextboxBY = By.name("state");
	By pinTextboxBY = By.name("pinno");
	By phoneTextboxBY = By.name("telephoneno");
	By emailTextboxBY = By.name("emailid");
	By passwordTextboxBY = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		name = "ABC DGF";
		dob = "1999-01-01";
		addr = "G3435 DDDD";
		state = "FFFF";
		city = "GGGG";
		pin = "214141";
		phone = "35242523";
		email = "auto" + rand.nextInt(999999) + "@gmai.com";

		editAddress = "GYHGW GH";
		editCity = "PPOJDS";
		editEmail = "GH@gmail.com";
		editPhone = "0091341241";
		editState = "DFSDSE";
		editPin = "000000";
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("auto" + rand.nextInt(999999) + "@gmai.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBY).sendKeys(name);
		driver.findElement(dobTextboxBY).sendKeys(dob);
		driver.findElement(addrTextboxBY).sendKeys(addr);
		driver.findElement(stateTextboxBY).sendKeys(state);
		driver.findElement(cityTextboxBY).sendKeys(city);
		driver.findElement(pinTextboxBY).sendKeys(pin);
		driver.findElement(phoneTextboxBY).sendKeys(phone);
		driver.findElement(emailTextboxBY).sendKeys(email);
		driver.findElement(passwordTextboxBY).sendKeys(password);

		Thread.sleep(5000);
		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		// Check output

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		// Get customer ID
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// Verify 3 fields disable
		Assert.assertFalse(isElementEnabled(nameTextboxBY));
		Assert.assertFalse(isElementEnabled(genderRadioBY));
		Assert.assertFalse(isElementEnabled(dobTextboxBY));

		Assert.assertEquals(driver.findElement(nameTextboxBY).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextboxBY).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(addrTextboxBY).getAttribute("value"), addr);
		Assert.assertEquals(driver.findElement(cityTextboxBY).getAttribute("value"), city);

		Assert.assertEquals(driver.findElement(stateTextboxBY).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBY).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBY).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxBY).getAttribute("value"), email);

		// Clear data
		driver.findElement(addrTextboxBY).clear();
		driver.findElement(cityTextboxBY).clear();
		driver.findElement(stateTextboxBY).clear();
		driver.findElement(pinTextboxBY).clear();
		driver.findElement(phoneTextboxBY).clear();
		driver.findElement(emailTextboxBY).clear();

		// Edit data
		driver.findElement(addrTextboxBY).sendKeys(editAddress);
		driver.findElement(cityTextboxBY).sendKeys(editCity);
		driver.findElement(stateTextboxBY).sendKeys(editState);
		driver.findElement(pinTextboxBY).sendKeys(editPin);
		driver.findElement(phoneTextboxBY).sendKeys(editPhone);
		driver.findElement(emailTextboxBY).sendKeys(editEmail);

		Thread.sleep(5000);
		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());

		// Check output

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddress);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),
				editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),
				customerID);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else
			return false;
	}

}
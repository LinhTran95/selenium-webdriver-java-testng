package api;

import java.util.List;
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
	String loginPageUrl, userID, password, customerid;
	String name, dob, addr, city, state, pin, phone, email;
	String editAddress, editCity, editPin, editPhone, editEmail;

	By nameTextboxBY = By.name("name");
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
		city="GGGG";
		pin = "214141";
		phone = "35242523";
		email = "abcree@gmai.com";
		
		editAddress="";
		editCity="";
		editEmail="";
		editPhone="";
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("");
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

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manager Id :" + userID + "']")).isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[tex()='New Customer']")).click();

		driver.findElement(nameTextboxBY).sendKeys(name);
		driver.findElement(dobTextboxBY).sendKeys(dob);
		driver.findElement(addrTextboxBY).sendKeys(addr);
		driver.findElement(stateTextboxBY).sendKeys(state);
		driver.findElement(cityTextboxBY).sendKeys(city);
		driver.findElement(pinTextboxBY).sendKeys(pin);
		driver.findElement(phoneTextboxBY).sendKeys(phone);
		driver.findElement(emailTextboxBY).sendKeys(email);
		driver.findElement(passwordTextboxBY).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// Check output
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		

	}

	@Test
	public void TC_04_Edit_Customer() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
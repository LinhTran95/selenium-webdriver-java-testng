package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	WebDriverWait excliptWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		excliptWait = new WebDriverWait(driver, 15);
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		// Login Page Url matching
		driver.get("https://tiki.vn/");
		// Hover mouse
		WebElement vietnameseLanguage = driver
				.findElement(By.xpath("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]"));

		jsExecutor.executeScript("arguments[0].click();", vietnameseLanguage);

		sleepInSecond(10);
	}

	@Test
	public void TC_02() {
		navigateToUrlByJS("http://live.techpanda.org/");

		String homePageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homePageDomain, "live.techpanda.org");

		String homePageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageURL, "live.techpanda.org");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		hightlightElement("//a[text()='Customer Service']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", getEmailRandom());

		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");

		hightlightElement("//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));

		navigateToUrlByJS("https://demo.guru99.com/v4/");
		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

	}

	@Test
	public void TC_03() {
		navigateToUrlByJS("https://login.ubuntu.com/");

		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@name='email']"), "Please fill out this field.");

		sendkeyToElementBySelenium("//input[@name='email']", "idk");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@name='email']"),
				"Please include an '@' in the email address.");

		sendkeyToElementBySelenium("//input[@name='email']", "@");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@name='email']"),
				"Please enter a part followed by '@'.'@' is incomplete.");

		sendkeyToElementBySelenium("//input[@name='email']", "123@..");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@name='email']"),
				"'.' is used at a wrong position in '..'.");

		sendkeyToElementBySelenium("//input[@name='email']", "123@gmail.com");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@name='password']"), "Please fill out this field.");

	}

	public void TC_04() {
		String loginPageUrl, userID, password, customerID;
		String name, dob, addr, city, state, pin, phone, email;
		String editAddress, editState, editCity, editPin, editPhone, editEmail;

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

		name = "ABC DGF";
		dob = "1999-01-01";
		addr = "G3435 DDDD";
		state = "FFFF";
		city = "GGGG";
		pin = "214141";
		phone = "35242523";
		email = getEmailRandom();

		driver.get("http://demo.guru99.com/v4");
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBY).sendKeys(name);
		// Remove attribute (type=date)
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(5);
		
		driver.findElement(dobTextboxBY).sendKeys(dob);
		driver.findElement(addrTextboxBY).sendKeys(addr);
		driver.findElement(stateTextboxBY).sendKeys(state);
		driver.findElement(cityTextboxBY).sendKeys(city);
		driver.findElement(pinTextboxBY).sendKeys(pin);
		driver.findElement(phoneTextboxBY).sendKeys(phone);
		driver.findElement(emailTextboxBY).sendKeys(email);
		driver.findElement(passwordTextboxBY).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
		
		// Check output
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver2) {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void sendkeyToElementBySelenium(String locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "myname" + rand.nextInt(999) + "@gmail.com";
	}

}
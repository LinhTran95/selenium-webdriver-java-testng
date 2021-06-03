package api;

import static org.junit.Assert.assertArrayEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_2 {
	WebDriver driver;
	Action action;
	String projectPath = system.g

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

	}

	@Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contentClick(driver.findElement(By.xpath(""))).perform();
		sleepInSecond(3);
		
		//Verify Quit display
		assert.assertTrue(driver.findElement(By.xpath("")));
		
		//Hover to Quit
		
		
		
		
		
		
	}

	@Test
	public void TC_02_Drag_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");

		WebElement sourceCircle = driver.findElement(By.id(id));
		WebElement targetCirlce = driver.findElement(By.id(id));

		action.dragAndDrop(sourceCircle, targetCirlce).perform();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("")), "03a9f4");

	}

	@Test
	public void TC_03_Drag_Drop_HTML5() {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_Drag_Drop_HTML5_Xpath() {
		
	}

	public String getRGBValue(String hexaValue) {
		return Color.fromString(hexaValue).asRgb();
	}

	public String getHexaValue(String rgbValue) {
		return Color.fromString(rgbValue).asHex();
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
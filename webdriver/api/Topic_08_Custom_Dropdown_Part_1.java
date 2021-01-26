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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part_1 {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInButtonDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "1");
		sleepInSecond(4);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']  ")).getText(),
				"1");

		selectItemInButtonDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "13");
		sleepInSecond(4);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"13");

		selectItemInButtonDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "2");
		sleepInSecond(4);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"2");

		selectItemInButtonDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li", "5");
		sleepInSecond(4);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"5");

	}

	@Test
	public void TC_02_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		selectItemInButtonDropdown("//select[@name='DateOfBirthDay']/option", "//select[@name='DateOfBirthDay']/option",
				"18");
		selectItemInButtonDropdown("//select[@name='DateOfBirthMonth']/option",
				"//select[@name='DateOfBirthMonth']/option", "December");
		selectItemInButtonDropdown("//select[@name='DateOfBirthYear']/option",
				"//select[@name='DateOfBirthYear']/option", "1989");

	}

	public void selectItemInButtonDropdown(String parentXpath, String allItemXpath, String expectedText) {
		// Click vao Dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		// Cho cho den khi item hien ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		// Lay het cac item dua vao List
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		// DUng vong lap de duyet qua
		for (WebElement item : allItem) {

			// Duyet qua tung item, get text -> verify if text = expected -> clik item
			// out loop
			if (item.getText().equals(expectedText)) {
				item.click();
				break;

			}
		}

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
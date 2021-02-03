package api;

import java.awt.RenderingHints.Key;
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

public class Topic_08_Custom_Dropdown_Part_1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String month = "February";
	String firstMonths[] = { "March", "May", "June", "July" };
	String secondMonths[] = { "March", "May", "June" };

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
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

	@Test
	public void TC_03_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInButtonDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Basketball");
		sleepInSecond(3);
		Assert.assertEquals(getAngularSelecgtingText(), "Basketball");

		selectItemInButtonDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Golf");
		sleepInSecond(3);
		Assert.assertEquals(getAngularSelecgtingText(), "Golf");

		selectItemInButtonDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Badminton");
		sleepInSecond(3);
		Assert.assertEquals(getAngularSelecgtingText(), "Badminton");

	}

	@Test
	public void TC_04_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInButtonDropdown("//div[@role='listbox']", "//div[@role='option']", "Christian");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@clas='divider text']")), "Christian");

		selectItemInButtonDropdown("//div[@role='listbox']", "//div[@role='option']", "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@clas='divider text']")), "Justen Kitsune");

		selectItemInButtonDropdown("//div[@role='listbox']", "//div[@role='option']", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@clas='divider text']")), "Stevie Feliciano");
	}

	@Test
	public void TC_05_Editable01() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Albania");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//dic[@class='divider text]'")).getText(), "Albania");

		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Armenia");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//dic[@class='divider text]'")).getText(), "Armenia");

		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Belarus");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//dic[@class='divider text]'")).getText(), "Belarus");

		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Benin");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//dic[@class='divider text]'")).getText(), "Benin");

	}

	@Test
	public void TC_06_VueJs() {
		selectItemInButtonDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a")).getText(), "First Option");

		selectItemInButtonDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a")).getText(), "Second Option");

		selectItemInButtonDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a")).getText(), "Third Option");

	}

	@Test
	public void TC_07_MutilpleItemSelect() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectMultipleItemInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//div[@class='ms-drop bottom'])[1]//li//span", firstMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemIsSelected(firstMonths));

		driver.navigate().refresh();

		selectMultipleItemInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//div[@class='ms-drop bottom'])[1]//li//span", secondMonths);
		sleepInSecond(2);
		Assert.assertTrue(areItemIsSelected(firstMonths));

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

	public String getAnngularDropdownSelectedText() {
		return (String) jsExecutor
				.executeScript("return document.querySelector(\select[name='games']>option[selected]\").text");

	}

	public void selectItemInEditableDropdown(String parentXpath, String allItemXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		sleepInSecond(2);

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		for (WebElement item : allItem) {
			if (item.getText().equals(expectedText)) {
				item.click();
				break;

			}
		}

	}

	public void selectItemEditable(String parentXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);

		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);

	}

	public void selectMultipleItemInCustomDropdown(String parentXpath, String allItemXpath, String[] months) {

		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		for (String month : months) {
			for (WebElement item : allItem) {
				if (item.getText().equals(month)) {
					item.click();
					sleepInSecond(2);
					break;

				}
			}
		}
	}

	public boolean areItemIsSelected(String[] months) {

		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]")).getText();
		System.out.println("Text da chon= " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : months) {
				if (allItemSelectedText.contains(item))
					;
				{
					break;

				}
			}
			return true;
		} else if (numberItemSelected > 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[tetx()='All selected'"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {

			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[tetx()='" + numberItemSelected + "of 12 selected"))
					.isDisplayed();
		} else {
			new RuntimeException("No selected month");
			return false;
		}

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getAngularSelecgtingText() {
		return (String) jsExecutor
				.executeScript("return.document.querySelector(\"sekect[name='games']>option[slected]\").text");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
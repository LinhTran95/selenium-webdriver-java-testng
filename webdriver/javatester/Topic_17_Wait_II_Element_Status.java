package api;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.time.temporal.WeekFields;
import java.util.Date;
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

public class Topic_17_Wait_II_Element_Status {
	WebDriver driver;
	WebDriverWait excliptWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Find_Element() {
		// 1- tìm dc element
		// Nếu như tìm thấy ngay thì không cần chờ hết timeout
		// Nếu như chưa tìm thấy thì tiếp tục chờ- trong thời gian chờ mỗi 0.5s lại tiếp
		// tục tìm
		// Nếu như tìm thấy vẫn đang trong thời gian chờ thì không cần chờ timeout
		// Chuyển qua step tiếp theo

		System.out.println("Start - " + getDateTimeNow());
		driver.findElements(By.id("email"));
		System.out.println("End - " + getDateTimeNow());

		// 2- không tìm thấy element nào
		// Nó sẽ thao tác vs element đầu tiên (không quan tâm element này ẩn hay hiện)

		System.out.println("Start - " + getDateTimeNow());
		driver.findElements(By.xpath("//input"));
		System.out.println("End - " + getDateTimeNow());

		// 3- tìm thấy nhiều hơn 1 element
		// Chờ hết timeout rồi mà vẫn không tìm thấy element
		// Đánh testcase này fail ngay lập tức tại đúng step này
		// Throw 1 exception: No such element
		System.out.println("Start - " + getDateTimeNow());
		driver.findElements(By.xpath("//label"));
		System.out.println("End - " + getDateTimeNow());

	}

	@Test
	public void TC_02_Find_Elements() {
		// 1- tìm dc element
		// Nếu như tìm thấy ngay thì không cần chờ hết timeout
		// Nếu như chưa tìm thấy thì tiếp tục chờ- trong thời gian chờ mỗi 0.5s lại tiếp
		// tục tìm
		// Nếu như tìm thấy vẫn đang trong thời gian chờ thì không cần chờ timeout
		// Chuyển qua step tiếp theo
		// Lưu nó vào trong List (chỉ có duy nhất 1 element)

		System.out.println("Start - " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println(getDateTimeNow());

		// 2- không tìm thấy element nào
		// Lưu vào trong list (chứa các element thoả điều kiện)
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println(getDateTimeNow());

		// 3- tìm thấy nhiều hơn 1 element
		// Chờ hết timeout rồi mà vẫn không thấy element
		// Không đánh fail testcase
		// Không throw exception
		// Chỉ là 1 list rỗng
		// Chuyển qua step tiếp theo để chạy tiếp

		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println(getDateTimeNow());

	}
	@Test
	public void TC_03_Find_Elements() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(checkboxs.size());
		for (WebElement checkbox : checkboxs) {
			checkbox.click();
			sleepInSecond(1);
			
			
		}
		
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

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
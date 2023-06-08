package api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

public class Topic_14_Window_Tabs {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");
		// before click
		String kynaID = driver.getWindowHandle();
		System.out.println("ID of page A " + kynaID);
		// Click to fb link
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		sleepInSecond(5);

		// After click
		// Set<String> allIDs = driver.getWindowHandles();

		// Switch to Facebook page (tab)
		swithToWindowByID(kynaID);
		String faceboookID = driver.getWindowHandle();
		
		System.out.println("ID of page A " + faceboookID);
		/*
		 * for (String id : allIDs) { System.out.println("All IDS = "+ id); if
		 * (!id.equals(parentID)) { driver.switchTo().window(id); } }
		 */
		// Verified URL fb is corrected
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));

		// Switch to Kyna page (tab)
		swithToWindowByID(faceboookID);
		/*
		 * for (String id : allIDs) { System.out.println("All IDS = "+ id); if
		 * (!id.equals(parentID)) { driver.switchTo().window(id); } }
		 */
		// Verified URL Kyna is corrected
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna"));
		sleepInSecond(5);
	}

	@Test
	public void TC_02_Greater_Than_Two_Windows_Or_Tab() {
		driver.get("https://skills.kynaenglish.vn/");
		
		String kynaID = driver.getWindowHandle();
		// CLick to fb footer 
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		// Switch to fb 
		swithToWindowsByTitle("Đăng nhập Facebook");
		sleepInSecond(3);
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));
		
		// Switch to kyna 
		swithToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(3);
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna"));
		
		// Click youtube 
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		
		// Switch youtube 
		swithToWindowsByTitle("Kyna.vn - YouTube");
		sleepInSecond(3);
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));
	
		// CLick to Primus 
		driver.findElement(By.xpath("//div[@class='info']//a[text()='Thông tin hữu ích']")).click();
		
		// Switch to Primus 
		swithToWindowsByTitle("502 Server Error");
		sleepInSecond(3);
		Assert.assertTrue(driver.getCurrentUrl().contains("https://skills.kynaenglish.vn/bai-viet"));
		
		// Switch to kyna (*)
		swithToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(3);
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna"));
		
		// Close all tab instead of kyna 
		closeAllWindowWithoutParent(kynaID);
	}

	public void swithToWindowByID(String windowID) {
		// LAy het tat ca ID cua nhung window/tab dang co
		Set<String> allIDs = driver.getWindowHandles();

		// Dung vong lap
		for (String id : allIDs) {
			// Moi lan duyet qua 1 gia tri thi kiem tra 1 dieu kien
			// Neuno khong bang voi gia tri dang so sanh
			if (!id.equals(windowID)) {
				// Switch vao cai ID do
				driver.switchTo().window(id);
				sleepInSecond(2);
				// Thoat khoi vong lap
				// Dat duoc dieu kien - khong can chay tiep cac gia tri con lai
				break;
			}
		}
	}
	
	public void swithToWindowsByTitle (String expectedWindowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualWindowTitle = driver.getTitle();
			
			if (actualWindowTitle.equals(expectedWindowTitle));
		}
	}

	public void closeAllWindowWithoutParent(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
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
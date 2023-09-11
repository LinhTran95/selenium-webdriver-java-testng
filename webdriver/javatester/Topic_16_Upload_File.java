package api;

import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_16_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pic1File = "pic_1.jpg";
	String pic2File = "pic_2.jpg";
	String pic3File = "pic_3.jpg";
	String seleniumPath = projectPath + getFileSeparator() + "uploadFile" + getFileSeparator() + pic1File;
	String seleniumPath2 = projectPath + getFileSeparator() + "uploadFile" + getFileSeparator() + pic2File;
	String seleniumPath3 = projectPath + getFileSeparator() + "uploadFile" + getFileSeparator() + pic3File;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Upload_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));

		uploadFile.sendKeys(seleniumPath);

		sleepInSecond(3);

		// Verify file upload success
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1File + "']")).isDisplayed());

		// Click Start upload
		driver.findElement(By.cssSelector(".files .start")).click();

		// Verify file is uploaded
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1File + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multi_File() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(seleniumPath + "\n" + seleniumPath2 + "\n" + seleniumPath3);

		// Verify file is uploaded
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1File + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2File + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic3File + "']")).isDisplayed());

		// Click Start upload for each files
		List<WebElement> startButtons = driver.findElements(By.cssSelector(".files .start"));

		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(3);
		}
		
		// Verify file is uploaded
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1File + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2File + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + pic3File + "']")).isDisplayed());


	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileSeparator() {
		return File.separator;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

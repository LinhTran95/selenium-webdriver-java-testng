package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Part_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Web_Element_Command() {
		//Thao tac truc tiep len element + khai bao bien 
		//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("selenium_19@gmail.com");
		// Khai bao bien roi moi thao tac 
		WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='pass']"));
		passwordTextbox.sendKeys("123456");
		
		// xoa di
		passwordTextbox.clear();
		
		// Nhap lai gia tri moi
		passwordTextbox.sendKeys("654321");
		
		//Neu muon thao tac vs 1 element: WebElement
		//Neu muon thao tac voi nhieu element (2 tro len): List<WebElement>
		//List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@typ='checkbox']"));
		//System.out.println("Tong so checkbox tai page nay = " + checkboxes.size());
		
		//xoa du lieu 
		WebElement element = driver.findElement(By.xpath(""));
		element.clear();
		// Nhap du lieu
		element.sendKeys();
		//click vao button 
		element.click();
		// lay ra gia tri nam trong 1 attribute
		element.getAttribute("placeholder");
		//lay ra style cua mot element
		element.getCssValue("background");
		
		//GUI
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//chup hinh loi dua vao report
		//element.getScreenshotAs(target);
		element.getTagName();
		
		//Lay ra text cua 1 element bat ky, ko lay trong attribute
		element.getText();
		
		//Kiem tra xem element mong muon dang hien thi
		Assert.assertTrue(element.isDisplayed());
		
		//co the thao tac dc 
		Assert.assertTrue(element.isEnabled());
		
		//Khong the thao tac dc 
		Assert.assertTrue(element.isDisplayed());
		
		//Da dc chon thanh cong 
		Assert.assertTrue(element.isSelected());
		
		//ENTER vao trong 1 form  (chi dung dc voi form)
		element.submit();
			

	}
	@Test
	public void TC_02_Title() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
	}
	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	@Test
	public void TC_04_Get() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains(" Login or Create an Account "));
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
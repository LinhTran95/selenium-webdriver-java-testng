package api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_02_Xpath_Css_02Test {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
  @Test
  public void TC_01_ID() {
	  driver.findElement(By.id("email"));
  }
  @Test
  public void TC_03_Name() {
	  driver.findElement(By.name("login[username]")).sendKeys("53262424");
	  
  }
  @Test
  public void TC_04_TagName() {
	  System.out.println("Tong so link tren trang hien tai = "+ driver.findElements(By.tagName("a")));

  }
  @Test
  public void TC_05_Linktext() {
	  driver.findElement(By.linkText("Forgot Your Password?")).click();
	  
  }
  @Test
  public void TC_06_Partial_Linktext() {
	  driver.findElement(By.partialLinkText("Forgot")).click();
	  
  }
  @Test
  public void TC_07_Css_Selector() {

	  driver.findElement(By.cssSelector("input[id='id_email']")).sendKeys("automation.gmail.com");
	  driver.findElement(By.cssSelector("input[id='id_email']")).clear();
  
  }
  @Test
  public void TC_08_Xpath() {
	driver.findElement(By.xpath("//form[@id='login-form]//inpput[@id='id_email']"));
	 
  
  }
  @AfterClass
	public void afterClass() {
		driver.quit();
	}
}

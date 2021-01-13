package javatester;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_01_Data_Type {
	public static void main (String[]arg)
	{
		//Date type
		//1- kieu du lieu nguyen thuy (primitive)
		//Ky tu:char
		char key ='A';
		//So nguyen: ko co phan than phan: byte, short, int, long
		byte numberByte= 127; //pham vi
		short numberShort = 6500;
		int numberInt = 6500;
		long numberLong = 5234245;
		// so thuc: co phan thap phan: float, double
		float numberFloat = 15.65F;
		double numberDouble = 56.33D;
		// boolean: true or false 
		boolean status = true;
		//2- Kieu tham chieu
		//String, array, class, object, collection 
		String address = "HG DHF SLK";// chu,so, ky tu dac biet
		String addresses []= {"Ho Chi Minh","Da Nang","Ha Noi"};
		int numbers []= {12, 43, 343};
		//Class
		Topic_01_Data_Type topic01 = new Topic_01_Data_Type();
		Object a ="Can Tho";
		
		//List<String> add = new ArrayList<String>();
		
		String fullName = "ABC ADD";
		//String: kieu du lieu (data type: String/ int/ float/ double/ char/ Array/ Class/ Collection/...)
		//fullName: ten bien (variable/ field/ property)
		//ABC ADD: gia tri cua bien
		
		WebDriver driver = null;
		// 1 element
		WebElement emailTextbox = driver.findElement(By.id(""));
		
		// Nhieu element
		//List <WebElement> checkboxes = driver.findElements(By.id(""));
		
		
	}
}

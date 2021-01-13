package javatester;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_05_Random_Number {
	public static void main (String[]arg)
	{
		//Java calass
		Random rand = new Random();
	
		System.out.println("auto" + rand.nextInt(999999) + "@gmai.com");
		
		
	}
}

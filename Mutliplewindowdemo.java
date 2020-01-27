package com.oracle.training;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Mutliplewindowdemo {
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='Multiple Windows']")).click();
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		Set<String> ids = driver.getWindowHandles();
		 Iterator<String> id = ids.iterator();
		 String parentid = id.next();
		 String childid = id.next();
		 driver.switchTo().window(childid);
		 System.out.println(driver.findElement(By.cssSelector(".example")).getText());
		 driver.switchTo().window(parentid);
		 System.out.println(driver.findElement(By.xpath("//div/h3")).getText());
		 
	}
	
}
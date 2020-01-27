package com.oracle.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tabledemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://qaclickacademy.com/practice.php");
		driver.manage().window().maximize();
		WebElement table = driver.findElement(By.id("product"));
		int totNoRow = table.findElements(By.tagName("tr")).size();
		int totNoCol = table.findElements(By.tagName("th")).size();
		System.out.println("Totalnumber of rows:"+totNoRow);
		System.out.println("Totalnumber of rows:"+totNoCol);
		
		//print second information 
		
		//"//table[@id='product']/tbody/tr/following-sibling::tr[2]"
		
		System.out.println(driver.findElement(By.xpath("//table[@id='product']/tbody/tr/following-sibling::tr[2]")).getText());
	}

}

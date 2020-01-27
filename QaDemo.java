package com.oracle.training;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class QaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://qaclickacademy.com/practice.php");
		driver.manage().window().maximize();
		
		//check box
		System.out.println(driver.findElements(By.cssSelector("[type='checkbox']")).size());
		String defaultValue = driver.findElement(By.xpath("//input[@id='checkBoxOption2']/parent::label")).getText();
		System.out.println(defaultValue);
		driver.findElement(By.id("checkBoxOption2")).click();
		
		//DropDown
		Select s = new Select(driver.findElement(By.cssSelector("#dropdown-class-example")));
		driver.findElement(By.cssSelector("#dropdown-class-example")).click();
		s.selectByVisibleText(defaultValue);
		
		//input box
		WebElement inputBox = driver.findElement(By.xpath("//input[@class='inputs']"));
		inputBox.click();
		inputBox.sendKeys(defaultValue);
		driver.findElement(By.cssSelector("#alertbtn")).click();
		String textValue = driver.switchTo().alert().getText();
		if(textValue.contains(defaultValue))
		{
			System.out.println("success");
			driver.switchTo().alert().accept();
		}
		else
		{
			System.out.println("fail");
		}
	}

}

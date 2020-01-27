package com.oracle.training;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SortingDemo1
{
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> priceList = driver.findElements(By.cssSelector(".table-display tr td:nth-child(3)"));
		//Creating Array list
		
		ArrayList price = new ArrayList();
		for(int i=0;i<priceList.size();i++)
		{
			price.add(driver.findElements(By.cssSelector("tr td:nth-child(3)")).get(i).getText());
		}
		System.out.println(price);
		
		int totalPrice = 0;
		for(int i=0;i<priceList.size();i++)
		{
			String t = priceList.get(i).getText();
			totalPrice += Integer.parseInt(t) ;
		}
		System.out.println(totalPrice);
	}

}

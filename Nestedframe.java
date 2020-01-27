package com.oracle.training;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Nestedframe {
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		Nestedframe nF = new Nestedframe();
		//try to open the nested page dynamically
		//Keys chord used to handle multiple key actions 
		
		WebElement page = driver.findElement(By.id("content"));
		List<WebElement> tagrefernece = page.findElements(By.tagName("a"));
		for(int i=0;i<tagrefernece.size();i++) {
			String tab = tagrefernece.get(i).getText();
			if(tab.contains("Nested Frames"))
			{
				String clickOnLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
				driver.findElements(By.tagName("a")).get(i+1).sendKeys(clickOnLink);
			}
			
		}
		//Handle child window
		Set<String> browserTabs = driver.getWindowHandles();
		Iterator<String> tabId = browserTabs.iterator();
		String parentId = tabId.next();
		/*
		 * String childId = tabId.next(); System.out.println(parentId);
		 * System.out.println(childId);
		 */
		while(tabId.hasNext())
		{
			String id = tabId.next();
		    driver.switchTo().window(id);
		    System.out.println(driver.getCurrentUrl());
		    if(driver.getCurrentUrl().contains("nested"))
		    {
		    	System.out.println("Success");
		    	nF.frameDemo(driver);
		    }
		    else
		    {
		    	driver.switchTo().window(parentId);
		    }
			
		}
	}
	
	public void frameDemo(WebDriver driver)
	{
		int size = driver.findElements(By.tagName("frame")).size();
		System.out.println(size);
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		System.out.println(driver.findElement(By.id("content")).getText());
	}
	
}

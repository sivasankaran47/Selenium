package com.oracle.training;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AutosuggestionAssignment {
	public static void main(String args[]) throws IOException
	{
		DesiredCapabilities ch = DesiredCapabilities.chrome();
		ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		ChromeOptions c = new ChromeOptions();
		c.merge(ch);
		System.setProperty("webdriver.chrome.driver", "src\\\\references\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(c);
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.cssSelector("#autocomplete")).click();
		driver.findElement(By.cssSelector("#autocomplete")).sendKeys("IND");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		System.out.println(text);
		driver.findElement(By.cssSelector("#autocomplete")).sendKeys(Keys.ARROW_DOWN);
		int listCount = driver.findElements(By.cssSelector("#ui-id-1 .ui-menu-item")).size();
		int i =0;
		while(!text.equalsIgnoreCase("INDIA"))
		{
			i++;
			driver.findElement(By.cssSelector("#autocomplete")).sendKeys(Keys.ARROW_DOWN);
			text = (String) js.executeScript(script);
			if(i>listCount)
			{
				break;
			}
			
		}
		driver.findElement(By.cssSelector("#autocomplete")).sendKeys(Keys.ENTER);
		if(i>listCount)
		{
			System.out.println("Element not found");
		
		}
		else
		{
			System.out.println("Element Found");
			
		}
		
	}
	public void screenshot(WebDriver driver) throws IOException
	{
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("C:\\Users\\sivanat.I-FLEX\\Desktop\\Selenium\\TestResult\\s1.png"));
	}

}

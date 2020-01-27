package com.oracle.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class assignment {
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
	//	driver.findElement(By.cssSelector(a[text()='Click to load get data via Ajax!']))
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();
		WebDriverWait w = new WebDriverWait(driver,5);
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[id*='result']")));
		System.out.println(driver.findElement(By.cssSelector("div[id*='result']")).getText());
	}
}

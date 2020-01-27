package com.oracle.training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SortingDemo extends AutosuggestionAssignment {

	public static void main(String args[]) throws IOException {
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		SortingDemo sD = new SortingDemo();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

		// Storing value in Array list
		List<WebElement> orginalList = driver.findElements(By.cssSelector("tr td:nth-child(2)"));
		// Creating ArrayList
		ArrayList<String> orgList = new ArrayList<String>();
		for (int i = 0; i < orginalList.size(); i++) {
			// String orgList = orginalList.get(i).getText();
			orgList.add(orginalList.get(i).getText());
		}
		System.out.println(orgList);
		ArrayList<String> copiedList = new ArrayList<String>();
		for (int i = 0; i < orginalList.size(); i++) {
			// String orgList = orginalList.get(i).getText();
			copiedList.add(orgList.get(i));
		}
		System.out.println(copiedList);
		Collections.sort(copiedList);
		System.out.println("###########");
		System.out.println(copiedList);
		sD.screenshot(driver);
		Assert.assertTrue(orgList.equals(copiedList));

	}
}

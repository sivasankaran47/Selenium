package com.oracle.training;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AutosuggestiveDemo  {

	public static void main(String[] args) throws InterruptedException {
		DesiredCapabilities ch = new DesiredCapabilities();
		ch.acceptInsecureCerts();
		ChromeOptions c = new ChromeOptions();
		c.merge(ch);
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://ksrtc.in/oprs-web/");
		driver.manage().window().maximize();
		AutosuggestiveDemo aut = new AutosuggestiveDemo();
		aut.departcity(driver);
		
		aut.Arrivalcity(driver);
		aut.calender(driver);
		aut.submitButton(driver);
		

	}

	public void departcity(WebDriver driver) {
		WebElement leavingInput = driver.findElement(By.id("fromPlaceName"));
		leavingInput.click();
		leavingInput.sendKeys("BENG");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-1"))));
		leavingInput.sendKeys(Keys.ARROW_DOWN);
		int listCount = driver.findElements(By.cssSelector("#ui-id-1 .ui-menu-item")).size();
		System.out.println(listCount);
		// JavaScriptExecutor

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return document.getElementById(\"fromPlaceName\").value;";
		String text = (String) js.executeScript(script);
		// System.out.println(text);

		/*
		 * String script = "return document.getElementById(\"fromPlaceName\").value;";
		 * String text=(String) js.executeScript(script); System.out.println(text);
		 */
		int i = 0;
		while (!text.equalsIgnoreCase("BENGALURU")) {
			i++;
			leavingInput.sendKeys(Keys.ARROW_DOWN);
			text = (String) js.executeScript(script);
			System.out.println(text);

			if (i > listCount) {
				System.out.println("Element not found");
				break;
			}
		}
		if (i > listCount) {
			System.out.println("Element not found");
		} else {
			System.out.println("Element found");
			leavingInput.sendKeys(Keys.ENTER);

		}
	}

	public void Arrivalcity(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement arrivalInput = driver.findElement(By.cssSelector("#toPlaceName"));
		arrivalInput.click();
		arrivalInput.sendKeys("CHEN");
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"toPlaceName\").value;";
		String text = (String) js.executeScript(script);
		System.out.println(text);

		int listCount = driver.findElements(By.cssSelector("#ui-id-2 .ui-menu-item")).size();
		System.out.println(listCount);
		int i = 0;
		while (!text.equalsIgnoreCase("CHENNAI")) {
			i++;
			arrivalInput.sendKeys(Keys.ARROW_DOWN);
			text = (String) js.executeScript(script);
			// System.out.println(text);

			if (i > listCount) {
				System.out.println("Element not found");
				break;
			}
		}
		if (i > listCount) {
			System.out.println("Element not found");
		} else {
			System.out.println("Element found");
			arrivalInput.sendKeys(Keys.ENTER);

		}
		
		

	}

public void calender(WebDriver driver)
{
	driver.findElement(By.id("txtJourneyDate")).click();
	String departMonth = "February";
	String depDate = "22";
	
	//selecting Month
	for(int i=0;i<12;i++)
	{
	String month = driver.findElement(By.cssSelector(".ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all .ui-datepicker-title")).getText();
	//
	if(!month.contains(departMonth)) {
		  driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")). click();
		 }
	else
	{
		System.out.println(month);
		break;
	}
	}
	//selecting date
	List<WebElement> datecount = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
	for (int i=0;i<datecount.size();i++)
	{
		//System.out.println(datecount.get(i).getText());
		if(depDate.equals(datecount.get(i).getText()))
		{
			datecount.get(i).click();
			break;
		}
		
	}
}

public void submitButton(WebDriver driver)
{
	driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg.btn-block.btn-booking")).click();
}
}
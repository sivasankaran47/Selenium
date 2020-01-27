package com.oracle.training;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;



public class Spicejet {
	
	
public static void main(String args[])
{
	System.setProperty("webdriver.chrome.driver", "src\\references\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
	Spicejet jet = new Spicejet();
	jet.chromeInvoke(driver);
	jet.checkBoxSelection(driver);
	jet.citySelection(driver);
	jet.calender(driver);
	jet.passengers(driver);
	
}

public void chromeInvoke(WebDriver driver)
{
	
	driver.get("https://www.spicejet.com/");
	//driver.get("https://www.makemytrip.com/");
	driver.manage().window().maximize();
}

//check box count
public void checkBoxCount(WebDriver driver)
{
	
	int count = driver.findElements(By.cssSelector("input[type= checkbox]")).size();
	System.out.println(count);
}

public void checkBoxSelection(WebDriver driver)
{
	checkBoxCount(driver);
	
	String[] selectionList= {"Senior Citizen","Student"};
	//driver.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).click();
	 List<WebElement> selectionType = driver.findElements(By.xpath("//input[@type='checkbox']/following-sibling::label"));
	 for (int i =0 ; i<selectionType.size();i++)
	 {
		 String type = selectionType.get(i).getText();
		 List selectionsList = Arrays.asList(selectionList);
		if(selectionsList.contains(type))
		 {	
			System.out.println(driver.findElements(By.cssSelector("input[type= checkbox]")).get(i).isSelected());
			 driver.findElements(By.cssSelector("input[type= checkbox]")).get(i).click();
		 }
	 }

}

public void citySelection(WebDriver driver)
{
	//Departure City
	String	orgCity = "MAA";
	
	driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
	 List<WebElement> oCity = driver.findElements(By.cssSelector(("#ctl00_mainContent_ddl_originStation1_CTNR a[href='#']")));
	for (int i=0; i<oCity.size();i++)
	{
		String cityName = oCity.get(i).getText();
		//System.out.println(cityName);
		if (cityName.contains(orgCity))
		{
			System.out.println(cityName);
			driver.findElements(By.cssSelector(("#ctl00_mainContent_ddl_originStation1_CTNR a[href='#']"))).get(i).click();
		}
				
	}
	
	//Arrival City
		String	depCity = "SAG";
		List<WebElement> dCity = driver.findElements(By.cssSelector(("#glsctl00_mainContent_ddl_destinationStation1_CTNR a[href='#']")));
		for (int i=0; i<dCity.size();i++)
		{
			String cityName = dCity.get(i).getText();
			if(cityName.contains(depCity))
			{
				System.out.println(cityName);
				driver.findElements(By.cssSelector(("#glsctl00_mainContent_ddl_destinationStation1_CTNR a[href='#']"))).get(i).click();
			}
		}
}

public void calender(WebDriver driver)
{
	//selecting Month
	String departMonth = "April";
	String depDate = "22";
	for(int i=0;i<12;i++)
	{
	String month = driver.findElement(By.cssSelector(".ui-datepicker-group.ui-datepicker-group-first .ui-datepicker-title")).getText();
	if(!month.contains(departMonth)) {
		  driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")). click();
		 }
	else
	{
		System.out.println(month);
		break;
	}
	}
	List<WebElement> datecount = driver.findElements(By.cssSelector(".ui-datepicker-group.ui-datepicker-group-first td[data-handler='selectDay']"));
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

public void passengers(WebDriver driver)
{
	driver.findElement(By.id("divpaxinfo")).click();
	System.out.println(driver.findElement(By.id("divpaxOptions")).isDisplayed());
	Select s = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
	s.selectByVisibleText("2");
	driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
}




}

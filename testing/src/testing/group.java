package testing;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.basetest;

public class group extends basetest {
	
	
	@Test(testName= "amazon",groups ="regression")
	public void amazon()
	{
		
        //System.out.println("my first test");
        driver.get("https://www.amazon.com/");
        extentTest.info("navigated to url");
       driver.findElement(By.xpath("//*[@id=\"nav-orders\"]")).click();
       
        
	}
	
	
	@Test(testName="amazon1",groups = "test")
public void amazon1()
{
	//System.out.println("my second method");
		 driver.get("https://www.amazon.com");
	        extentTest.info("navigated to url");
	       driver.findElement(By.linkText("Amazo Basics")).click();
}
}

package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class baseclass {
	
	public static WebDriver driver = null;
	@BeforeSuite
	public void launchbrowser()
	{
		
		driver = new ChromeDriver();
	}
	
	@AfterSuite
public void closebrowser()
{
	driver.close();
}
}

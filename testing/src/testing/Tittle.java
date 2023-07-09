package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class Tittle {
	
	
	public void firdt()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.com");
		
		
		
		
      String expectedtitle ="Amazon.com. Spend less. Smile more.";
      
      String actualtitle = driver.getTitle();
      
      Assert.assertEquals(actualtitle, expectedtitle);

	}

}

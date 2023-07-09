import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class module {


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver =new ChromeDriver();
		driver.get("https://www.ebay.com/");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("pen");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		
		
		
		
		driver.findElement(By.xpath("//*[@id=\"gh-btn\"]")).click();
		
		
		driver.close();
		

	}

}

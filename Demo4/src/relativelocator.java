import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;



public class relativelocator {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.amazon.com");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	    
	    
	    //above()
	    
	    
	    WebElement Sell = driver.findElement(By.linkText("Sell"));
	    WebElement searchtab=driver.findElement(with(By.tagName("input")).above(Sell));
	   searchtab.sendKeys("gun");
	   
	   
	   //below()
	   
	   WebElement Searchtab = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
	    WebElement dontchange= driver.findElement(with(By.tagName("input")).below(Searchtab));
	   dontchange.click();
	   

	}

}

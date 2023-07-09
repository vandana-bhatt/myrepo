package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class basetest 
{
	
	public static WebDriver driver;
	public static String screenshotssubfoldername;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	
	@Parameters("browser Name")
	@BeforeTest
    public void Setup(ITestContext context,@Optional("chrome")String browserName)
    {
		
		switch(browserName.toLowerCase())
		{
		case "chrome" :
			driver =new ChromeDriver();
			break;
		case "safari" :
			driver =new SafariDriver();
			break;
			default:
				System.out.println("Browser invalid");
				break;
		}
		
		driver.manage().window().maximize();
		
		Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
		String device=capabilities.getBrowserName()+ " "+ capabilities.getBrowserVersion();
		String author = context.getCurrentXmlTest().getParameter("author");
		extentTest = extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignAuthor(device);
    }
    
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	
	@BeforeSuite
	
    public void initialiseExtentReports()
    {
		ExtentSparkReporter sparkReporter_all=new ExtentSparkReporter("AllTest.html");
		sparkReporter_all.config().setReportName("AllTest Report");
		//ExtentSparkReporter sparkReporter_failed= new ExtentSparkReporter("Failedtestes.html");
		//sparkReporter_failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
		//sparkReporter_failed.config().setReportName("Failure report");
		extentReports =new ExtentReports();
		//extentReports.attachReporter(sparkReporter_all,sparkReporter_failed);
		extentReports.setSystemInfo("os", System.getProperty("os.name"));
		extentReports.setSystemInfo("java version", System.getProperty("java.version"));
		
	}
		@AfterSuite
		public void generateExtentReports() throws Exception
		{
			extentReports.flush();
			Desktop.getDesktop().browse(new File("AllTest.html").toURI());
		}
		@AfterMethod
		public void checkstatus(Method m,ITestResult result)
		{
			if(result.getStatus()==ITestResult.FAILURE)
			{
				String screenshotpath = null;
				screenshotpath = captureScreenshot(result.getTestContext().getName()+"-"+result.getMethod().getMethodName()+".jpg");
				extentTest.addScreenCaptureFromPath(screenshotpath);
				extentTest.fail(result.getThrowable());
			}
			else if(result.getStatus()==ITestResult.SUCCESS)
			{
				extentTest.pass(m.getName()+"is passed");
			}
			extentTest.assignCategory(m.getAnnotation(Test.class).groups());
		}
		
    public String captureScreenshot(String fileName)
    {
    	if(screenshotssubfoldername==null)
    	{
    		LocalDateTime myDateobj=LocalDateTime.now();
    		DateTimeFormatter myFormatObj=DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm:ss");
    				screenshotssubfoldername=myDateobj.format(myFormatObj);
    	}
    	TakesScreenshot takesscreenshot=(TakesScreenshot)driver;
    	File SourceFile=takesscreenshot.getScreenshotAs(OutputType.FILE);
    	File destFile=new File("./Screenshots/"+ screenshotssubfoldername+ "/" +fileName);
    	try {
    		FileUtils.copyFile(SourceFile, destFile);
    		
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    
    System.out.println("screenshot saved");
	return fileName;
    }	
    
}

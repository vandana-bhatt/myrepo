package testing;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class skipping {

	
	Boolean datasetup=false;
	@Test(enabled= false)
	public void testi()
	{
		System.out.println("skip this test");
	}
	
	
	@Test
	public void testl()
	{
		System.out.println("skip the test case");
		
		throw new SkipException("skipping becoz of incomplete code");
	}
	
	@Test
	public void testk()
	{
		System.out.println("skip this case");
		
		if(datasetup==true)
		{
			System.out.println("skipping");
			
		}
		
		else
		{
			System.out.println("skip");
			throw new SkipException("incomplete");
		}
	}
}


package testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.baseclass;

public class listenerdemotest extends baseclass{
	@Test
	public void launchapp()
	{
		driver.get("http://www.amazon.com");
		Assert.assertTrue(false);
	}

}

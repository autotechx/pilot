package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;

public class TC02_LoginPage extends Base{

	@Test
	public void validateTestCase2() throws InterruptedException{
		logger.assignCategory("Smoke Test");
		logger.info("Description: This test is checking TC02");

		try {
			Assert.assertTrue(false);
		} catch (Exception e) {
			logger.info("Assert Error in catch block is: "+e.getMessage());
		}
	}
}

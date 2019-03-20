package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;


public class TC03_HomePage extends Base{

	@Test
	public void validateTestCase3() throws InterruptedException{
		logger.assignCategory("Regression Test");
		logger.info("Description: This test is checking TC03");
		

		try {
			Assert.assertTrue(false);
		} catch (Exception e) {
			logger.info("Assert Error in catch block is: "+e.getMessage());
		}
	}
}

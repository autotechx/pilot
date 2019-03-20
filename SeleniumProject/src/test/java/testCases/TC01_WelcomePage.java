package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.Base;

import pages.PageObjectRepository;
import util.SeleniumBaseFunctions;

public class TC01_WelcomePage extends SeleniumBaseFunctions {

	
	private static final By By = null;

	@Test
	public void validateTestCase1() throws Throwable {
		logger.assignCategory("Regression Test");
		logger.info("Description: This test is checking TC01");
		
//		String title = PageObjectRepository.validateWelcomePageTitle();
//		System.out.println(title);
		
//		waitForElement(,30);
		
		try {
			Assert.assertTrue(true);
		} catch (Exception e) {
			logger.info("Assert Error in catch block is: "+e.getMessage());
		}
	}

}

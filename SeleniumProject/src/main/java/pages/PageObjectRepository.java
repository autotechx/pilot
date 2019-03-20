package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import base.Base;

public class PageObjectRepository extends Base{

	String ExpectedTitle = "Google";
	
	//Page Factory - OR:
	@FindBy(id="hplogo")
	public static WebElement GoogleLogo;
	
	@FindBy(name="password")
	public static WebElement password;
	
	
	public static final By OBJ_LoginuserName_Text = By.id("hplogo");
	public static final By OBJ_LoginPassword_Text = By.name("password");
	
	
	
//	public PageObjectRepository()
//	{
//		PageFactory.initElements(driver, this);
//	}
//	
//	//Actions:
//	public static String validateWelcomePageTitle(){
//	
////		logger = report.createTest("LoginPage");
////		logger.log(Status.INFO, "Validating the LoginPage Title");
//		
//		return driver.getTitle();
//	}
//	
//	public static boolean validateGoogleLogo(){
//		return GoogleLogo.isDisplayed();
//	}
}

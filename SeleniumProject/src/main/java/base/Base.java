package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import util.Helper;

public class Base extends TestListenerAdapter {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
//  This is for log4j logs:
//	public static Logger log = Logger.getLogger(Base.class);
	

	public Base() {
		try {

			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java" + "/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			logger.info("Test Execution on Browser: " + browserName);
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\neova500\\Downloads\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			logger.info("Test Execution on Browser >> " + browserName);
			System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String URL = prop.getProperty("url");
		driver.get(URL);
		logger.info("URL Entered is: " + URL);
	}

	@BeforeSuite
	public void setupReport() {
		Reporter.log("Setting up reports and Test is getting ready", true);
		htmlReport = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/SeleniumAutomationReport.html"));
		extent = new ExtentReports();
		
		
		htmlReport.config().setReportName("SCALYR Automation Report");
//		htmlReport.config().setTestViewChartLocation(ChartLocation.TOP);
//		htmlReport.config().setTheme(Theme.DARK);
		
		
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Environment", "UAT");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("UserName", "Navdeep");
		extent.setSystemInfo("Java Version", "1.8");
		extent.setSystemInfo("Selenium Version", "3.14.0");
		extent.setSystemInfo("ExtentReport Version", "4.0.5");

		
		
		Reporter.log("Settings Done and Test Started............", true);
	}

	@AfterSuite
	public void generateReport() {
		Reporter.log("Preparing Report", true);
		extent.flush();
		Reporter.log("Report is generated......................", true);
	}

	@BeforeTest
	public void startExtentReports(ITestContext context) throws Exception {
		logger = extent.createTest(context.getName());
	}

	@BeforeClass
	public void setup() {
		Reporter.log("Trying to start browser and getting application ready", true);
		initialization();
		Reporter.log("Browser and Application is up and running", true);
	}

	@AfterClass
	public void tearDown() {
		Reporter.log("Trying to close the current Open browser", true);
		driver.close();
		logger.info("Browser is Closed successfully");
		Reporter.log("Browser is closed.......................", true);
	}

	@BeforeMethod
	public void beforeTestMethod(Method method) {
//	    Test test = method.getAnnotation(Test.class);
		
	    logger.createNode("TestName: "+method.getName()); 
	    
//	    parent = report.createTest(method.getName());
//	    child = parent.createNode("Test Name: "+method.getName());
	    String testMethodName = method.getName();
	    logger.log(Status.INFO,
				MarkupHelper.createLabel("Testing Functionality >>> "+testMethodName, ExtentColor.ORANGE));

	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		
		Reporter.log("Test is about to end", true);
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel("***Test Case FAILED***Screenshot Captured Below***", ExtentColor.RED));
			logger.log(Status.FAIL, result.getThrowable());
			logger.fail("Test Failed - Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel("***Test Case PASSED***Screenshot Captured Below***", ExtentColor.GREEN));
			logger.pass("Test Passed - Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel("***Test Case SKIPPED***Screenshot Captured Below***", ExtentColor.BLUE));
			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		Reporter.log("Test is Completed", true);
	}

}

package qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import qa.pages.RegisterPage;
import qa.pages.HomePage;
import qa.base.TestBase;
import qa.util.ExtentReporterNG;
import qa.util.SeleniumUtil;;

@Listeners(ExtentReporterNG.class)
public class HomePageTest extends TestBase {

	HomePage homePage;
	RegisterPage registerPage;
	public ExtentReports report;
	public ExtentTest extentLogger;
	Logger logger = Logger.getLogger(HomePageTest.class);
	SoftAssert softAssert;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		homePage = new HomePage();
		registerPage = new RegisterPage();
		softAssert = new SoftAssert();
	}

	@BeforeTest
	public void setVariables() {
		report = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportNG.html", true);
		report.addSystemInfo("Environment", "UAT");
		report.addSystemInfo("User", "Admin");
	}

	@AfterTest
	public void pauseReporting() {
		report.flush();
		report.close();
	}

	/*
	 * The test is to register to the application.
	 */
	@Test(priority = 1, groups = "regression")
	public void registerNewUser() {
		String testId = "T01";
		try {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("title"), "Page title is incorrect.");
		softAssert.assertTrue(homePage.Register());
		softAssert.assertTrue(registerPage.inputDetails(prop.getProperty("username"), prop.getProperty("firstname"),
				prop.getProperty("lastname"), prop.getProperty("password"), true));
		logger.info("Current page's title: ");
		softAssert.assertAll();
		} catch (Exception e) {
			Assert.fail(testId + " failed due to below reason:" + e);
		}
	}

	/*
	 * The test is to log in to the web app.
	 */
	@Test(priority = 2, dependsOnMethods = "registerNewUser", groups = "regression")
	public void signIn() {
		String testId = "T02";
		try {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("title"), "Page title is incorrect.");
		homePage.clickBuggyRating();
		Assert.assertTrue(homePage.logIn(prop.getProperty("username"), prop.getProperty("password"),prop.getProperty("firstname")));
		} catch (Exception e) {
			Assert.fail(testId + " failed due to below reason:" + e);
		}
	}

	/*
	 * The test is to if same user can be registered twice.
	 */
	@Test(priority = 3, groups = "regression")
	public void reRegisterUser() {
		String testId = "T03";
		try {
		Assert.assertEquals(driver.getTitle(), prop.getProperty("title"), "Page title is incorrect.");
		softAssert.assertTrue(homePage.Register());
		String username = registerPage.inputDetailsAndReturnUser(prop.getProperty("username2"),
				prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("password"), true);
		driver.navigate().refresh();
		softAssert.assertFalse(registerPage.inputDetails(username, prop.getProperty("firstname"),
				prop.getProperty("lastname"), prop.getProperty("password"), false));
		softAssert.assertAll();
		} catch (Exception e) {
			Assert.fail(testId + " failed due to below reason:" + e);
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE)
			screenshotPath = SeleniumUtil.takeScreenshotAtEndOfTest();
			driver.quit();
	}

}

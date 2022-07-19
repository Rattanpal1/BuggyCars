package qa.base;

import org.openqa.selenium.WebDriver;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.concurrent.TimeUnit;
import qa.util.WebEventListener;
import org.apache.commons.io.FileUtils;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String screenshotPath;
	//public static final Logger logger = null; 

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream("C:\\Users\\ranje\\eclipse-workspace\\BuggyCar\\resources\\config.properties");			
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	public static void initialization() {
		
		String wbrowser = prop.getProperty("browser");

		if (wbrowser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (wbrowser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}	

	public static String  CaptureScreenshot(WebDriver driver, String screenshotName) {
		
		String outputFolder = null;
		String date = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
		
		try {
			 outputFolder = System.getProperty("user.dir" + "Screenshots" +  screenshotName + " " + date + ".jpg");			
			 File destination = new File(outputFolder);			
			 FileUtils.copyFile(src, destination);			
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputFolder;
	}
}

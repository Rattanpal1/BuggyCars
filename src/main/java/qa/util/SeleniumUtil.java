package qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import qa.base.TestBase; 

public class SeleniumUtil extends TestBase {

public static boolean isDisplayed(WebElement ele) {
       try {
             if(ele!=null && ele.isDisplayed()){
                return true;
          }
      }catch(Exception e) {
          return false;
          }
   
       return false;
}


public static String takeScreenshotAtEndOfTest()  {
    String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String destination = System.getProperty("user.dir") + "/screenshots/" +  dateName
            + ".png";
    File finalDestination = new File(destination);
    try {
		FileHandler.copy(source, finalDestination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
    return destination;
}

}
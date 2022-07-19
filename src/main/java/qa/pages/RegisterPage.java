package qa.pages;

import java.sql.Timestamp;
import java.util.Date;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.base.TestBase;
import qa.util.SeleniumUtil;

public class RegisterPage extends TestBase {
	
	@FindBy(id = "username")
	WebElement inputUsername;

	@FindBy(xpath = "//input[starts-with(@class, 'form-control ng-untouched ng-pristine ng-invalid') and @id ='firstName']")
	WebElement inputFirstname;

	@FindBy(id = "lastName")
	WebElement inputLastname;

	@FindBy(id = "password")
	WebElement inputPassword;

	@FindBy(xpath = "//input[@class='form-control ng-untouched ng-pristine ng-invalid' and @id = 'confirmPassword']")
	WebElement inputConfirmPassword;

	@FindBy(xpath = "//button[contains(text(),'Register')]")
	WebElement registerBtn;

	@FindBy(xpath = "//div[contains(text(),'Registration is successful')]")
	WebElement successMessage;
	
	@FindBy(xpath = "//div[contains(text(),'Passwords do not match')]")
	WebElement passwordMismatchWarning;

	// static final Logger logger =
	// LogManager.getLogger(BookingsPage.class.getName());

	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}


	public boolean inputDetails(String username, String firstname, String lastname, String password, Boolean flag) {
		
		if(flag){
		Date now = new Date(); 
		Timestamp current = new Timestamp(now.getTime()); 		
		username = username + current.getNanos();
		}		
		inputUsername.sendKeys(username);
		inputFirstname.sendKeys(firstname);
		inputLastname.sendKeys(lastname);
		inputPassword.sendKeys(password);
		inputConfirmPassword.sendKeys(password);
		registerBtn.click();
		/* if(flag)
		new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOf(successMessage)); */
		return SeleniumUtil.isDisplayed(successMessage);		
	}
	
	public String inputDetailsAndReturnUser(String username, String firstname, String lastname, String password, Boolean flag) {
		
		if(flag){
		Date now = new Date(); 
		Timestamp current = new Timestamp(now.getTime()); 		
		username = username + current.getNanos();
		}		
		inputUsername.sendKeys(username);
		inputFirstname.sendKeys(firstname);
		inputLastname.sendKeys(lastname);
		inputPassword.sendKeys(password);
		inputConfirmPassword.sendKeys(password);
		registerBtn.click();
		/* if(flag)
		new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOf(successMessage)); */		
		return username;		
	}

}

package qa.pages;

import java.sql.Timestamp;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.base.TestBase;
import qa.util.SeleniumUtil;

public class HomePage extends TestBase {
	
	RegisterPage registerPage;

	@FindBy(xpath="//input[@name='login']")
	WebElement inputLogin;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[@type='submit' and text()='Login']")
	WebElement loginBtn;
		
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerBtn;
	
	@FindBy(xpath="//a[text()='Buggy Rating']")
	WebElement buggyRatingBtn;	
	
	@FindBy(xpath="//img[@title='Lamborghini']")
	WebElement popularMakeImg;
	
	@FindBy(xpath="//img[@title='Diablo']")
	WebElement popularModelImgOld;
	
	@FindBy(xpath="//img[@title='Guilia Quadrifoglio']")
	WebElement popularModelImg;
	
	@FindBy(xpath="//img[@class='img-fluid center-block' and @src = '/img/overall.jpg']")
	WebElement overAllRating1;
	
	@FindBy(xpath="//img[@class='img-fluid center-block'])[3]")
	WebElement overAllRating2;	
	
	/* Hi, Username element can be located using this WebElement as well
	@FindBy(xpath="//span[contains(text(),'Hi')]")
	WebElement loggedInAs;
	*/
	@FindBy(xpath="//a[contains(text(),'Profile')]/parent::li/parent::ul/li[1]")
	WebElement loggedInAs;
	
	@FindBy(id ="comment")
	WebElement inputComment;
	
	@FindBy(xpath="//button[contains(text(),'Vote!')]")
	WebElement voteBtn;

	public HomePage() {
		PageFactory.initElements(driver, this);
		registerPage = new RegisterPage();
	}

	public boolean Register() {		
		registerBtn.click();
		/*
		  	JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].scrollIntoView(true);", registerPage.inputConfirmPassword);
		 
		 */
		new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(registerPage.inputConfirmPassword));				
		return registerPage.inputConfirmPassword.isDisplayed();
	}

	public void clickBuggyRating() {
		buggyRatingBtn.click(); 	
	}
	
	public boolean logIn(String username, String password, String firstname) {
		inputLogin.sendKeys(username);
		inputPassword.sendKeys(password);
		loginBtn.click();
		return(loggedInAs.getText().contains(firstname));		
	}
	
	public boolean addComment(String comment) {		
		Date now = new Date(); 
		Timestamp current = new Timestamp(now.getTime());  		
		comment = comment + current.getSeconds();
		popularModelImg.click();
		inputComment.sendKeys(comment);
		voteBtn.click();
		String xpath = "//td[contains(text(),'" + comment +"')]";
		WebElement commentElement = driver.findElement(By.xpath(xpath));
		return(SeleniumUtil.isDisplayed(commentElement));		
	}

}

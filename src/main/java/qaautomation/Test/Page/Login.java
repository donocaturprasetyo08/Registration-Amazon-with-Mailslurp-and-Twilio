package qaautomation.Test.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaautomation.Test.Navigation.BasePage;

public class Login extends BasePage {
	By linkLogin = By.linkText("Login");
	
	public Login(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}
	public void login() {
		clickByXpath(linkLogin);
	}

}

package qaautomation.Test.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaautomation.Test.Navigation.BasePage;
import qaautomation.Test.Utils.Utils;

public class SignUp extends BasePage {
	By buttonCreate1 = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	By buttonCreate2 = By.xpath("//a[@id='createAccountSubmit']");
	By inputNama = By.xpath("//input[@id='ap_customer_name']");
	By inputEmail = By.xpath("//input[@id='ap_email']");
	By inputPassword1 = By.xpath("//input[@id='ap_password']");
	By inputPassword2 = By.xpath("//input[@id='ap_password_check']");
	By buttonCreateAccount = By.xpath("//input[@id='continue']");
	By inputCode = By.xpath("//input[@id='cvf-input-code']");
	By buttonCode = By.xpath("//input[@aria-labelledby='cvf-submit-otp-button-announce']");
	By inputNumber = By.xpath("//input[@placeholder='(e.g., 201-555-5555)']");
	By buttonNumber = By.xpath("//input[@name='cvf_action']");
	By inputOtp = By.xpath("//input[@name='code']");
	By buttonOtp = By.xpath("//span[@id='a-autoid-0']//input[@name='cvf_action']");
	
	public SignUp(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
		// TODO Auto-generated constructor stub
	}
	public void signUpData(String nama, String email, String password, String passwordCheck) {
		clickByXpath(buttonCreate1);
		Utils.hardWait(2);
		clickByXpath(buttonCreate2);
		Utils.hardWait(2);
		setText(inputNama, nama);
		setText(inputEmail, email);
		setText(inputPassword1, password);
		setText(inputPassword2, passwordCheck);
		clickByXpath(buttonCreateAccount);
		Utils.hardWait(20);
	}
	public void codeVerify(String code) {
		setText(inputCode, code);
		clickByXpath(buttonCode);
	}
	public void setNumber(String number) {
		setText(inputNumber, number);
		clickByXpath(buttonNumber);
	}
	public void setOtp(String otp) {
		setText(inputOtp, otp);
		clickByXpath(buttonOtp);
	}
	
	
}

package qaautomation.Test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.mailslurp.clients.ApiClient;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWeb implements DriverWeb {
	ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();
	
	private static final String YOUR_API_KEY = "apiKey mailslurp";
	protected static ApiClient mailslurpClient;
	protected static final Long TIMEOUT_MILLIS = 30000L;

	@Override
	@BeforeMethod
	public void createChromeDriver() {
		// setup mailslurp
		mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
		mailslurpClient.setApiKey(YOUR_API_KEY);
		mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
		
		// setup chromedriver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		driver.set(new ChromeDriver(options));
		driver.get().get("https://www.amazon.com/");
		driver.get().manage().window().maximize();
		explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(120)));
	}

	@Override
	@AfterMethod
	public void quitChromeDriver() {
		// TODO Auto-generated method stub
		driver.get().close();
	}
}

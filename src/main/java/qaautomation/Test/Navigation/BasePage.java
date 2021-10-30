package qaautomation.Test.Navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();

	public BasePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}

	protected final void clickByXpath(By locator) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		driver.get().findElement(locator).click();
	}

	protected final void setText(By locator, String text) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		driver.get().findElement(locator).sendKeys(text);
	}

	protected final String getText(By locator) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		return driver.get().findElement(locator).getText();
	}
	protected final void scrollUntilElement(By locator) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		WebElement element = driver.get().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("return arguments[0].scrollIntoView();", element);
	}
	protected final void uploadAvatar(By locator, String location) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		WebElement uploadElement = driver.get().findElement(locator);
		uploadElement.sendKeys(location);
	}
	protected final void selectData(By locator, String select) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(locator));
		Select clickData = new Select(driver.get().findElement(locator));
		clickData.selectByVisibleText(select);
	}
}

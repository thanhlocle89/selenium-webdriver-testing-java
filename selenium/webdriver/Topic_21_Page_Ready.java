package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://www.facebook.com/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public Boolean isPageLoadSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExecutor.executeScript("return (windown.jQuery != null)&&(jQuery.active===0)");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad)&&explicitWait.until(jsLoad);
		
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}

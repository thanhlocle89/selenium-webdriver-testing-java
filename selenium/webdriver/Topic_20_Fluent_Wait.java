package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Functions;

public class Topic_20_Fluent_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebElement> fluentWait;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<By> fluentWaitBy;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Count_Down() {
		explicitWait = new WebDriverWait(driver, 15);

		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countDownElement = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#javascript_countdown_time")));

		fluentWait = new FluentWait<WebElement>(countDownElement);
		fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		fluentWait.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement countDownElement) {
				// TODO Auto-generated method stub
				String text = countDownElement.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}
//	@Test
	public void TC_02_Hello_World() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
//		WebElement finishElement = driver.findElement(By.cssSelector("div#finish>h4"));
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		fluentWaitDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver element) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#finish>h4"));
			}
		});

	}
	@Test
	public void TC_02_Hello_World_II() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		By finishElement = By.cssSelector("div#finish>h4");
		fluentWaitBy =  new FluentWait<By>(finishElement);
		
		fluentWaitBy.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		fluentWaitBy.until(new Function<By, Boolean>() {

			@Override
			public Boolean apply(By finishElement) {
				// TODO Auto-generated method stub
				return driver.findElement(finishElement).isDisplayed();
			}
		});
		

	}
	public String getTimeStamp() {
		// TODO Auto-generated method stub
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
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

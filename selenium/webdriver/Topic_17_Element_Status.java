package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explixitWait;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		explixitWait = new WebDriverWait(driver, 10);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");

		explixitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("Automationfc@gmail.com");

	}

	@Test
	public void TC_02_Invisible_unDisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explixitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	public void TC_02_Invisible_unDisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		explixitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
	}

	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("reg_email_confirmation__")));
	}
	
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		WebElement reEnterEmailAddress = driver.findElement(By.name("reg_email_confirmation__"));
		
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		explixitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddress));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInsecond(long timeInSecond) {
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

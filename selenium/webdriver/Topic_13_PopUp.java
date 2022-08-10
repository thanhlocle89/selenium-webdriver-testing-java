package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_PopUp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Fixed_Popup_NN24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		WebElement popUpLogin  = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		
		Assert.assertFalse(popUpLogin.isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		
		Assert.assertTrue(popUpLogin.isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();

		Assert.assertFalse(popUpLogin.isDisplayed());
	}

	@Test
	public void TC_02_Fixed_Popup_Kyna() {
		driver.get("https://kyna.vn/");
		
		WebElement popUpLogin  = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		
		Assert.assertFalse(popUpLogin.isDisplayed());
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(popUpLogin.isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close.close")).click();

		Assert.assertFalse(popUpLogin.isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInsecond (long timeInSecond) {
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

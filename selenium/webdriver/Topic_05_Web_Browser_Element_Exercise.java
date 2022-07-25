package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_Exercise {
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
	public void TC_01_verifyUrl() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	}
//	@Test
	public void TC_02_verifyTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}
//	@Test
	public void TC_03_navigationFunction() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}
	@Test
	public void TC_04_getPageSource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

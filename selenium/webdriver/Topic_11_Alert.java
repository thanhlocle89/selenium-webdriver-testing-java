package webdriver;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String autoITFireFox = projectPath + "\\autoITAuthentication\\authen_firefox.exe";
	Alert alert;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Simple_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}
	

//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
//	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String promptMessage = "Welcome to Prompt Alert";
		alert.sendKeys(promptMessage);
		alert.accept();
//		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")), "You entered: "+ promptMessage);
		Assert.assertTrue(driver.findElement(By.cssSelector("p#result")).getText().contains(promptMessage), promptMessage);
		
		
	}
//	@Test
	public void TC_04_Authentication_Alert() {
//		http://the-internet.herokuapp.com/basic_auth
		String url = authenticationUrl("admin", "admin", "http://the-internet.herokuapp.com/basic_auth");
		driver.get(url);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
//	@Test
	public void TC_05_Authentication_Alert_Navigate_From_Other_Page() {
//		http://the-internet.herokuapp.com/basic_auth
//		String originUrl = "http://the-internet.herokuapp.com/";
		driver.get("http://the-internet.herokuapp.com/");
		String newUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(newUrl);
		String url = authenticationUrl("admin", "admin", newUrl);
		driver.get(url);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
//		http://the-internet.herokuapp.com/basic_auth
		String userName = "admin";
		String passWord = "admin";
		Runtime.getRuntime().exec(new String[] {autoITFireFox,userName,passWord}); 	
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInsecond(20);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	public String authenticationUrl (String userName, String passWord, String url) {
		String[] split = url.split("//");
//		format: https:/admin:password@url
		String authenticationUrl = split[0] + userName + ":" + passWord + "@" + split[1];
		return authenticationUrl;
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

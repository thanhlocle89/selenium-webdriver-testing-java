package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_TextBox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String webUrl,emailAddrress,userId,passWord,customerName,gender,dateOfBirthInput,dateOfBirthOutput;
	String addressInput,addressOutput,city,state,PIN,mobileNumber;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		webUrl = driver.getCurrentUrl();
		emailAddrress = "thanhloc" + generateRandomNumber() + "@hotmail.net";
	}

	@Test
	public void TC_01_Register_Login_() {
//		register
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("")).sendKeys(emailAddrress);
		driver.findElement(By.name("btnLogin")).click();
		
		
		userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
//		Login
		driver.get(webUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id :"+userId);
	}
	@Test
	public void TC_02_Create_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		customerName = "Selenium Online";
		gender = "male";
		dateOfBirthInput = "10/01/2000";
		dateOfBirthOutput = "2000-01-10";
		addressInput = "123 PO Box\nLos Angeles\nUnited State";
		addressOutput = "123 PO Box Los Angeles United State";
		city = "New York";
		state = "Chicago";
		PIN = "123654";
		mobileNumber = "0912139685";
		
//		Input
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		
		WebElement dateOfBirthTextbox = driver.findElement(By.xpath("//input[@name='dob']"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dateOfBirthTextbox);
		
		dateOfBirthTextbox.sendKeys(dateOfBirthInput);
		driver.findElement(By.xpath("//input[@name='addr']")).sendKeys(addressInput);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(PIN);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddrress);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
//		Verify
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),PIN);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddrress);
		
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

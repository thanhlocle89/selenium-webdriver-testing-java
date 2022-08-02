package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Default_Drop_Down {
	WebDriver driver;
//	Khai bao bien select
	Select select;
	JavascriptExecutor javaExecutor;
	String projectPath = System.getProperty("user.dir");
	String gender, firstName, lastName, day, month, year, companyName, password, emailAddress;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		javaExecutor = (JavascriptExecutor) driver; 
		driver.manage().window().maximize();

		gender = "male";
		firstName = "Le";
		lastName = "Loc";
		day = "20";
		month = "May";
		year = "1989";
		companyName = "xinhacoi";
		password = "123456";
		emailAddress = "lethanhloc"+generateRandomNumber()+"@hotmail.net";
	}

//	@Test
	public void TC_NoCommerce() {
//		I-Action (Input Data)
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
//		Bao gio dung de thao tac voi element thi moi khoi tao
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

//		Thao tac vs Dropdown
		select.selectByVisibleText("20");
//		Kiem tra xem da chon dc chua
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "20");
//		kiem tra xem co phai multiple 		
		Assert.assertFalse(select.isMultiple());
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1989");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1989");
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);	
		driver.findElement(By.id("register-button")).click();	

//	II-Verify(Output Data)
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
	}

//	@Test
	public void TC_02_Drop_Down_List_P_II() {
		driver.get("https://rode.com/en/support/where-to-buy");
		select = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Select country from list below");
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		System.out.println(select.getOptions().size());
	}
	
	@Test
	public void TC_03_HTML_Drop_Down() {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		select = new Select(driver.findElement(By.id("Person_Role__c")));
		
		WebElement item = driver.findElement(By.xpath("//button[text()='REGISTER NOW']"));
		javaExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
		select.selectByVisibleText("*Job Function");
		select.selectByVisibleText("SDET / Test Automation Engineer");
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		select = new Select(driver.findElement(By.id("Test_Framework__c")));
		select.selectByVisibleText("*Existing Test Framework");
		select.selectByVisibleText("Selenium");
		
		select = new Select(driver.findElement(By.id("Self_Report_Country__c")));
		select.selectByVisibleText("*Country");
		select.selectByVisibleText("Germany");
		
	}
	@AfterClass
	public void afterClass() {
//		driver.quit();
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

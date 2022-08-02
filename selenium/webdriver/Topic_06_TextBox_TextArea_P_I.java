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

public class Topic_06_TextBox_TextArea_P_I {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String webUrl,emailAddrress,userId,passWord,customerName,gender,dateOfBirthInput,dateOfBirthOutput;
	String addressInput,addressOutput,city,state,PIN,mobileNumber;
	
	
	String firstName, lastName, employeeID, number, comment;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		webUrl = driver.getCurrentUrl();
		emailAddrress = "thanhloc" + generateRandomNumber() + "@hotmail.net";
	}

//	@Test
	public void TC_01_Register_Login_() {
		driver.get("https://demo.guru99.com/v4/");
//		register
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddrress);
		driver.findElement(By.name("btnLogin")).click();
		
		
		userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
//		Login
		driver.get(webUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : "+userId);
	}
//	@Test
	public void TC_02_Create_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		customerName = "Selenium Online";
		gender = "male";
		dateOfBirthInput = "10/01/2000";
		dateOfBirthOutput = "2000-10-01";
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
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
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
	
	@Test
	public void TC_03_Textbox_TextArea_P_II() {
		By firstNameElement = By.cssSelector("input[title='First Name']");
		By lastNameElement = By.cssSelector("input[title='Last Name']");
		By employeeIDElement = By.id("personal_txtEmployeeId");
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
//		Input
		firstName = "Le";
		lastName = "Loc";
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		
//		Verify
		Assert.assertEquals(driver.findElement(firstNameElement).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNameElement).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(employeeIDElement).getAttribute("value"), employeeID);
		
		Assert.assertFalse(driver.findElement(firstNameElement).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameElement).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDElement).isEnabled());
		
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		Assert.assertTrue(driver.findElement(firstNameElement).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameElement).isEnabled());
		
//		Input
		firstName = "Do";
		lastName = "Oanh";
		driver.findElement(firstNameElement).clear();
		driver.findElement(firstNameElement).sendKeys(firstName);
		driver.findElement(lastNameElement).clear();
		driver.findElement(lastNameElement).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#btnSave")).click();
//		Verify
		Assert.assertEquals(driver.findElement(firstNameElement).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNameElement).getAttribute("value"),lastName);
		Assert.assertFalse(driver.findElement(firstNameElement).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameElement).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDElement).isEnabled());
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.cssSelector("input#btnAdd")).click();
//		Input
		number = "123456";
		comment = "Very good";
		driver.findElement(By.id("immigration_number")).sendKeys(number);
		driver.findElement(By.id("immigration_comments")).sendKeys(comment);
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
//		verify
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
//		WebElement numberTextbox = driver.findElement(By.id("immigration_number"));
//		jsExecutor.executeScript("arguments[0].disabled = true", numberTextbox);
		
//		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number ']/following-sibling::input")).getText(), number);
//		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/following-sibling::textarea")).getText(), comment);
//		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getText(), number);
		String hiddenText = driver.findElement(By.id("immigration_number")).getAttribute("value");
		Assert.assertEquals(hiddenText, number);
		hiddenText = driver.findElement(By.id("immigration_comments")).getAttribute("value");
		Assert.assertEquals(hiddenText, comment);
		
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
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

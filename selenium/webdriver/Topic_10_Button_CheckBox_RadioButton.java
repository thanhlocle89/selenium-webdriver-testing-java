package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_CheckBox_RadioButton {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInsecond(3);
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
//		Kiem tra xem login button dang disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("lethanhloc89@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
//		Kiem tra xem login button dang enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		sleepInsecond(3);
		sleepInsecond(3);
		String backgroundColorHexa = Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(backgroundColorHexa, "#C92127");
		
	}
	@Test
	public void TC_02_Default_Checkbox_And_Radio_Button()	{
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualZoneCheckbox = By.xpath("//input[@id= 'eq5']");
//		chon
		driver.findElement(dualZoneCheckbox).click();
		sleepInsecond(2);
//		Verify da duoc chon
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
//		bo chon
		driver.findElement(dualZoneCheckbox).click();
		sleepInsecond(2);
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By radioButton = By.xpath("//input[@id='engine3']");
		driver.findElement(radioButton).click();
		
		if(!driver.findElement(radioButton).isSelected())
		{
			driver.findElement(radioButton).click();
			Assert.assertTrue(driver.findElement(radioButton).isSelected());
		}
		
		
		
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

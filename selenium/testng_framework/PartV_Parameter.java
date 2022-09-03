package testng_framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class PartV_Parameter {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		switch (browerName) {
		case "firefox": {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		case "edge": {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		}
		default:
			throw new RuntimeException("Unexpected value: " + browerName);
		}
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Parameters("environment")
	@Test
	public void TC_01_LoginToSystem(String envName){
		String envUrl =getEnvironmenturl(envName);
		System.out.println(envUrl);
		driver.get(envUrl+"index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	public String getEnvironmenturl(String envName) {
		if(envName.equals("dev")) {
			return "http://dev.techpanda.org/";
		}else if(envName.equals("test")) {
			return "http://test.techpanda.org/";
		}else if(envName.equals("live")) {
			return "http://live.techpanda.org/";
		}else {
			return null;
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

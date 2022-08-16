package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windown_Tab {
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
	public void TC_01_Basic_Form() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInsecond(5);
		String parentId= driver.getWindowHandle();
		String parentTitle = "SELENIUM WEBDRIVER FORM DEMO";
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		String expectedTitle = "Google";
		switchWindownByTitle(expectedTitle);
		sleepInsecond(5);
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		
		
		switchWindownByTitle(parentTitle);
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		
		expectedTitle = "Facebook – log in or sign up";
		sleepInsecond(5);
		switchWindownByTitle(expectedTitle);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		
		
		
		switchWindownByTitle(parentTitle);
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		
		
		expectedTitle = "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh";
		sleepInsecond(5);
		switchWindownByTitle(expectedTitle);
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		
		
		closeAllWindownWithoutParent(parentId);
		switchWindownByTitle(parentTitle);
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), parentTitle);
		
	}
	
//	@Test
	public void TC_02_Basic_TechPanda() {
		driver.get("http://live.techpanda.org/");
		sleepInsecond(5);
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInsecond(5);
		switchWindownByID(parentID);
		parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInsecond(5);
		switchWindownByID(parentID);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Alert alert = driver.switchTo().alert();
		sleepInsecond(5);
		alert.accept();
		sleepInsecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Cambridge_Dictionary() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[contains(@class,'login-button')]//span[text()='Đăng nhập']")).click();
		sleepInsecond(5);
		
		switchWindownByID(parentID);
		driver.findElement(By.xpath("//input[@value ='Log in']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'control-textbox')]/span[text()='This field is required']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'control-password')]/span[text()='This field is required']")).isDisplayed());
		String childernID = driver.getWindowHandle();

		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
		driver.findElement(By.xpath("//input[@value ='Log in']")).click();
		sleepInsecond(5);
		
		switchWindownByID(childernID);
		sleepInsecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Automation FC']")).isDisplayed());

	}

	public void switchWindownByID(String ParentID) {
		Set<String> listWindownID = driver.getWindowHandles();
		for (String ID : listWindownID) {
			if (!ID.equals(ParentID)) {
				driver.switchTo().window(ID);
			}
		}
	}

	public void switchWindownByTitle(String expectedTitle) {
		Set<String> listWindownID = driver.getWindowHandles();
		for (String ID : listWindownID) {
			driver.switchTo().window(ID);
			if(driver.getTitle().equals(expectedTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindownWithoutParent(String parentID) {
		Set<String> listWindownID = driver.getWindowHandles();
		for (String ID : listWindownID) {
			if(!ID.equals(parentID)) {
				driver.switchTo().window(ID);
				driver.close();
				sleepInsecond(5);
			}
		}
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

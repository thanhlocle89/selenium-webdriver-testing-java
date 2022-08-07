package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Custom_Checkbox_RadioButton {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor executor;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		executor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By summerRadio = By.xpath("//input[@value='Summer']/parent::span/input");
		sleepInsecond(3);
		executor.executeScript("arguments[0].click();", driver.findElement(summerRadio));
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(summerRadio).isSelected());
		if(!driver.findElement(summerRadio).isSelected()){
			executor.executeScript("arguments[0].click();", summerRadio);
		}
		sleepInsecond(3);
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		executor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		executor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		
		executor.executeScript("arguments[0].click();",driver.findElement(indeterminateCheckbox));
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		executor.executeScript("arguments[0].click();", driver.findElement(indeterminateCheckbox));
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
		
	}

	@Test
	public void TC_02() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By canthoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");
		
		executor.executeScript("arguments[0].click();",driver.findElement(canthoRadio));
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
		
		
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

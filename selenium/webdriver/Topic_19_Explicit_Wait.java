package webdriver;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Invisible_3() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait = new WebDriverWait(driver, 3);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
	
//	@Test
	public void TC_01_Invisible_5() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait = new WebDriverWait(driver, 5);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
	
//	@Test
	public void TC_01_Invisible_10() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait = new WebDriverWait(driver, 10);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
	
//	@Test
	public void TC_02_Visible_3() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
//	@Test
	public void TC_02_Visible_5() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
//	@Test
	public void TC_02_Visible_10() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}
	
//	@Test
	public void TC_03_Explicit_Wait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		Assert.assertEquals(driver.findElement(By.cssSelector("span[id*=ContentPlaceholder1_Label1]")).getText(),"No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='17']")));
		driver.findElement(By.xpath("//a[text()='17']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=ContentPlaceholder1_RadCalendar1]>div.raDiv")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='17']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span[id*=ContentPlaceholder1_Label1]")).getText(),"Wednesday, August 17, 2022");
	}
	
//	@Test
	public void TC_04_Upload_File() {
		
		String imageName1 = "image1.jpg";
		String imageName2 = "image2.jpg";
		String imageName3 = "image3.jpg";
		String imageUploadFolder = projectPath + File.separator + "ImageUpload" + File.separator;
		String image1FilePath, image2FilePath, image3FilePath;
		image1FilePath = imageUploadFolder + imageName1;
		image2FilePath = imageUploadFolder + imageName2;
		image3FilePath = imageUploadFolder + imageName3;
		
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait = new WebDriverWait(driver, 15);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(image1FilePath + "\n" + image2FilePath + "\n" + image3FilePath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='image1.jpg']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='image2.jpg']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='image3.jpg']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image3.jpg']")).isDisplayed());
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

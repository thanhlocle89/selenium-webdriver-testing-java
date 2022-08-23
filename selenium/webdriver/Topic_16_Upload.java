package webdriver;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String imageName1 = "image1.jpg";
	String imageName2 = "image2.jpg";
	String imageName3 = "image3.jpg";
	String imageUploadFolder = projectPath + File.separator + "ImageUpload" + File.separator;
	String image1FilePath, image2FilePath, image3FilePath;

// change
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		image1FilePath = imageUploadFolder + imageName1;
		image2FilePath = imageUploadFolder + imageName2;
		image3FilePath = imageUploadFolder + imageName3;
	}

//	@Test
	public void TC_01_Upload_Single() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInsecond(3);

		By uploadFile = By.cssSelector("input[type='file']");

		driver.findElement(uploadFile).sendKeys(image1FilePath);
		driver.findElement(uploadFile).sendKeys(image2FilePath);
		driver.findElement(uploadFile).sendKeys(image3FilePath);
		sleepInsecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image3.jpg']")).isDisplayed());
		sleepInsecond(3);

		List<WebElement> buttonStarts = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInsecond(2);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image3.jpg']")).isDisplayed());
	}
	@Test
	public void TC_02_Upload_Multiple() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInsecond(3);

		By uploadFile = By.cssSelector("input[type='file']");
		driver.findElement(uploadFile).sendKeys(image1FilePath + "\n" + image2FilePath + "\n" + image3FilePath);
		sleepInsecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='image3.jpg']")).isDisplayed());
		sleepInsecond(3);

		List<WebElement> buttonStarts = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInsecond(2);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='image3.jpg']")).isDisplayed());
		
		
		
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

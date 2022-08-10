package webdriver;

import java.util.List;
import java.util.Random;
import java.util.ResourceBundle.Control;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_User_Interactions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action; 
	JavascriptExecutor executor;
	Alert alert;
// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
		
	}

//	@Test
	public void TC_01_HoverToElement() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
//	@Test
	public void TC_02_HoverToElement() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}
	
//	@Test
	public void TC_03_HoverToElement() {
		driver.get("https://fptshop.com.vn/");
		action.moveToElement(driver.findElement(By.xpath("//a[contains(string(),' LAPTOP ')]"))).perform();
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Acer']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://fptshop.com.vn/may-tinh-xach-tay/acer");
	}
//	@Test
	public void TC_04_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
		
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		sleepInsecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 4);
	}
//	@Test
	public void TC_05_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
		
		action.keyDown(Keys.CONTROL).moveToElement(allNumber.get(0)).click().moveToElement(allNumber.get(3)).click()
		.moveToElement(allNumber.get(9)).click().release().perform();

		
		sleepInsecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 4);
	}
//	@Test
	public void TC_06_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClickButton =  driver.findElement(By.xpath("//button[text() = 'Double click me']"));
		
		executor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
		action.doubleClick(doubleClickButton).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Hello Automation Guys!']")).isDisplayed());
	}
//	@Test
	public void TC_07_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		WebElement quitButton = driver.findElement(By.xpath("//span[text()='Quit']"));
		action.moveToElement(quitButton).perform();
		
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover')]")).isDisplayed());
		quitButton.click();
		alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertFalse(quitButton.isDisplayed());
	}
	@Test
	public void TC_08_DragAndDropHTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement target  = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), target).perform();
		sleepInsecond(3);
		Assert.assertEquals(target.getText(), "You did great!");
		
//		String bgColor = target.getCssValue("background-color");
//		String bgColorHex = Color.fromString(target.getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(Color.fromString(target.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
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

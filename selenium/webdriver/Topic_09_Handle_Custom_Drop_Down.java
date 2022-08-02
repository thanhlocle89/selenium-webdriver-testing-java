package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Custom_Drop_Down {
	WebDriver driver;
	WebDriverWait explixitWait;
	JavascriptExecutor jvExecutor;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jvExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explixitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_J_Query() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	
		selectItemDropDownCustomHaveScroll("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals("19", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
		
		selectItemDropDownCustomHaveScroll("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals("10", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
		
		selectItemDropDownCustomHaveScroll("span#number-button", "ul#number-menu>li>div", "15");
		Assert.assertEquals("15", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
//		driver.findElement(By.cssSelector("span#number-button")).click();
//		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
//		
//		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
//		for (WebElement webElement : allItems) {
//			String text = webElement.getText();
//			if (text.equals("5")) {
//				webElement.click();
//				break;
//			}
//		}
		
		
	}

	public void selectItemDropDownCustom( String parentLocator,String itemLocator,String expectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInsecond(2);
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemLocator)));
		
		List<WebElement> allItems = driver.findElements(By.cssSelector(itemLocator));
		for (WebElement webElement : allItems) {
			String text = webElement.getText();
			if (text.equals(expectedItem)) {
				sleepInsecond(2);
				webElement.click();
				sleepInsecond(2);
				break;
			}
		}
	}
	public void selectItemDropDownCustomHaveScroll( String parentLocator,String itemLocator,String expectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInsecond(2);
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemLocator)));
		
		List<WebElement> allItems = driver.findElements(By.cssSelector(itemLocator));
		for (WebElement webElement : allItems) {
			String text = webElement.getText();
			if (text.equals(expectedItem)) {
				jvExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
				sleepInsecond(3);
				explixitWait.until(ExpectedConditions.elementToBeClickable(webElement));
				webElement.click();
				sleepInsecond(2);
				break;
			}
		}
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

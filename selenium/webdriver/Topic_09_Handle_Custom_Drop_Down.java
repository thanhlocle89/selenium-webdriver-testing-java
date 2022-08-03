package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
	WebElement item;
// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jvExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explixitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_J_Query() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	
		selectItemDropDownCustomBySeleniumOrJavaExecutor("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "5");
		Assert.assertEquals("5", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
		
		selectItemDropDownCustomBySeleniumOrJavaExecutor("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals("10", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
		
		selectItemDropDownCustomBySeleniumOrJavaExecutor("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals("19", driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText());
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
//	@Test
	public void TC_02_ReAct_JS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	
		selectItemDropDownCustom("//div[@class='ui fluid selection dropdown']", "//div[@style='pointer-events:all']/span", "Elliot Fu");
		Assert.assertEquals("Elliot Fu", driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']/div[@class='divider text']")).getText());
	}
	
//	@Test
	public void TC_03_Vue() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
	
		selectItemDropDownCustom("//div[@id='app']/div[@class='btn-group']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
		Assert.assertEquals("Second Option", driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText());
	}
//	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	
		selectEditableItemDropDownCustom("//input[@class='search']", "//div[@class='visible menu transition']/div/span", "Algeria");
		Assert.assertEquals("Algeria", driver.findElement(By.xpath("//div[@class='divider text']")).getText());
	}
	public void selectItemDropDownCustom( String parentLocator,String itemLocator,String expectedItem) {
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInsecond(2);
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));
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
	public void selectEditableItemDropDownCustom( String parentLocator,String itemLocator,String expectedItem) {
		driver.findElement(By.xpath(parentLocator)).clear();
//		Tim den phan tu roi truyen vao gias tri can dien
		driver.findElement(By.xpath(parentLocator)).sendKeys(expectedItem);
		sleepInsecond(2);
//		doi cho den khi load het duoc cac gia tri trong drop down list
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
//		Dua tat ca cac phan tu trong drop down vao 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));
//		Duyet tat ca cac phan tu trong list bang vong lap for
		for (WebElement webElement : allItems) {
			String text = webElement.getText();
//			Neu phan tu bang voi phan tu mong muon thi con phan tu do va thoat khoi vong lap
			if (text.equals(expectedItem)) {
//				scroll den phan tu can nhap
				jvExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
				sleepInsecond(3);
				explixitWait.until(ExpectedConditions.elementToBeClickable(webElement));
				webElement.click();
				sleepInsecond(2);
				break;
			}
		}
	}
	public void selectItemDropDownCustomBySeleniumOrJavaExecutor( String parentLocator,String itemLocator,String expectedItem) {
		
//		Tim den phan tu roi truyen vao gias tri can dien
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInsecond(2);
//		doi cho den khi load het duoc cac gia tri trong drop down list
		explixitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
//		Dua tat ca cac phan tu trong drop down vao 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));
//		Duyet tat ca cac phan tu trong list bang vong lap for
		
		for (WebElement item : allItems) {
//			Neu phan tu bang voi phan tu mong muon thi con phan tu do va thoat khoi vong lap
			if (item.getText().equals(expectedItem)) {
//				scroll den phan tu can nhap
//				jvExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
//				sleepInsecond(3);
				jvExecutor.executeScript("arguments[0].click();", item);
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

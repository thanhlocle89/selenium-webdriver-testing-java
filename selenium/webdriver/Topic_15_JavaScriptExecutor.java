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

public class Topic_15_JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

//	@Test
	public void TC_01_TechPanda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		String domainPage  = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainPage, "live.techpanda.org");
		
		String urlPage  = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlPage, "http://live.techpanda.org/");
		
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button[@title='Add to Cart']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		String titlePage  = (String) executeForBrowser("return document.title");
		Assert.assertEquals(titlePage, "Customer Service");
		
		scrollToElementOnDown("//input[@id='newsletter']");
		String emailAddrress = "thanhloc" + generateRandomNumber() + "@hotmail.net";
		sendkeyToElementByJS("//input[@id='newsletter']",emailAddrress);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String domainPageGuru  = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainPageGuru, "demo.guru99.com");
	}

	@Test
	public void TC_02_HTML5() {
		navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
		sleepInSecond(5);
		
		clickToElementByJS("//button[text()='Đăng ký']");	
		String validationMessage = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='lastName']","Le");
		
		clickToElementByJS("//button[text()='Đăng ký']");	
		validationMessage = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='firstName']","Loc");
		
		clickToElementByJS("//button[text()='Đăng ký']");	
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='email']","leloc89@hotmail.com");
		
		clickToElementByJS("//button[text()='Đăng ký']");	
		validationMessage = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='password']","leloc89@hotmail.com");
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}

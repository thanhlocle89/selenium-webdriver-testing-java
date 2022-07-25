package webdriver;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	WebElement element;
//	khai bao + khoi tao
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		khoi tao
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test 
	  public void TC_01_Browser() { 
		//Cac ham tuong tac voi browser thong qua bien driver 
	  driver.close();
	  // Dong tab, window dang active 
	  driver.quit();
	  //Dong browser 
	  // tim 1 element 
	  WebElement logingButton =driver.findElement(By.cssSelector("")); 
	  // tim nhieu element 
	  List<WebElement>links = driver.findElements(By.id("")); 
	  // mo ra 1 url
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html"); 
	  // tra ve url cua page dang dung 
	  String currentUrl = driver.getCurrentUrl();
	  
	  String title = driver.getTitle(); 
	  // Source code cua page hien tai 
	  String pageSource = driver.getPageSource(); 
	  // lay ra ID cua tab/window dang dung/active( Window/tab) 
	  driver.getWindowHandle();// 1
	  driver.getWindowHandles();// tat ca
	  
	  driver.manage().getCookies();// trong phan framework
	  
	  driver.manage().window().fullscreen(); driver.manage().window().maximize();
	  // cho element duoc tim thay trong time xx giay
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
	  // cho pageload thanh con trong xx giay 
	  driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS); 
	  // cho cho script duoc inject thanh cong trong xx giay
	  driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	  
	  driver.navigate().back(); driver.navigate().forward();
	  driver.navigate().refresh();
	  driver.navigate().to("https://docs.google.com/presentation"); 
	  // Alert/Frame( IFrame ) /Window(Tab) driver.switchTo().alert();
	  driver.switchTo().frame(0); driver.switchTo().window(""); }

	@Test
	public void TC_02_Elements() {
//Cac ham tuong tac voi Elements thong qua bien elements
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		WebElement emailTextbox = driver.findElement(By.id("txtEmail"));
		emailTextbox.clear();
		emailTextbox.sendKeys("Le Thanh Loc");
		// xoa du lieu trong 1 filed editable(textbox, textArea,Editable DropDown )
		element.clear();
		// Nhap du lieu vao 1 field editable element.sendKeys("");
		element.sendKeys(Keys.ENTER);

//		  lay ra gia tri trong 1 attribute cua element
		element = driver.findElement(By.id("txtEmail"));
		String textExample = element.getAttribute("placeholder");
// Tra ve thuoc tinh cua CSS
		// font, size, color
//		tra ve mau sau
		element.getCssValue("background-color");
//		tra ve font size
		element.getCssValue("font-size");
//		
		element.getLocation();
		element.getRect();
		element.getSize();
		
//		chup hinh va dua vao report
		element.getScreenshotAs(OutputType.FILE);
//	tra ve the html cua element
		element.getTagName();
		
//		tra ve text cua 1 element( Link/header/messager error)
		element.getText();

		//		tra ve dung hoac sai cua element co hien thi hay ko

		element.isDisplayed();
//		tra ve element da duoc chon hay chua
//		checkbox/radio
		element.isSelected();
		
//		tra ve dung hoac sai cua element co the thao tac dc hay ko
//		co bij disable ko
		element.isEnabled();
		
//		chi lam viec duoc voi form
//		login/register
//		tuong tu voi viec nhan ENTER
		element.submit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

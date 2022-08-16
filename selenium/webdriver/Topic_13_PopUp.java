package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_PopUp {
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

//	@Test
	public void TC_01_Fixed_Popup_NN24h() {
		driver.get("https://ngoaingu24h.vn/");

		WebElement popUpLogin = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));

		Assert.assertFalse(popUpLogin.isDisplayed());

		driver.findElement(By.cssSelector("button.login_.icon-before")).click();

		Assert.assertTrue(popUpLogin.isDisplayed());

		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();

		Assert.assertFalse(popUpLogin.isDisplayed());
	}

//	@Test
	public void TC_02_Fixed_Popup_Kyna() {
		driver.get("https://kyna.vn/");

		WebElement popUpLogin = driver.findElement(By.cssSelector("div#k-popup-account-login"));

		Assert.assertFalse(popUpLogin.isDisplayed());

		driver.findElement(By.cssSelector("a.login-btn")).click();

		Assert.assertTrue(popUpLogin.isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();

		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close.close")).click();

		Assert.assertFalse(popUpLogin.isDisplayed());
	}

//	@Test
	public void TC_03_Random_Popup_InDom() {
		driver.get("https://blog.testproject.io/");

		WebElement popUpLogin = driver.findElement(By.cssSelector("div#mailch-bg"));
		sleepInsecond(3);
		if (popUpLogin.isDisplayed()) {

			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		String keyWord = "Selenium";
		driver.findElement(By.cssSelector("aside.widget-area input.search-field")).sendKeys(keyWord);
		driver.findElement(By.cssSelector("aside.widget-area span.glass")).click();

		sleepInsecond(5);

		List<WebElement> listArticle = driver.findElements(By.xpath("//a/parent::h3"));

		for (WebElement webElement : listArticle) {
			Boolean articleTitle = webElement.getText().contains(keyWord);
			Assert.assertTrue(articleTitle);

			System.out.println(articleTitle);
		}

	}

//	@Test
	public void TC_04_Random_PopUp_InDOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInsecond(15);

		WebElement popupTC = driver.findElement(By.cssSelector("div.tve-leads-conversion-object"));
		if (popupTC.isDisplayed()) {
//			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(@class,'tve_ea_thrive_leads_form_close')]")));
		}

		Assert.assertEquals(driver.findElement(By.cssSelector("p.commit")).getText().toUpperCase(),
				"NÂNG TẦM GIÁ TRỊ - KẾT NỐI THÀNH CÔNG");

	}

//	@Test
	public void TC_05_Random_PopUP_N_IN_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInsecond(15);
	}
	
//	@Test
	public void TC_06_iFrame_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInsecond(5);
		
		WebElement fbIframe = driver.findElement(By.cssSelector("div.face-content iframe"));
		Assert.assertTrue(fbIframe.isDisplayed());
		
		driver.switchTo().frame(fbIframe);
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		sleepInsecond(3);
	
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		sleepInsecond(3);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0912122133");
		
		Select chatDropDown = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
		chatDropDown.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("This is testing scenario");
		sleepInsecond(3);
		
		driver.switchTo().defaultContent();
		String keyword = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> listKhoaHoc = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement webElement : listKhoaHoc) {
			Assert.assertTrue(webElement.getText().contains(keyword));
			System.out.println(webElement.getText());
		}
		
	}
	
	@Test
	public void TC_07_Frame_HDFCBank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("lethanhloc");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		sleepInsecond(3);
		
		
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

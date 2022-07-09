package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {
	// Khai bao bien de dai dien cho thu vien Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		// Buoc 1: mo Brower len
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Bam cho maximize Brower
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		// Buoc 2: Nhap url
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Buoc 3: Click vao account link de mo trang login

		// Xpath Format: //tagname[@attribute-name='attribute-value']
		// OR //tagname[@attribute-name="attribute-value"]
		// Vi du
		// HTML cua element Email Textbox
		// <input type="email" autocapitalize="email" ="off"
		// spellcheck="false" name="login[username]" value="" id="email"
		// class="input-text required-entry validate-email" title="Email Address">

		// tagname : input
		// attribute name : type autocapitalize autocorrect spellcheck name value class
		// title
		// attribute value : email email off false login[username] email input-text
		// required-entry validate-email Email Address

		// Xpath se la
		// input[@type='email']
		// input[@autocapitalize='email']
		// input[@autocorrect='off']
		// input[@name='login[username]']-> dung duoc
		// input[@id='email'] -> dung duoc
		// input[@class='input-text required-entry validate-email']
		// input[@title='Email Address'] -> dung duoc

		// CSS Format: tagname[attribute-name='attribute-value'] ( bo // va @)
		// Tim 1 Element

		// ID
		driver.findElement(By.id("email"));

		// Class: new-user form
		// Gia tri khong chua khoang trang -> lay het
		// Gia tri chua khoang trang -> lay 1 phan
		driver.findElement(By.className("new-users"));

		// Name - Email Textbox
		driver.findElement(By.name("login[username]"));

		// TagName - Tim xem co bao nhieu element/ page
		driver.findElements(By.tagName("a"));

		// LinkText -Text Tuyet Doi
		driver.findElement(By.linkText("SITE MAP"));

		// Partial LinkText -Text Tuong Doi
		driver.findElement(By.partialLinkText("SITE MAP"));
		driver.findElement(By.partialLinkText("SITE M"));

		// Css - Cover duoc 6 loai tren
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[title='Email Address']"));

		// Xpath
		driver.findElement(By.xpath("//input[@name='login[username]']"));
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@title='Email Address']"));
		// Tim nhieu Elements
		// driver.findElements(null);

		// Click vao link
	}


	@AfterClass
	public void afterClass() {
		// Dong Brower
		driver.quit();
	}
}

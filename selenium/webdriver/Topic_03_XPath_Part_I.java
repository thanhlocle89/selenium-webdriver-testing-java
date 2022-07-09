package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		// Buoc 1: Open techpanda
		driver.get("http://live.techpanda.org/");

		// Buoc 2: Tim den My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));

		// HTML checkbox subscribe element
		// <input type="email" autocapitalize="off" autocorrect="off" spellcheck="false"
		// name="email" id="newsletter" title="Sign up for our newsletter"
		// class="input-text required-entry validate-email">

		// Tag name: input
		// Attribute name: type autocapitalize autocorrect spellcheck name id title
		// class
		// Attribute value: email off off false email newsletter Sign up for our
		// newsletter input-text required-entry validate-email
		// Tim theo xpath
		driver.findElement(By.xpath("//input[@id='newsletter']"));

		// Tim theo css
		driver.findElement(By.cssSelector("input[id='newsletter']"));

		// Tim theo classname
		driver.findElement(By.className("block-content"));

		// Tim theo id
		driver.findElement(By.id("newsletter"));

		// Tim theo name
		driver.findElement(By.name("email"));

		// Tim theo Linktext
		driver.findElement(By.linkText("SITE MAP"));

		// Tim theo Partial Linktext
		driver.findElement(By.partialLinkText("SITE MAP"));

		// Tim theo Tagname
		driver.findElements(By.tagName("a"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

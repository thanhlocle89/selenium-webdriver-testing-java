package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_Excercise_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

// change
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Is_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Email textbox
		WebElement emailTextbox =driver.findElement(By.cssSelector("input#mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("lethanhloc89@gmail.com");
			System.out.println("Email textbox is displayed");
			
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
//		Age under 18 button
		WebElement ageUnder18Button = driver.findElement(By.cssSelector(" input#under_18"));
		if (ageUnder18Button.isDisplayed()) {
			ageUnder18Button.click();
			System.out.println("Age Under  18 Radio Button is displayed");
		} else {
			System.out.println("Age Under  18 Radio Button is not displayed");
		}
		
		
		
//		Textarea Education
		WebElement eduTextArea = driver.findElement(By.cssSelector("textarea#edu"));
		if (eduTextArea.isDisplayed()) {
			eduTextArea.sendKeys("UET");
			System.out.println("Education Text area is display");
			
		} else {
			System.out.println("Education Text area is not display");
		}
//		Image 5: Name User5 not displayed
		
		WebElement hidenElement = driver.findElement(By.xpath("//div[@class='figcaption']//h5[text()='Name: User5']"));
		if (hidenElement.isDisplayed()) {
			System.out.println("This element is displayed");
		} else {
			System.out.println("This element is not displayed");
		}
	}

//	@Test
	public void TC_02_Is_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Email textbox
		WebElement emailTextbox =driver.findElement(By.cssSelector("input#mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email textbox is enabled");
			
		} else {
			System.out.println("Email textbox is not enabled");	
		}
//		Age under 18 button
		WebElement ageUnder18Button = driver.findElement(By.cssSelector(" input#under_18"));
		if (ageUnder18Button.isEnabled()) {
			System.out.println("Age Under  18 Radio Button is enabled");
		} else {
			System.out.println("Age Under  18 Radio Button is not enabled");
		}
		
//		Textarea Education
		WebElement eduTextArea = driver.findElement(By.cssSelector("textarea#edu"));
		if (eduTextArea.isEnabled()) {
			System.out.println("Education Text area is enabled");
			
		} else {
			System.out.println("Education Text area is not enabled");
		}
		
//		Single DropDown: Job role 1
		WebElement jobRole1SingleDropdown = driver.findElement(By.cssSelector("select#job1"));
		if (jobRole1SingleDropdown.isEnabled()) {
			System.out.println("Job Role 1 Single Drop Down is enabled");
		} else {
			System.out.println("Job Role 1 Single Drop Down is not enabled");
		}
		
//		Single DropDown: Job role 2
		WebElement jobRole2MultipleDropdown = driver.findElement(By.cssSelector("select#job2"));
		if (jobRole2MultipleDropdown.isEnabled()) {
			System.out.println("Job Role 2 Multiple Drop Down is enabled");
		} else {
			System.out.println("Job Role 2 Multiple Drop Down is not enabled");
		}
//		Development interest checkbox
		WebElement developmentCheckBox = driver.findElement(By.cssSelector("input#development"));
		if (developmentCheckBox.isEnabled()) {
			System.out.println("Development Check box is enabled");
		} else {
			System.out.println("Development Check box is not enabled");
		}
		
//		Slide-1 slide
		WebElement slider1 = driver.findElement(By.cssSelector("input#slider-1"));
		if (slider1.isEnabled()) {
			System.out.println("this slider is enalbed");
		} else {
			System.out.println("this slider is not enalbed");
		}
		
//		Password Text box is disable
		WebElement passWordTextBox = driver.findElement(By.cssSelector("input#disable_password"));
		if (passWordTextBox.isEnabled()) {
			System.out.println("Password textbox is enabled");
			
		} else {
			System.out.println("Password textbox is not enabled");
		}
//		Disable radio button
		WebElement disableRadioButton = driver.findElement(By.cssSelector("input#radio-disabled"));
		if (disableRadioButton.isEnabled()) {
			System.out.println("This radio button is enabled");
		} else {
			System.out.println("This radio button is not enabled");
		}
		
//		Biography Text area is disabled
		WebElement bioTextArea = driver.findElement(By.cssSelector("textarea#bio"));
		if (bioTextArea.isEnabled()) {
			System.out.println("Biography Text Area is enabled");
		} else {System.out.println("Biography Text Area is not enabled");}
		
//		Job role3 dropdown list is disable
		WebElement job3DropDownList = driver.findElement(By.cssSelector("select#job3"));
		if (job3DropDownList.isEnabled()) {
			System.out.println("Job 3 Drop Down List is enalbed");
		} else {
			System.out.println("Job 3 Drop Down List is not enalbed");
		}
		
//		interest checkbox is disable
		WebElement disableCheckBox = driver.findElement(By.cssSelector("input#check-disbaled"));
		if (disableCheckBox.isEnabled()) {
			System.out.println("This check box is enalbed");
		} else {
			System.out.println("This check box is not enalbed");
		}
		
//		Slide-2 is disable
		WebElement disableSlider = driver.findElement(By.cssSelector("input#slider-2"));
		if (disableSlider.isEnabled()) {
			System.out.println("this slider is enabled");
		} else {
			System.out.println("this slider is not enabled");
		}
	}
	
//	@Test
	public void TC_03_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18Button = driver.findElement(By.cssSelector(" input#under_18"));
		ageUnder18Button.click();
		if (ageUnder18Button.isSelected()) {
			System.out.println("Under 18 Radio button is selected");
		} else {
			System.out.println("Under 18 Radio button is not selected");
		}
		WebElement languageJaveCheckBox = driver.findElement(By.cssSelector("input#java"));
		languageJaveCheckBox.click();
		if (languageJaveCheckBox.isSelected()) {
			System.out.println("Java checkbox is selected");
		} else {
			System.out.println("Java checkbox is not selected");
		}
		languageJaveCheckBox.click();
		if (languageJaveCheckBox.isSelected()) {
			System.out.println("Java checkbox is selected");
		} else {
			System.out.println("Java checkbox is not selected");
		}
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@class='fs-mask  av-email']")).sendKeys("lethanhloc89@gmail.com");
//		sleepInsecond(10);
		driver.findElement(By.cssSelector("input#new_username")).click();
		WebElement passWordTextBox = driver.findElement(By.cssSelector("input#new_password"));
		
//		check lower case
		passWordTextBox.sendKeys("aaa");
		sleepInsecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
//		check Upper case
		passWordTextBox.clear();
		passWordTextBox.sendKeys("AAA");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
//		check number case
		passWordTextBox.clear();
		passWordTextBox.sendKeys("123");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
//		check special char case
		passWordTextBox.clear();
		passWordTextBox.sendKeys("!@#");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
//		check 8 char case
		passWordTextBox.clear();
		passWordTextBox.sendKeys("abcABC@@");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
//		check 8 char case
		passWordTextBox.clear();
		passWordTextBox.sendKeys("abcABC@@123");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("input.av-password.success-check")).isDisplayed());
		
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
}

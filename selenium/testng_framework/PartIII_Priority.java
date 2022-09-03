package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class PartIII_Priority {
 @Test(priority = 10,enabled = true,description = "this is enable method")
 public void TC_01() {
	 System.out.println("this is group 1");
 }
 @Test(priority = 9,enabled = false)
 public void TC_02() {
	 System.out.println("this is group 1");
 }
 @Test(priority = 8,enabled = true,description = "this is enable method")
 public void TC_03() {
	 System.out.println("this is group 1");
 }
 @Test(priority = 7,enabled = false)
 public void TC_04() {
	 System.out.println("this is group 1");
 }
 
 @Test(priority = 6,enabled = true,description = "this is enable method")
 public void TC_05() {
	 System.out.println("this is group 2");
 }
 @Test(priority = 5,enabled = false)
 public void TC_06() {
	 System.out.println("this is group 2");
 }
 @Test(priority = 4,enabled = true,description = "this is enable method")
 public void TC_07() {
	 System.out.println("this is group 2");
 }
 @Test(priority = 3,enabled = false)
 public void TC_08() {
	 System.out.println("this is group 2");
 }
 
 
}

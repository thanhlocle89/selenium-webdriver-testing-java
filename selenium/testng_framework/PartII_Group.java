package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class PartII_Group {
 @Test(groups = "group1")
 public void TC_01() {
	 System.out.println("this is group 1");
 }
 @Test(groups = "group1")
 public void TC_02() {
	 System.out.println("this is group 1");
 }
 @Test(groups = "group1")
 public void TC_03() {
	 System.out.println("this is group 1");
 }
 @Test(groups = "group1")
 public void TC_04() {
	 System.out.println("this is group 1");
 }
 
 @Test(groups = "group2")
 public void TC_05() {
	 System.out.println("this is group 2");
 }
 @Test(groups = "group2")
 public void TC_06() {
	 System.out.println("this is group 2");
 }
 @Test(groups = "group2")
 public void TC_07() {
	 System.out.println("this is group 2");
 }
 @Test(groups = "group2")
 public void TC_08() {
	 System.out.println("this is group 2");
 }
 
 
}

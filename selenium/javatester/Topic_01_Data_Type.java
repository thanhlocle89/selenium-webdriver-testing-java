package javatester;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		byte bNumber = 5;
		short snumber = 1000;
		int inumber = 10000;
		long lnumber = 1000000;
		
		float Salary = 15.5f;
		double dSalary = 15.5d;
		
		Date date = new Date();	
		
		WebDriver driver = new FirefoxDriver();
		
		Object student;
		
		int numb[]= {3,5,10};
		String add[]= {"HN","DN","HCM"};
		
		List<Integer> studentNumber = new ArrayList<>();
		List<String> studentAddresses = new ArrayList<>();
		
		Set<String> studentCity = new LinkedHashSet<>(); 
		
		
	}
}

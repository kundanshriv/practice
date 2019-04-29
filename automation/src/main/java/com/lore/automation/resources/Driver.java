package com.lore.automation.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.lore.automation.utils.Constant;
// Singleton class
public class Driver {
	
	
	private static Driver instance;
	private static WebDriver driver;
	private static FileInputStream fis;
	private static Properties prop;
	
	
	private Driver()
	{
		
	}
	
	
	
	public static WebDriver getDriver(String browser)
	{
	
		
		if(driver==null)
		{
			String browserName=Driver.getProperty(browser);
			
			if(browserName.equalsIgnoreCase("chrome"))
			
			{
			System.setProperty(Constant.Chrome_Key, Constant.ChromeValue_Path);
			driver=new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("firefox"))
				
			{
			System.setProperty(Constant.FireFox_Key, Constant.FireFoxValue_Path);
			driver=new FirefoxDriver();
			}
			else if(browserName.equalsIgnoreCase("IE"))
				
			{
			System.setProperty(Constant.IE_Key, Constant.IEValue_Path);
			driver=new InternetExplorerDriver();
			}
			
		}
		return driver;
	}
	
	private static String getProperty(String propName)
	{
		try {
			fis=new FileInputStream(Constant.ConfigFile_Path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prop=new Properties();
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(propName);
		
	}
	
	public static void getUrl(String url)
	{
		driver.get(getProperty(url));
	}
	
	public static String verifyTitle()
	{
		return driver.getTitle();
	}
	
	public static void closeBrowser()
	{
		if(driver!=null)
		{
			driver.quit();
			driver=null;
		}
	}

}

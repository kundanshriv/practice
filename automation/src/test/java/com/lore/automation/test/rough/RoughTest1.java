package com.lore.automation.test.rough;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lore.automation.resources.Driver;



public class RoughTest1 {
	
		
	
	@BeforeMethod
	public void setUp()
	{
		Driver.getDriver("browser");
		Driver.getUrl("url");
		
	}
	
	@Test
	public void titleVerification()
	{
		System.out.println(Driver.verifyTitle());
		Assert.assertTrue("Facebook – log in or sign up".equalsIgnoreCase(Driver.verifyTitle()));
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		Driver.closeBrowser();
		
	}

}

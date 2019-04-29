package com.lore.automation.resources;

import org.testng.annotations.BeforeTest;

public class Base {
	
	
	
	@BeforeTest
	public void getInstanace()
	{
		Driver.getDriver("browser");
	}

}

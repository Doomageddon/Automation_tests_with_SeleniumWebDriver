package com.listatsoftware.Test_Task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainPageTest {

	private WebDriver driver;

	private MainPage mainPage;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en-us");
		driver = new ChromeDriver(options);
		driver.get("https://www.booking.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void quit() {
		driver.quit();
	}
	
	@Test
	public void testDestinationAutocomplete() {
		mainPage = new MainPage(driver).inputDestination("To");
		for(WebElement autocomplete : mainPage.getListDestinationAutocomplete()) {
			System.out.println(autocomplete.getText());
			assertTrue(autocomplete.getText().contains("To"));
		}
	}
	
	@Test
	public void testChangeLanguageAndCurrency() {
		mainPage = new MainPage(driver);
		mainPage.changeCurrency("RUB");
		assertEquals("RUB", mainPage.getCurrentCurrency());
		mainPage.changeCurrency("UAH");
		assertEquals("UAH", mainPage.getCurrentCurrency());
		mainPage.changeLanguage("RU");
		assertEquals("RU", mainPage.getCurrentLanguage());
		mainPage.changeLanguage("UK");
		assertEquals("UK", mainPage.getCurrentLanguage());
	}
}

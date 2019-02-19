package com.listatsoftware.Test_Task;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SearchPageTest {

	private WebDriver driver;

	private MainPage mainPage;

	private SearchPage searchPage;

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
	public void testInputDataMatchesSearchResults() {
		mainPage = new MainPage(driver).inputDestination("New York")
				.selectMounthYearChekIn("September 2019")
				.selectDayChekIn("1")
				.selectMounthYearChekOut("September 2019")
				.selectDayChekOut("30")
				.getTitle()
				.submitData();
		searchPage = new SearchPage(driver).getTitle();
		for (WebElement name : searchPage.getNameResultOfSearch()) {
			assertTrue(name.getText().contains("New York"));
		}
		assertTrue(searchPage.getDateResultOfSearch().get(0).getText().contains("Sunday, September 1, 2019"));
		assertTrue(searchPage.getDateResultOfSearch().get(1).getText().contains("Monday, September 30, 2019"));
	}
}

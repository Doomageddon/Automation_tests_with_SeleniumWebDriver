package com.listatsoftware.Test_Task;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	private final WebDriver driver;

	@FindBy(css = ".jq_tooltip.highlighted_district.district_link.visited_link")
	private List<WebElement> nameResultOfSearch;

	@FindBy(css = ".sb-date-field__display")
	private List<WebElement> dateResultOfSearch;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SearchPage getTitle() {
		System.out.println("Page title is: " + driver.getTitle());
		return this;
	}

	public List<WebElement> getNameResultOfSearch() {
		return nameResultOfSearch;
	}
	
	public List<WebElement> getDateResultOfSearch() {
		return dateResultOfSearch;
	}
}

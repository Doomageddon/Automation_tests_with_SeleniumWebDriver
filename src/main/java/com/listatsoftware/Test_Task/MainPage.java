package com.listatsoftware.Test_Task;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	private final WebDriver driver;

	@FindBy(name = "ss")
	private WebElement destination;

	@FindBy(css = ".sb-date-field__select-field.js-date-field__select")
	private List<WebElement> selectDateList;
	
	@FindBy(css = ".search_hl_name")
	private List<WebElement> listDestinationAutocomplete;
	
	@FindBy(css = "[data-id=language_selector]")
	private WebElement language;
	
	@FindBy(css = "[class~=selected_country]")
	private WebElement currentLanguage;
	
	@FindBy(css = "#current_language_foldout li")
	private List<WebElement> clickToChangeLanguage;
	
	@FindBy(name = "selected_currency")
	private WebElement currentCurrency;
	
	@FindBy(css = ".popover_trigger.long_currency_text")
	private WebElement currency;
	
	@FindBy(css = "#current_currency_foldout li")
	private List<WebElement> clickToChangeCurrency;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public MainPage getTitle() {
		System.out.println("Page title is: " + driver.getTitle());
		return this;
	}
	
	public String getCurrentLanguage() {
		return currentLanguage.getAttribute("data-lang").toUpperCase();
	}
	
	public void changeLanguage(String s) {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.elementToBeClickable(language));
		language.click();
		for(WebElement setLanguage : clickToChangeLanguage) {
			(new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeClickable(setLanguage));
			if(s.toLowerCase().equals(setLanguage.getAttribute("data-lang"))) {
				setLanguage.click();
				break;
			}
		}
	}
	
	public String getCurrentCurrency() {
		return currentCurrency.getAttribute("value");
	}
	
	public void changeCurrency(String s) {
		(new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.elementToBeClickable(currency));
		currency.click();
		for(WebElement setCurrency : clickToChangeCurrency) {
			(new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.elementToBeClickable(setCurrency));
			if(s.equals(setCurrency.getAttribute("data-lang"))) {
				setCurrency.click();
				break;
			}
		}
	}
	
	public List<WebElement> getListDestinationAutocomplete(){
		return listDestinationAutocomplete;
	}
	
	public MainPage inputDestination(String s) {
		destination.sendKeys(s);
		return this;
	}

	public MainPage selectMounthYearChekIn(String s) {
		Select select = new Select(selectDateList.get(0));
		select.selectByVisibleText(s);
		return this;
	}

	public MainPage selectDayChekIn(String s) {
		Select select = new Select(selectDateList.get(1));
		select.selectByValue(s);
		return this;
	}

	public MainPage selectMounthYearChekOut(String s) {
		Select select = new Select(selectDateList.get(2));
		select.selectByVisibleText(s);
		return this;
	}

	public MainPage selectDayChekOut(String s) {
		Select select = new Select(selectDateList.get(3));
		select.selectByValue(s);
		return this;
	}

	public MainPage submitData() {
		destination.submit();
		return this;
	}
}

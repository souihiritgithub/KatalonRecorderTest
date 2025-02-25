package com.example.PracticeAutomationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ClickEvents {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\souih\\KatalonReceorder\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		baseUrl = "https://practice-automation.com/";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
	}

	@Test
	public void testClickEvents() throws Exception {
		driver.get("https://practice-automation.com/click-events/");
		try {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"top-wrap\"]/section/div/h1"));
			assertEquals("Click Events", element.getText());

			WebElement btn = driver
					.findElement(By.xpath("//*[@id=\"post-3145\"]/div/div[3]/div/div/div/div[1]/button"));
			btn.click();
			element = driver.findElement(By.id("demo"));
			assertEquals("Meow!", element.getText());

			btn = driver.findElement(By.xpath("//button[@onclick='dogSound()']"));
			btn.click();
			element = driver.findElement(By.id("demo"));
			assertEquals("Woof!", element.getText());

			btn = driver.findElement(By.xpath("//button[@onclick='pigSound()']"));
			btn.click();
			element = driver.findElement(By.id("demo"));
			assertEquals("Oink!", element.getText());

			btn = driver.findElement(By.xpath("//button[@onclick='cowSound()']"));
			btn.click();
			element = driver.findElement(By.id("demo"));
			assertEquals("Moo!", element.getText());

		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

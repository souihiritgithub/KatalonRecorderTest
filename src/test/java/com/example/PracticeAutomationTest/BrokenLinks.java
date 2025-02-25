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

public class BrokenLinks {
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
	public void testBrokenLinks() throws Exception {
		driver.get("https://practice-automation.com/broken-links/");
		try {
			WebElement element = driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/h1"));
			assertEquals("Broken Links", element.getText());
			assertEquals("Home", driver.findElement(By.linkText("Home")).getText());

			element = driver.findElement(By.xpath("//article[@id='post-1267']/div/p[2]"));
			assertEquals(
					"In an ideal scenario a website should have no broken links. Therefore, it’s important to ensure that all links are working properly. One way to do that is to verify that the HTTP response code is 200 when the link is clicked.",
					element.getText());

			element = driver.findElement(By.xpath("//article[@id='post-1267']/div/p[3]"));
			assertEquals(
					"In this case, clicking the link will return a response code of 404 (Not Found). You could write an automated test that checks for the expected response code of 200.",
					element.getText());

			element = driver.findElement(By.xpath("//article[@id='post-1267']/div/p[3]"));
			assertEquals(
					"In this case, clicking the link will return a response code of 404 (Not Found). You could write an automated test that checks for the expected response code of 200.",
					element.getText());
			element = driver.findElement(By.xpath("//*[@id=\"post-1267\"]/div/figure/table/thead/tr/th[1]"));
			assertEquals("Name", element.getText());

			element = driver.findElement(By.xpath("//*[@id=\"post-1267\"]/div/figure/table/thead/tr/th[2]"));
			assertEquals("Status", element.getText());

			element = driver.findElement(By.xpath("//*[@id=\"post-1267\"]/div/figure/table/tbody/tr/td[1]"));
			assertEquals("missing-page.html", element.getText());

			element = driver.findElement(By.xpath("//*[@id=\"post-1267\"]/div/figure/table/tbody/tr/td[2]"));
			assertEquals("404", element.getText());

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
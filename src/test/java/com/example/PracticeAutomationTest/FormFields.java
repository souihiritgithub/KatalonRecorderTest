package com.example.PracticeAutomationTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class FormFields {
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
  public void testFormFields() throws Exception {
    driver.get("https://practice-automation.com/");
    driver.findElement(By.linkText("Form Fields")).click();
    driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/h1")).click();
    try {
      assertEquals("Form Fields", driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/h1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/nav")).click();
    try {
      assertEquals("Form Fields", driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/nav/span/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("name-input")).clear();
    driver.findElement(By.id("name-input")).sendKeys("Islem");
    driver.findElement(By.xpath("//form[@id='feedbackForm']/p")).click();
    driver.findElement(By.id("name-input")).click();
    String Islem = (String)driver.findElement(By.id("name-input")).getAttribute("value");
    driver.findElement(By.xpath("//input[@type='password']")).click();
    driver.findElement(By.xpath("//input[@type='password']")).clear();
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
    driver.findElement(By.id("drink1")).click();
    driver.findElement(By.id("color1")).click();
    driver.findElement(By.id("automation")).click();
    new Select(driver.findElement(By.id("automation"))).selectByVisibleText("Yes");
    driver.findElement(By.id("feedbackForm")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("souihi.islem2019@gmail.com");
    driver.findElement(By.id("message")).click();
    driver.findElement(By.id("message")).clear();
    driver.findElement(By.id("message")).sendKeys("Islem");
    assertEquals("Islem", driver.findElement(By.id("message")).getAttribute("value"));
    driver.findElement(By.id("submit-btn")).click();
    assertEquals("Message received!", closeAlertAndGetItsText());
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

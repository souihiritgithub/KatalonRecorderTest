package com.example.PracticeAutomationTest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
		driver.get("https://practice-automation.com/form-fields/");
		try {
			WebElement element = driver.findElement(By.xpath("//div[@id='top-wrap']/section/div/h1"));
			assertEquals("Form Fields", element.getText());
			
			assertEquals("Home", driver.findElement(By.linkText("Home")).getText());
			
			element = driver.findElement(By.xpath("//*[@id=\"feedbackForm\"]/label[1]"));
			assertEquals("Name", element.getText());
			
			element = driver.findElement(By.id("name-input"));
			element.clear();
			element.sendKeys("Islem");
		
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/p"));
			assertTrue(element.getText().matches("^[\\s\\S]* Required$"));
			element = driver.findElement(By.xpath("//input[@type='password']"));
			element.click();
			element.clear();
			element.sendKeys("123456");
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/label[3]"));
			assertEquals("What is your favorite drink?", element.getText());
					
			driver.findElement(By.id("drink1")).click();
			Thread.sleep(500);
			driver.findElement(By.id("feedbackForm")).click();
			Thread.sleep(500);
			driver.findElement(By.id("drink2")).click();
			Thread.sleep(500);
			driver.findElement(By.id("drink3")).click();
			Thread.sleep(500);
			driver.findElement(By.id("drink4")).click();
			Thread.sleep(500);
			Actions actions = new Actions(driver);
			WebElement drink5 = driver.findElement(By.id("drink5"));
			actions.moveToElement(drink5).click().perform();
			Thread.sleep(500);
			
			actions = new Actions(driver);
			WebElement color1 = driver.findElement(By.id("color1"));
			actions.moveToElement(color1).click().perform();
			Thread.sleep(500);
			
			WebElement color2 = driver.findElement(By.id("color2"));
			actions.moveToElement(color2).click().perform();
			Thread.sleep(500);
			
			WebElement color3 = driver.findElement(By.id("color3"));
			actions.moveToElement(color3).click().perform();
			Thread.sleep(500);
			
			WebElement color4 = driver.findElement(By.id("color4"));
			actions.moveToElement(color4).click().perform();
			Thread.sleep(500);
			
			WebElement color5 = driver.findElement(By.id("color5"));
			actions.moveToElement(color5).click().perform();
			Thread.sleep(500);
			
			// actions.perform() // Déplace la souris sur l'élément, ce qui peut déclencher
			// le scroll
			// Sélectionner l'élément du menu déroulant
			WebElement automationDropdown = driver.findElement(By.id("automation"));
			// Forcer le scroll avant chaque interaction
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", automationDropdown);
			Thread.sleep(500); // Petite pause pour assurer le scroll
			
			// Sélectionner "Yes"
			new Select(automationDropdown).selectByVisibleText("Yes");
			
			// Sélectionner "No"
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", automationDropdown);
			Thread.sleep(500);
			new Select(automationDropdown).selectByVisibleText("No");
			
			// Sélectionner "Undecided"
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", automationDropdown);
			Thread.sleep(500);
			new Select(automationDropdown).selectByVisibleText("Undecided");
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/ul/li"));
			assertEquals("Selenium", element.getText());
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/ul/li[2]"));
			assertEquals("Playwright", element.getText());
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/ul/li[3]"));
			assertEquals("Cypress", element.getText());
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/ul/li[4]"));
			assertEquals("Appium", element.getText());
			
			element = driver.findElement(By.xpath("//form[@id='feedbackForm']/ul/li[5]"));
			assertEquals("Katalon Studio", element.getText());
			
			driver.findElement(By.id("feedbackForm")).click();
			element = driver.findElement(By.id("email"));
			element.click();
			element.clear();
			element.sendKeys("Souihi.islem@gmail.com");
			
			element = driver.findElement(By.id("message"));
			element.click();
			element.clear();
			element.sendKeys("hello word");
			
			element = driver.findElement(By.id("submit-btn"));
			assertEquals("Submit", element.getText());
			element.click();
			
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
